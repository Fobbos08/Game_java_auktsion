package servlets;

import game.GameManipulator;

import javax.servlet.RequestDispatcher;
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
 * Created by Эмиль on 14.05.2015.
 */
@WebServlet(name = "createGamePage")
public class CreateGamePage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean isOk;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("gameId")) {
                    isOk = GameManipulator.startGame(UUID.fromString(cookie.getValue()));
                }
            }
        }
        Writer w = response.getWriter();
        w.write("ok");
        w.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        UUID gameId = UUID.randomUUID();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    gameId = GameManipulator.createGame(UUID.fromString(cookie.getValue()));
                    GameManipulator.addUserToGame(gameId, UUID.fromString(cookie.getValue()));
                }
            }
        }
        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/createGamePage.jsp");
        Cookie c = new Cookie("gameId", gameId.toString());
        response.addCookie(c);
        dispatcher.forward(request,  response);
    }
}
