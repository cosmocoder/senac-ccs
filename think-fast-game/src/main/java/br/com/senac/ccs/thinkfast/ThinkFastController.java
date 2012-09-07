package br.com.senac.ccs.thinkfast;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( urlPatterns = { "/thinkfast"}, asyncSupported = true)
public class ThinkFastController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    public class Question {
        private String description;
        private List<String> answers;
        private String correctAnswer;

        public Question(String description, List<String> answers, String correctAnswer) {
            this.description = description;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }

        public String getDescription() {
            return description;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }

}
