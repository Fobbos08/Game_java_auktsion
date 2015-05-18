package servlets;

import game.GameManipulator;
import game.Tovar;
import game.bonuses.Bonus;

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
 * Created by Эмиль on 18.05.2015.
 */
@WebServlet(name = "AdminPage")
public class AdminPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        if (data.equals("addtovar")) {
            RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/addTovarPage.jsp");
            dispatcher.forward(request, response);
        }
        if (data.equals("settings")) {
            RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/settingPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/adminPage.jsp");
        dispatcher.forward(request,  response);
    }
}
