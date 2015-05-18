package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;

/**
 * Created by Эмиль on 25.04.2015.
 */
public class StartPage extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/dbServlet.jsp");
        dispatcher.forward(request,  response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/startPage.jsp");
        dispatcher.forward(request,  response);
    }
}
