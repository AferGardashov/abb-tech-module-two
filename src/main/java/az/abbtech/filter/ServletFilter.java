package az.abbtech.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "servletFilter", urlPatterns = "/students/*")
public class ServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        boolean isAuthenticated = false;

        // Check if the user is authenticated
        if (session != null && session.getAttribute("username") != null) {
            List<String> userRoles = (List<String>) session.getAttribute("roles");

            /*
               You can add your custom logic here
             */
            // Check if the user has the SUPER_STUDENTS role
            if (userRoles != null && userRoles.contains("SUPER_STUDENTS")) {
                isAuthenticated = true;

                // Check the HTTP method for DELETE, POST, PUT (Delete, create, update student)
                String method = httpRequest.getMethod();
                if (method.equalsIgnoreCase("DELETE")
                        || method.equalsIgnoreCase("POST")
                        || method.equalsIgnoreCase("PUT")) {

                    // Allow the request for users with the SUPER_STUDENTS role
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        // If the user is not authorized, send a 403 response
        if (!isAuthenticated) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter()
                    .write("{\"error\":\"Access denied. Only SUPER_STUDENTS can perform this operation.\"}");
        }
    }

}
