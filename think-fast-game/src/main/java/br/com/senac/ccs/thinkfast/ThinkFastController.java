package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

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