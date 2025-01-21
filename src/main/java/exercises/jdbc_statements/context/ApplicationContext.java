package exercises.jdbc_statements.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercises.jdbc_statements.repository.StudentDBRepositoryImpl;
import exercises.jdbc_statements.service.StudentService;
import exercises.jdbc_statements.service.StudentServiceImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StudentService studentService = new StudentServiceImpl(new StudentDBRepositoryImpl());
        ObjectMapper objectMapper = new ObjectMapper();
        sce.getServletContext().setAttribute("studentService", studentService);
        sce.getServletContext().setAttribute("objectMapper", objectMapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

}
