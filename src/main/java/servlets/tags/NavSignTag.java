package servlets.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class NavSignTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {

            out.print("<nav>\n" +
                    "    <ul class=\"topMenu\">\n" +
                    "        <li><a href=\"index.jsp\" class=\"down\">Main</a>\n" +
                    "            <ul class=\"submenu\">\n" +
                    "                <li><a href=\"\">Prices</a></li>\n" +
                    "                <li><a href=\"createOrder.jsp\">Create order</a></li>\n" +
                    "            </ul>\n" +
                    "        </li>\n" +
                    "        <li><a href=\"contacts.jsp\">Contacts</a></li>\n" +
                    "        <li><a href=\"\">About us</a></li>\n" +
                    "        <li><a href=\"orders\" id=\"get-orders-button\">Get orders</a></li>\n" +
                    "    </ul>\n" +
                    "    <div>\n" +
                    "        <a class=\"float-right btn btn-outline-primary\" name=\"sign-in-button\">Log in</a>\n" +
                    "        <a class=\"float-right btn btn-outline-primary\" name=\"sign-up-button\" >Sign up</a>\n" +
                    "        <a class=\"float-right btn btn-outline-primary\" name=\"log-out-button\" >Log out</a>\n" +
                    "    </div>\n" +
                    "</nav>");


            out.print("\n" +
                    "<div class = \"sign-container\" id=\"sign-in\">\n" +
                    "    <div class=\"card\">\n" +
                    "        <article class=\"card-body\">\n" +
                    "            <a href=\"\" class=\"float-right btn btn-outline-primary\" name=\"close\">X</a>\n" +
                    "            <h4 class=\"card-title mb-4 mt-1\">Sign in</h4>\n" +
                    "\n" +
                    "            <form action=\"login\" method=\"post\" id=\"sign-in-form\">\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <label>Your email</label>\n" +
                    "                    <input name=\"email\" class=\"form-control\" id=\"sign-in-email\" placeholder=\"Email\" type=\"email\" required>\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <a class=\"float-right\" href=\"#\">Forgot your password?</a>\n" +
                    "                    <label>Your password</label>\n" +
                    "                    <input name=\"password\" class=\"form-control\" placeholder=\"******\" type=\"password\" required>\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <div class=\"checkbox\">\n" +
                    "                        <label> <input name=\"remember-me\" type=\"checkbox\" checked /> Remember me </label>\n" +
                    "                    </div> <!-- checkbox .// -->\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-primary btn-block\" id=\"submit-sign-in\">Sign in</button>\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "            </form>\n" +
                    "\n" +
                    "        </article>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div class = \"sign-container\" id =\"sign-up\">\n" +
                    "    <div class=\"card\">\n" +
                    "        <article class=\"card-body\">\n" +
                    "            <a href=\"\" class=\"float-right btn btn-outline-primary\" name=\"close\">X</a>\n" +
                    "            <h4 class=\"card-title mb-4 mt-1\">Sign up</h4>\n" +
                    "\n" +
                    "            <form action=\"registration\" method=\"post\" id=\"sign-up-form\">\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <label>Your name</label>\n" +
                    "                    <input name=\"name\" class=\"form-control\" placeholder=\"Name\" required>\n" +
                    "                </div>\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <label>Your phone</label>\n" +
                    "                    <input name=\"phone\" class=\"form-control\" placeholder=\"Phone\" type = \"tel\">\n" +
                    "                </div>\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <label>Your email</label>\n" +
                    "                    <input name=\"email\" class=\"form-control\" id=\"sign-up-email\" placeholder=\"Email\" type=\"email\" required>\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <label>Your password</label>\n" +
                    "                    <input name=\"password\" class=\"form-control\" id=\"sign-up-password\" placeholder=\"******\" type=\"password\" required>\n" +
                    "                </div>\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <label>Repeat your password</label>\n" +
                    "                    <input name=\"password-repeat\" class=\"form-control\" placeholder=\"******\" type=\"password\" required>\n" +
                    "                </div><!-- form-group// -->\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <div class=\"checkbox\">\n" +
                    "                        <label> <input name=\"remember-me\" type=\"checkbox\" checked /> Remember me </label>\n" +
                    "                    </div> <!-- checkbox .// -->\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-primary btn-block\" id=\"submit-sign-up\">Sign up</button>\n" +
                    "                </div> <!-- form-group// -->\n" +
                    "            </form>\n" +
                    "\n" +
                    "        </article>\n" +
                    "    </div>\n" +
                    "</div>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
        //return super.doStartTag();
    }
}
