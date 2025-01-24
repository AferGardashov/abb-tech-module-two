package authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import authentication.exeptions.BadRequestException;
import authentication.exeptions.BaseExceptionDto;

import java.io.IOException;

@WebFilter(filterName = "ServletFilter", urlPatterns = "/students/*")
public class ServletFilter implements Filter {

    ObjectMapper objectMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        objectMapper = new ObjectMapper();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //Session based authentication
        if (request.getSession().getAttribute("username") != null
                && request.getSession().getAttribute("username")
                .equals("cavidan.hatamov@gmail.com")) {
            filterChain.doFilter(request, servletResponse);
        }


        //Cookie based authentication
        Cookie[] cookies = request.getCookies();
        boolean authenticated = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username") && cookie.getValue().equals("Name")) {
                    //check username and password in DB.
                    authenticated = true;
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
        if (!authenticated) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("application/json");
            response.setStatus(403);
            try {
                throw new BadRequestException("Forbidden");
            } catch (BadRequestException exception) {
                response.getWriter().println(objectMapper.writerWithDefaultPrettyPrinter().
                        writeValueAsString(new BaseExceptionDto(exception.getMessage(), 403)));
            }

        }
    }
}
