package br.com.senac.ccs.thinkfast;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/thinkfast/*", produces = "application/json")
public class ThinkFastController {

    private ThinkFastGame game;

    public void init(ServletConfig servletConfig) {
        this.game = new ThinkFastGame();
        this.game.init();
    }

    @RequestMapping(value = "play", method = RequestMethod.GET)
    public @ResponseBody Result play(@RequestParam String name, HttpSession session) {
        DeferredResult<Result> deferredResult = new DeferredResult<Result>();
        String id = session.getId();
        Screen screen = new WebScreen(deferredResult);
        return game.play(id,name,screen);
    }

    public void bind(HttpSession session) {
        String id = session.getId();
    }

    public void answer(@RequestParam String answer, HttpSession session) {
        String id = session.getId();
    }

}