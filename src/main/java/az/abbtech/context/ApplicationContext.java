package az.abbtech.context;

import az.abbtech.repository.StudentListRepositoryImpl;
import az.abbtech.service.StudentService;
import az.abbtech.service.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StudentService studentService = new StudentServiceImpl(new StudentListRepositoryImpl());
        ObjectMapper objectMapper = new ObjectMapper();
        sce.getServletContext().setAttribute("studentService", studentService);
        sce.getServletContext().setAttribute("objectMapper", objectMapper);
    }
}
