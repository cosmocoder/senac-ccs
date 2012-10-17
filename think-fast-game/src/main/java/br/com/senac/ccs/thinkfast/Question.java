package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers;
    @OneToOne(fetch = FetchType.EAGER)
    private Answer answer;

    public Question() {
    }

    public Question(final String description, final List<Answer> answers, final Answer answer) {
        this.description = description;
        this.answers = answers;
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @JsonIgnore
    public Answer getAnswer() {
        return answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}