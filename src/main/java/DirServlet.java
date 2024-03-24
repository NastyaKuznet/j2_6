import Model.UserProfile;
import Service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/files")
public class DirServlet extends HttpServlet{
    private final DBService dbService = new DBService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        String login = (String)req.getSession().getAttribute("login");
        String pass = (String)req.getSession().getAttribute("pass");
        if(dbService.getUser(login)==null || !dbService.getUser(login).getPassword().equals(pass)) {
            String url = req.getRequestURL().toString();
            res.sendRedirect(url.substring(0, url.lastIndexOf('/')) + "/");
            return;
        }

        String pathReq = req.getParameter("path");
        String pathUser = "D:\\files\\" + login;
        String curPath;
        if(pathReq != null){
            if(pathReq.startsWith(pathUser)){
                curPath = pathReq;
            } else {
                curPath = pathUser;
            }
        } else {
            curPath = pathUser;
        }

        File file = new File(curPath);
        File[] files;
        File[] directories;
        if(file.isDirectory()) {
            files = file.listFiles(File :: isFile);
            directories = file.listFiles(File :: isDirectory);
        }
        else {
            files = new File[0];
            directories = new File[0];
        }

        req.setAttribute("curPath", curPath);
        req.setAttribute("files", files);
        req.setAttribute("dires", directories);
        req.getRequestDispatcher("mypage.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("pass");
        String url = req.getRequestURL().toString();
        res.sendRedirect(url.substring(0, url.lastIndexOf('/')) + "/");
    }
}
