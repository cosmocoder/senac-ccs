package br.com.senac.ccs.thinkfast;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/thinkfast"},
        asyncSupported = true, loadOnStartup = 1)
public class ThinkFastController extends HttpServlet {

    private ThinkFastGame game;

    @Override
    public void init(ServletConfig servletConfig) {
        this.game = new ThinkFastGame();
        this.game.init();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String id = request.getSession().getId();
        if ("play".equals(action)) {
            game.play(id, request.getParameter("name"), request.startAsync());
        } else if ("answer".equals(action)) {
            game.answer(id, request.getParameter("answer"));
        } else if ("bind".equals(action)) {
            game.bind(id, request.startAsync());
        }
    }
}