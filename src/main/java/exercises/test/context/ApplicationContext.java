package exercises.test.context;

import exercises.test.repository.StudentListRepositoryImpl;
import exercises.test.service.StudentService;
import exercises.test.service.StudentServiceImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StudentService studentService = new StudentServiceImpl(new StudentListRepositoryImpl());
        sce.getServletContext().setAttribute("studentService", studentService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

}
