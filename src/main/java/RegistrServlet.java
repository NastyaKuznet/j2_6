import Model.UserProfile;
import Service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;

@WebServlet("/registration")
public class RegistrServlet extends HttpServlet{
    private final DBService dbService = new DBService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.getRequestDispatcher("regpage.jsp").forward(req,res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");

        if(login.isEmpty() || email.isEmpty() || password.isEmpty()) {
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().println("Все поля должны быть заполненны.");
            return;
        }

        if(dbService.getUser(login) == null){
            UserProfile newUser = new UserProfile(login, email, password);
            dbService.addUser(newUser);
            req.getSession().setAttribute("login",login);
            req.getSession().setAttribute("pass",password);

            String path = "D:\\files\\" + login;
            File newFolder = new File(path);
            boolean resultCreate = newFolder.mkdir();
            if(!resultCreate){
                res.setContentType("text/html;charset=utf-8");
                res.getWriter().println("Произошла ошибка во время создания папки.");
                return;
            }
            String url = req.getRequestURL().toString();
            res.sendRedirect(url.substring(0, url.lastIndexOf("/")) + "/files");
        }
        else{
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().println("Пользователь с таким логином уже есть.");
        }
    }
}
