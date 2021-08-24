package model;

import com.google.gson.annotations.SerializedName;
import db.Converter;
import org.hibernate.annotations.DynamicUpdate;
import repository.Victorina;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicUpdate
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    @Exclude
    @ManyToOne
    @JoinColumn(name = "victorina_id", referencedColumnName = "id")
    private Victorina victorina;
    private String difficulty;
    private String question;
    @Column(name = "correct_answer")
    @SerializedName("correct_answer")
    private String correctAnswer;
    @Convert(converter = Converter.class)
    @SerializedName("incorrect_answers")
    @Column(name = "incorrect_Answers")
    private List<String> incorrectAnswers;
    @Column(name = "user_answer_is_correct")
    private boolean correctUser;
    
    public Question() {
    }

    public Question(String type, String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswers) {
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return new ArrayList<>(incorrectAnswers);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void setVictorina(Victorina victorina) {
        this.victorina = victorina;
    }

    public Victorina getVictorina() {
        return victorina;
    }

       public boolean isCorrectUser() {
        return correctUser;
    }

    public void setCorrectUser(boolean correctUser) {
        this.correctUser = correctUser;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(type, question1.type) &&
                Objects.equals(difficulty, question1.difficulty) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(correctAnswer, question1.correctAnswer) &&
                Objects.equals(incorrectAnswers, question1.incorrectAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, difficulty, question, correctAnswer, incorrectAnswers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }
}
