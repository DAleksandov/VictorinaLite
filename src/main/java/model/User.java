package model;

import repository.Victorina;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String login;
    private String pass;
    @Column (name = "show_corr_ans")
    private boolean showCorrectAnswers;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Victorina> victorinaArrayList;

    public User() {
        this.victorinaArrayList = new ArrayList<>();
    }

    public User(String login, String pass) {
        this.victorinaArrayList = new ArrayList<>();
        this.login = login;
        this.pass = pass;
        this.showCorrectAnswers = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVictorinaArrayList(List<Victorina> victorinaArrayList) {
        this.victorinaArrayList = victorinaArrayList;
    }

    public void setVictorinaArrayList(ArrayList<Victorina> victorinaArrayList) {
        this.victorinaArrayList = victorinaArrayList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
   
     public boolean getshowCorrectAnswers() {
        return showCorrectAnswers;
    }

    public void setshowCorrectAnswers(boolean showCorrectAnswers) {
        this.showCorrectAnswers = showCorrectAnswers;
    }

    public void addVic(Victorina victorina) {
        this.victorinaArrayList.add(victorina);
        victorina.setUser(this);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", pass='" + pass + '\''
                + ", victorinaArrayList=" + victorinaArrayList
                + '}';
    }
}
