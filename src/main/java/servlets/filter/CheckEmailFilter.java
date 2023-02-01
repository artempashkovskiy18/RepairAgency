package servlets.filter;

import models.User;
import servlets.CommonServletMethods;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/createOrder", "/orders"})
public class CheckEmailFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();

        if(cookies == null){
            CommonServletMethods.forwardToErrorPage(req, resp, "you are not logged in");
        }else{
            User user = CommonServletMethods.getUserFromCookies(cookies);
            if(user == null){
                CommonServletMethods.forwardToErrorPage(req, resp, "no such user. Try to log in");

            }else {
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
