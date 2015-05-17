package servlets;

import game.GameManipulator;
import game.Player;
import game.Tovar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Эмиль on 16.05.2015.
 */
@WebServlet(name = "Users")
public class Users extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/players.jsp");

        ArrayList<Player> players = GameManipulator.getPlayers(gameId);

        request.setAttribute("users", players);

        dispatcher.forward(request,  response);
    }
}
