import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

@WebServlet("/download")
public class DownloadFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res) throws ServletException, IOException {
        String path = req.getParameter("path");
        File dlFile = new File(path);
        FileInputStream inStream = new FileInputStream(dlFile);

        String mimeType = getServletContext().getMimeType(path);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        res.setContentType(mimeType);
        res.setContentLength((int) dlFile.length());

        res.setHeader("Content-Disposition", "attachment; filename=\"" + dlFile.getName() + "\"");

        OutputStream outStream = res.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
