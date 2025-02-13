package vn.edu.t3h.bookshopmanager.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.model.User;
import vn.edu.t3h.bookshopmanager.utils.Constants;

import java.io.IOException;

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
        User currentUser = (User) request.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);
        if (currentUser != null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Cookie userCookie = new Cookie("username", currentUser.getUsername());
            userCookie.setMaxAge(60 * 60); // Cookie có hiệu lực trong 1 giờ
            response.addCookie(userCookie); // Thêm cookie vào response
            request.setAttribute("username", currentUser.getUsername());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
