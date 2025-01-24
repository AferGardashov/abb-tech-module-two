package az.abbtech.servlets;

import az.abbtech.dto.LoginDto;
import az.abbtech.exception.BadRequestException;
import az.abbtech.exception.BaseExceptionDto;
import az.abbtech.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private StudentService studentService;

    @Override
    public void init() {
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
        studentService = (StudentService) getServletContext().getAttribute("studentService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            try (InputStream inputStream = req.getInputStream()) {
                int read;
                while ((read = inputStream.read()) != -1) {
                    stringBuilder.append((char) read);
                }
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                LoginDto loginDto = objectMapper.readValue(stringBuilder.toString(), LoginDto.class);

                /*
                  Check user login credentials, and return roles, write roles to sessionAttribute
                 */
                var roles = studentService.getUserAuthorities(loginDto);
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("username", loginDto.userName());
                httpSession.setMaxInactiveInterval(300);
                httpSession.setAttribute("roles", roles);
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

