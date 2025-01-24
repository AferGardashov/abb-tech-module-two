package test.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.dto.LoginDto;
import test.exeptions.BadRequestException;
import test.exeptions.BaseExceptionDto;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = "Name";
        String password = "Password";
        try {
            StringBuilder builder = new StringBuilder();
            try (InputStream in = req.getInputStream()) {
                int read;
                while ((read = in.read()) != -1) {
                    builder.append((char) read);
                }
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            LoginDto loginDto = objectMapper.readValue(builder.toString(), LoginDto.class);

            if(loginDto.username().equals(username) && loginDto.password().equals(password)) {
                Cookie cooke = new Cookie("username", loginDto.username());
                cooke.setPath("/");
                cooke.setMaxAge(3000);
                cooke.setHttpOnly(true);
//                cooke.setSecure(true);
                resp.addCookie(cooke);
            }
            else{
                throw new BadRequestException("Invalid username or password");
            }

        } catch (Exception exception) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR)));
        }
    }
}