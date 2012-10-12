package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
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
    private final List<Question> questions;
    private Question currentQuestion;

    public ThinkFastGame() {
        this.participants = new ConcurrentHashMap<String, Participant>();
        this.questions = new ArrayList<Question>();
        this.lock = new ReentrantLock();
    }

    public Result play(String id, String name, Screen screen) {
        lock.lock();
        try {
            Participant participant = new Participant(id, name, screen);
            participants.put(id, participant);
            return new Result(currentQuestion, String.format("Welcome: %s", participant.getName()));
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
            if (this.currentQuestion.getAnswer().equals(answer)) {
                Question question = currentQuestion;
                Collections.shuffle(questions);
                currentQuestion = questions.get(0);
                questions.add(question);
                Participant winner = participants.get(id);
                winner.incrementScore();
                winner.notify(new Result(currentQuestion, String.format("Congratulations!")));
                participants.remove(id);
                Result result = new Result(currentQuestion, String.format("Player %s have answered faster, try again.", winner.getName()));
                for (Participant participant : participants.values()) {
                    participant.notify(result);
                }
                participants.put(id, winner);
            } else {
                return new Result(currentQuestion, "Fail!");
            }
        } finally {
            lock.unlock();
        }

        return null;
    }

    @PostConstruct
    public void init() {
        this.questions.add(new Question("Qual a capital dos EUA?", Arrays.asList(new String[]{"Washington DC", "California", "Nevada"}), "Washington DC"));
        this.questions.add(new Question("Qual a capital da Russia?", Arrays.asList(new String[]{"Berlin", "Paris", "Moscou"}), "Moscou"));
        this.currentQuestion = questions.get(0);
    }
}