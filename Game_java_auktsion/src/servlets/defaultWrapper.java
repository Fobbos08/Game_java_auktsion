package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Эмиль on 25.04.2015.
 */
@WebServlet(name = "defaultWrapper")
public class defaultWrapper extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getNamedDispatcher("default");

        HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
            public String getServletPath() { return ""; }
        };

        rd.forward(wrapped, response);
    }
}
