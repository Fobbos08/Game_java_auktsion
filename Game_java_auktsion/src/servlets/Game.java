package servlets;

import game.GameManipulator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

/**
 * Created by Эмиль on 16.05.2015.
 */
@WebServlet(name = "Game")
public class Game extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("tag");
        Cookie[] cookies = request.getCookies();
        UUID gameId = UUID.randomUUID();
        UUID id = UUID.randomUUID();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    id = UUID.fromString(cookie.getValue());
                }
                if (cookie.getName().equals("gameId")) {
                    gameId = UUID.fromString(cookie.getValue());
                }
            }
        }
        Writer writer = response.getWriter();
        writer.write("{isBuy:"+ GameManipulator.buy(gameId, id)+"}");
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
