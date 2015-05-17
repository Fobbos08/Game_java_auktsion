package servlets;

import business.ConstFields;
import game.GameManipulator;
import game.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Эмиль on 16.05.2015.
 */
@WebServlet(name = "Game")
public class Game extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
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
        if (data.equals("buy")) {
            writer.write("{isBuy:" + GameManipulator.buy(gameId, id) + "}");
        }
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
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

        if (data.equals("connection")) {
            GameManipulator.addUserToGame(UUID.fromString(request.getParameter("gameId")), id);
        }
        if (data.equals("getMyMoney")) {
            Player p = GameManipulator.getPlayer(gameId, id);
            RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/playerStatus.jsp");
            request.setAttribute("player", p);
            dispatcher.forward(request,  response);
        }
        if (data.equals(ConstFields.GetStat)) {
            ArrayList<Player> players = GameManipulator.getStats(gameId);
            RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/stat.jsp");
            request.setAttribute("players", players);
            dispatcher.forward(request,  response);
        }
    }
}
