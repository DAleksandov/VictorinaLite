package model;

import java.util.ArrayList;
import java.util.Objects;

public class Questions {
    private String response_code;
    private ArrayList<Question> results;

    public Questions(String response_code, ArrayList<Question> results) {
        this.response_code = response_code;
        this.results = results;
    }

    public String getResponse_code() {
        return response_code;
    }

    public ArrayList<Question> getResults() {
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questions that = (Questions) o;
        return Objects.equals(response_code, that.response_code) &&
                Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response_code, results);
    }

    @Override
    public String
    toString() {
        return "QuestionConnection{" +
                "response_code='" + response_code + '\'' +
                ", results=" + results +
                '}';
    }
}
