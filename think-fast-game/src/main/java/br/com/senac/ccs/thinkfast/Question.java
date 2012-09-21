package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {

    private final String description;
    private final List<String> answers;
    private final String answer;

    public Question( final String description, final List<String> answers, final String answer ) {
        this.description = description;
        this.answers = answers;
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @JsonIgnore
    public String getAnswer() {
        return answer;
    }
}