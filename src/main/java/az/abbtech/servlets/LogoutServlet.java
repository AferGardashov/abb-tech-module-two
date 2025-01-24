package az.abbtech.servlets;

import az.abbtech.exception.BadRequestException;
import az.abbtech.exception.BaseExceptionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession httpSession = req.getSession(false);

            if (httpSession != null) {
                // Invalidate the session
                httpSession.invalidate();

                // Remove session cookie
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("JSESSIONID".equals(cookie.getName())) {
                            cookie.setValue(null);
                            cookie.setMaxAge(0); // Set cookie to expire immediately
                            cookie.setPath("/"); // Match the path of the session cookie
                            resp.addCookie(cookie); // Add cookie to response
                        }
                    }
                }
            }

        } catch (BadRequestException badRequestException) {
            resp.setStatus(401);
            resp.getWriter().println(
                    objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(new BaseExceptionDto(badRequestException.getMessage(), 401)));
        } catch (Exception e) {
            resp.setStatus(500);
            log(e.getMessage());
            resp.getWriter()
                    .println(objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(new BaseExceptionDto("Internal Server Error", 500)));
        }
    }
}

