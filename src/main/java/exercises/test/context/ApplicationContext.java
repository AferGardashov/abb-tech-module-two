package exercises.test.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercises.test.repository.StudentDBRepositoryImpl;
import exercises.test.service.StudentService;
import exercises.test.service.StudentServiceImpl;
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
