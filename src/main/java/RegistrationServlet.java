import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String savePassword = req.getParameter("save-password");
        PrintWriter out = resp.getWriter();
        out.println(email);
        out.println(password);
        out.println(savePassword);
    }
}
