package br.com.senac.ccs.thinkfast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.AsyncContext;

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

    public void play(String id, String name, AsyncContext asyncContext) throws IOException {
        lock.lock();
        try {
            Participant participant = new Participant(id, name, asyncContext);
            participants.put(id, participant);
            participant.notify(new Result(currentQuestion, "Welcome:"));
        } finally {
            lock.unlock();
        }
    }

    public void bind(String id, AsyncContext asyncContext) {
        participants.get(id).setAsyncContext(asyncContext);
    }

    public void answer(String id, String answer) throws IOException {
        lock.lock();
        try {
            if (this.currentQuestion.getAnswer().equals(answer)) {
                Question question = currentQuestion;
                Collections.shuffle(questions);
                currentQuestion = questions.get(0);
                questions.add(question);
                Participant winner = participants.remove(id);
                winner.notify(new Result(currentQuestion, "Congratulations!"));
                Result result = new Result(currentQuestion, String.format("Player %s have answered faster, try again.", winner.getName()));
                for (Participant participant : participants.values()) {
                    participant.notify(result);
                }
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