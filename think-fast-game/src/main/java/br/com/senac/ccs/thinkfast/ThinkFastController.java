package br.com.senac.ccs.thinkfast;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ThinkFastController( ThinkFastGame game) {
        this.game = game;
    }

    private ThinkFastGame game;

    @RequestMapping(value = "play", method = RequestMethod.GET)
    public @ResponseBody Result play(@RequestParam String name, HttpSession session) {
        return game.play(session.getId(), name, new WebScreen());
    }

    @RequestMapping(value = "bind", method = RequestMethod.GET)
    public @ResponseBody DeferredResult bind(HttpSession session) {
        WebScreen screen = new WebScreen();
        String id = session.getId();
        game.bind(id, screen);
        return screen.getDeferredResult();
    }

    @RequestMapping(value = "answer", method = RequestMethod.GET)
    public @ResponseBody Result answer(@RequestParam String answer, HttpSession session) {
        return game.answer(session.getId(), answer);
    }

}