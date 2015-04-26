package servlets;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Эмиль on 25.04.2015.
 */
@WebServlet(name = "loginBlock")
public class loginBlock extends HttpServlet {
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
                //insert registration code (insert in DB)!!!!!!!!!!!
                writer.write("{result: ok}");
            }
            writer.close();
        }
        RequestDispatcher dispatcher=getServletConfig().getServletContext().getRequestDispatcher("/shared/login.jsp");
        dispatcher.forward(request,  response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String registerValidate(HttpServletRequest request)
    {
        String res = "";
        res = res+=emailValidate(request.getParameter("email"));
        return res;
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
