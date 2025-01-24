package az.abbtech.servlets;

import az.abbtech.dto.RegStudentDto;
import az.abbtech.exception.BadRequestException;
import az.abbtech.exception.BaseExceptionDto;
import az.abbtech.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;


@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private StudentService studentService;
    private ObjectMapper objectMapper;


    @Override
    public void init() {
        studentService = (StudentService) getServletContext().getAttribute("studentService");
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            try (InputStream inputStream = req.getInputStream()) {
                int read;
                while ((read = inputStream.read()) != -1) {
                    stringBuilder.append((char) read);
                }
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                RegStudentDto regStudentDto = objectMapper.readValue(stringBuilder.toString(), RegStudentDto.class);
                studentService.registerStudent(regStudentDto);
            }
        } catch (BadRequestException badRequestException) {
            resp.setStatus(400);
            resp.getWriter().println(
                    objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(new BaseExceptionDto(badRequestException.getMessage(), 400)));
        } catch (Exception e) {
            resp.setStatus(500);
            log(e.getMessage());
            resp.getWriter()
                    .println(objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(new BaseExceptionDto("Internal Server Error", 500)));
        }
    }
}

