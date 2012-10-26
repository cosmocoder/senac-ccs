package br.com.senac.ccs.thinkfast;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.Collections;
import java.util.List;

public class QuestionRepositoryImpl extends QueryDslRepositorySupport implements QuestionRepositoryExtension {

    private Question current;

    public QuestionRepositoryImpl() {
        super(Question.class);
    }

    @Override
    public Question findNext(Question answered) {
        QQuestion question = QQuestion.question;
        List<Question> questions = from(question).where(question.ne(answered)).limit(10).list(question);
        Collections.shuffle(questions);
        return questions.get(0);




        //EntityPathBase<Question> question = entity(Question.class);
        //List<Question> questions = from(question).where(expression(question.getAnswer()).ne(answered)).limit(10).list(question);
    }

    @Override
    public Question getCurrent() {
        if(current == null) {
            QQuestion question = QQuestion.question;
            this.current = from(question).singleResult(question);
        }
        return current;
    }
}
