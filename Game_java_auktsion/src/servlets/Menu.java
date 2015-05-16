package servlets;

import business.SessionManipulator;
import game.GameManipulator;
import game.Tovar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Эмиль on 16.05.2015.
 */
@WebServlet(name = "Menu")
public class Menu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean isLogined = false;
        boolean isInGame = false;
        UUID gameId = null;
        UUID playerId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    isLogined = verifySession(UUID.fromString(cookie.getValue()));
                }
                if (cookie.getName().equals("gameId")) {
                    gameId = UUID.fromString(cookie.getValue());
                    //isInGame = true;
                }
            }
        }
        if (gameId != null && playerId != null) {
            isInGame = verifyGame(gameId, playerId);
        }

        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/gameWindow.jsp");

        request.setAttribute("isLogined", isLogined);
        request.setAttribute("isInGame", isInGame);

        dispatcher.forward(request,  response);
    }

    private boolean verifySession(UUID id)
    {
        return SessionManipulator.getUser(id) == null ? false : true;
    }

    private boolean verifyGame(UUID gameId, UUID playerId)
    {
        return GameManipulator.getPlayer(gameId, playerId) == null ? false : true;
    }
}
