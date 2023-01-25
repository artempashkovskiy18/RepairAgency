package servlets;

import constants.OtherConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO error.jsp doesn`t find error message
public class CommonServletMethods {
    public static void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute(OtherConstants.ERROR_PAGE_MESSAGE_NAME, errorMessage);
        request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
