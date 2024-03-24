import Model.UserProfile;
import Service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/")
public class AuthServlet extends HttpServlet{
    private final DBService dbService = new DBService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.getRequestDispatcher("logpage.jsp").forward(req,res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");

        if(login.isEmpty() || password.isEmpty()) {
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().println("Все поля должны быть заполненны.");
            return;
        }

        UserProfile user = dbService.getUser(login);
        if(user == null || !user.getPassword().equals(password)){
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().println("Неправильный логин или пароль.");
            return;
        }
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("pass", password);
        String url = req.getRequestURL().toString();
        res.sendRedirect(url.substring(0, url.lastIndexOf('/')) + "/files");
    }
}
