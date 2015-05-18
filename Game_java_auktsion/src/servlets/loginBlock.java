package servlets;



import business.DBConnector;
import business.SessionManipulator;
import models.User;
import sun.jdbc.odbc.JdbcOdbcDriver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by Эмиль on 25.04.2015.
 */


@WebServlet(name = "loginBlock")
public class LoginBlock extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("register") != null)
        {
            PrintWriter writer = response.getWriter();
            String validateResult = registerValidate(request);
            if (validateResult.length()>10)
            {
                writer.write(validateResult);
            }else
            {
                try {
                    DBConnector.registerUser(request.getParameter("login"),
                            request.getParameter("firstName"),
                            request.getParameter("lastName"),
                            request.getParameter("email"),
                            request.getParameter("password"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                writer.write("{result: ok}");
            }
            writer.close();
        }else
        if (request.getParameter("loginForm") != null)
        {
            PrintWriter writer = response.getWriter();

            String login = request.getParameter("login");
            String password = request.getParameter("password");

            User user = DBConnector.getUser(login, password);
            if (user == null) {
                writer.write("{result: notFount}");
                writer.close();
                return;
            }
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        user.setSessionId(cookie.getValue());
                    }
                }
            }
            SessionManipulator.addUser(user);
            writer.write(user.getGUID().toString());
            writer.close();
        }else {
            RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher(
                    "/shared/login.jsp"
            );
            dispatcher.forward(request,  response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String registerValidate(HttpServletRequest request)
    {
        String res = "";
        res+=validateEmpty(request.getParameter("login"), "login");
        res+=validateEmpty(request.getParameter("firstName"), "first name");
        res+=validateEmpty(request.getParameter("lastName"), "last name");
        res+=emailValidate(request.getParameter("email"));
        res += validatePassword(request.getParameter("password"), request.getParameter("rpassword"));
        return res;
    }

    private String validateEmpty(String value, String name)
    {
        String errorMessage = "<span>Invalid "+name+", min length 2 symbols</span>";
        if (value == null) return errorMessage;
        if (value.length()<2) return errorMessage;
        return "";
    }

    private String validatePassword(String password, String rpassword)
    {
        String errorMessage1 = "<span>Invalid password, min length 4 symbols</span>";
        String errorMessage2 = "<span>Passwords do not match</span>";
        if (password == null) return errorMessage1;
        if (password.length()<4) return errorMessage1;
        if (rpassword == null) return errorMessage2;
        if (!password.equals(rpassword)) return errorMessage2;
        return "";
    }

    private String emailValidate(String email)
    {
        String errorMessage = "<span>Invalid email</span>";
        if (email == null)
            return errorMessage;
        Pattern p = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher m = p.matcher(email);
        boolean b = m.matches();
        if (b) return "";
        return errorMessage;
    }
}
