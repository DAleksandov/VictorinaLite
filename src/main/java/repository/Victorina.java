package repository;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import crypter.CryptographyFiles;
import model.Question;
import model.Questions;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.DynamicUpdate;
import model.User;

import javax.persistence.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import model.Exclude;

@Entity
@DynamicUpdate
@Table(name = "victorina")
public class Victorina implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "victorina" , cascade = CascadeType.ALL)
    private List<Question> questions;
    @Transient
    @Exclude
    private String key;
     
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Exclude
    private User user;

    public Victorina() {
        this.questions = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Victorina(String filePath, String key) throws IOException {
        this.key = key;
        File file = new File(filePath);
        this.questions = new ArrayList<>();
        Gson gson = new Gson();
        if (file.getName().endsWith(".json")) {
            try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
                questions = gson.fromJson(buff, new TypeToken<ArrayList<Question>>() {
                }.getType());
            }
        }
        this.questions = crypt(new ArrayList<>(this.questions), this.key);
    }

    //https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple

    public Victorina(int count, String category, String difficulty, String type, String key) throws IOException {
        this.key = key;
        Gson gson = new Gson();
        if (!category.equals("8"))
            category = "&category=" + category;
        else
            category = "";
        if (!difficulty.equals(""))
            difficulty = "&difficulty=" + difficulty;
        else
            difficulty = "";
        if (!type.equals("1"))
            type = "&type=" + type;
        else
            type = "";
        URL url = new URL("https://opentdb.com/api.php?amount=" + count + category + difficulty + type);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream inputStream = url.openStream();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            String str = new String(bufferedInputStream.readAllBytes());
            Questions questions = gson.fromJson(str, new TypeToken<Questions>() {
            }.getType());
            for (int i = 0; i < questions.getResults().size(); i++) {
                Question question = questions.getResults().get(i);
                question.setVictorina(this);
                question.setCorrectAnswer(StringEscapeUtils.unescapeHtml4(question.getCorrectAnswer()));
                question.setDifficulty(StringEscapeUtils.unescapeHtml4((question.getDifficulty())));
                question.setQuestion(StringEscapeUtils.unescapeHtml4((question.getQuestion())));
                question.setType(StringEscapeUtils.unescapeHtml4((question.getType())));
                ArrayList<String> list = new ArrayList<>(); //заменить на сеттер в модели данных
                for (int j = 0; j < question.getIncorrectAnswers().size(); j++) {
                    list.add(StringEscapeUtils.unescapeHtml4(question.getIncorrectAnswers().get(j)));
                }
                question.setIncorrectAnswers(list);
            }
            this.questions = questions.getResults();
        }
    }

    public void saveToFile(String filePath) throws IOException {
        ArrayList<Question> questions = crypt(new ArrayList<>(this.questions), this.key);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)))) {
            Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                   if (fieldAttributes.getAnnotation(Exclude.class) != null)
                    return true;
                return false;
                }

                @Override
                public boolean shouldSkipClass(Class<?> type) {
                  return false;
                }
            }).create();
            gson.toJson(questions, bufferedWriter);
        }
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    private static ArrayList<Question> crypt(ArrayList<Question> questionsFrom, String key) throws IOException {
        ArrayList<Question> questions = new ArrayList<>(questionsFrom);
        CryptographyFiles crypter = new CryptographyFiles();
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setCorrectAnswer(crypter.crypt(questions.get(i).getCorrectAnswer(), key));
            ArrayList<String> incA = new ArrayList<>();
            for (int j = 0; j < questions.get(i).getIncorrectAnswers().size(); j++) {
                incA.add(crypter.crypt(questions.get(i).getIncorrectAnswers().get(j), key));
            }
            questions.get(i).setIncorrectAnswers(incA);
        }
        return questions;
    }

    public void addQuestion (Question question) {
        this.questions.add(question);
        question.setVictorina(this);
    }
    
    public ArrayList<Question> getQuestionsWithoutEncrypt() {
        return new ArrayList<>(this.questions);
    }

    @Override
    public String toString() {
        return "Victorina{" +
                "id=" + id +
                ", questions=" + questions +
                ", key='" + key + '\'' +
                '}';
    }
}
