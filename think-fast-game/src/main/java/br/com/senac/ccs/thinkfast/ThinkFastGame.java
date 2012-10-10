package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThinkFastGame {

    private final ConcurrentHashMap<String, Participant> participants;
    private final Lock lock;
    private final List<Question> questions;
    private Question currentQuestion;
    ObjectMapper objectMapper = new ObjectMapper();

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

    public void answer(String id, String answer) {
        lock.lock();
        try {

            if (this.currentQuestion.getAnswer().equals(answer)) {
                Question question = currentQuestion;
                Collections.shuffle(questions);
                currentQuestion = questions.get(0);
                questions.add(question);
                Participant winner = participants.get(id);
                winner.incrementScore();
                String placar = objectMapper.writeValueAsString(participants.values());
                winner.notify(new Result(currentQuestion, String.format("Congratulations! Placar %s", placar)));
                participants.remove(id);
                Result result = new Result(currentQuestion, String.format("Player %s have answered faster, try again. Placar %s", winner.getName(), placar));
                for (Participant participant : participants.values()) {
                    participant.notify(result);
                }
                participants.put(id, winner);
            } else {
                Participant participant = participants.get(id);
                participant.notify(new Result(currentQuestion, "Fail!"));
            }

        } finally {
            lock.unlock();
        }
    }

    public void init() {
        this.questions.add(new Question("Qual a capital dos EUA?", Arrays.asList(new String[]{"Washington DC", "California", "Nevada"}), "Washington DC"));
        this.questions.add(new Question("Qual a capital da Russia?", Arrays.asList(new String[]{"Berlin", "Paris", "Moscou"}), "Moscou"));
        this.currentQuestion = questions.get(0);
    }
}