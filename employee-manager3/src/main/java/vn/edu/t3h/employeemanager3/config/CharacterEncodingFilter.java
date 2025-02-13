package vn.edu.t3h.employeemanager3.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter(urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
    private  String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if(encodingParam!=null){
            encoding = encodingParam;
        }

        ServletContext context = filterConfig.getServletContext();
        //dat base url vao ServletContext cho toan bo ung dung
        context.setAttribute("baseUrl", ConfigInit.BASE_URL);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
