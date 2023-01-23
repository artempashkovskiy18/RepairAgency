package servlets.commands;

import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO
public class RegistrationCommand implements ICommand{
    UserService service = new UserService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String savePassword = req.getParameter("remember-me");

        if(service.checkIfUserExists(email)){

        }

        return "0";
    }
}
