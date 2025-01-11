package homeworks.homework16.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.homework16.dto.StudentDto;
import homeworks.homework16.exeptions.BadRequestException;
import homeworks.homework16.exeptions.BaseExceptionDto;
import homeworks.homework16.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "studentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        studentService = (StudentService) getServletContext().getAttribute("studentService");
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            if (id == null) {
                resp.getWriter().println(studentService.findAll());
            } else {
                resp.getWriter().println(studentService.findById(Integer.valueOf(id)));
            }
        } catch (Exception exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            StudentDto studentDto = objectMapper.readValue(builder.toString(), StudentDto.class);
            studentService.save(studentDto);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString("Saved successfully"));
        } catch (BadRequestException exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST)));
        } catch (Exception exception) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR)));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            StringBuilder builder = new StringBuilder();
            try (InputStream in = req.getInputStream()) {
                int read;
                while ((read = in.read()) != -1) {
                    builder.append((char) read);
                }
            }
            StudentDto studentDto = objectMapper.readValue(builder.toString(), StudentDto.class);
            studentService.removeById(studentDto.id());
            studentService.save(studentDto);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST)));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            if (id != null) {
                studentService.removeById(Integer.valueOf(id));
            }
            else{
                throw new BadRequestException("Student not found with given id");
            }
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(new BaseExceptionDto(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST)));
        }
    }
}
