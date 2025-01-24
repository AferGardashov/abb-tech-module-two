package authentication.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import authentication.exeptions.BaseExceptionDto;

import java.io.IOException;

@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //with session
//            HttpSession httpSession = req.getSession(false);
//            if (httpSession != null && httpSession.getAttribute("username").equals("cavidan.hatamov@gmail.com")) {
//                httpSession.invalidate();
//            }


            //with cookies
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username") && cookie.getValue().equals("Name")) {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception exception) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR)));
        }
    }
}