package az.abbtech.servlets;

import az.abbtech.dto.StudentDto;
import az.abbtech.exception.BadRequestException;
import az.abbtech.exception.BaseExceptionDto;
import az.abbtech.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    private ObjectMapper objectMapper;


    @Override
    public void init() {
        studentService = (StudentService) getServletContext().getAttribute("studentService");
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Optional<String> optionalStudentId = Optional.ofNullable(req.getParameter("id"));
        if (optionalStudentId.isEmpty()) {
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(studentService.findAll()));
        } else {
            resp.getWriter().println(studentService.findById(Long.parseLong(optionalStudentId.get())));
        }
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
                StudentDto studentDto = objectMapper.readValue(stringBuilder.toString(), StudentDto.class);
                studentService.save(studentDto);
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

