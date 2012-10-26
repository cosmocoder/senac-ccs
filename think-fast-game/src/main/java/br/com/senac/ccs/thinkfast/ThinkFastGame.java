package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ThinkFastGame {

    private final ConcurrentHashMap<String, Participant> participants;
    private final Lock lock;
    private QuestionRepository questionRepository;

    @Autowired
    public ThinkFastGame(QuestionRepository questionRepository) {
        this.participants = new ConcurrentHashMap<String, Participant>();
        this.lock = new ReentrantLock();
        this.questionRepository = questionRepository;
    }

    public Result play(String id, String name, Screen screen) {
        lock.lock();
        try {
            Participant participant = new Participant(id, name, screen);
            participants.put(id, participant);
            return new Result(questionRepository.getCurrent(), String.format("Welcome: %s", participant.getName()));
        } finally {
            lock.unlock();
        }
    }

    public void bind(String id, Screen screen) {
        participants.get(id).setScreen(screen);
    }

    public Result answer(String id, String answer) {
        lock.lock();
        try {
            Question question = questionRepository.getCurrent();
            if (question.getAnswer().getDescription().equals(answer)) {
                question = questionRepository.findNext(question);
                Participant winner = participants.get(id);
                winner.incrementScore();
                winner.notify(new Result(question, String.format("Congratulations!")));
                participants.remove(id);
                Result result = new Result(question, String.format("Player %s have answered faster, try again.", winner.getName()));
                for (Participant participant : participants.values()) {
                    participant.notify(result);
                }
                participants.put(id, winner);
            } else {
                return new Result(question, "Fail!");
            }
        } finally {
            lock.unlock();
        }

        return null;
    }

    @PostConstruct
    public void init() {
        Answer rightAnswer1 = new Answer("Washington DC");
        questionRepository.save(new Question("Qual a capital dos EUA?", Arrays.asList(
                new Answer[]{
                        rightAnswer1,
                        new Answer("California"),
                        new Answer("Nevada")}),
                rightAnswer1)
        );
        Answer rightAnswer2 = new Answer("Moscou");
        questionRepository.save(new Question("Qual a capital da Russia?",
                Arrays.asList(new Answer[]{
                        new Answer("Berlin"),
                        new Answer("Paris"),
                        rightAnswer2}),
                rightAnswer2)
        );
    }
}