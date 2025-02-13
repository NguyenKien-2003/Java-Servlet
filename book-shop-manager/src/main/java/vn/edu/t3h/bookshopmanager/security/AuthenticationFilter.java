package vn.edu.t3h.bookshopmanager.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookshopmanager.model.User;
import vn.edu.t3h.bookshopmanager.service.UserService;
import vn.edu.t3h.bookshopmanager.service.impl.UserServiceImpl;
import vn.edu.t3h.bookshopmanager.utils.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class   AuthenticationFilter implements Filter {
    private UserService userService;
    private final Map<String, String> roleMapping = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = new UserServiceImpl(new UserDaoImpl());

        // Định nghĩa vai trò cho các đường dẫn
        roleMapping.put("/admin", Constants.ROLE.ADMIN.name());
        roleMapping.put("/employee", Constants.ROLE.EMPLOYEE.name());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        for (Map.Entry<String, String> entry : roleMapping.entrySet()) {
            if (uri.startsWith(entry.getKey())) {
                if (!isAuthorized(request, entry.getValue())) {
                    response.sendRedirect(request.getContextPath() + "/login?message=" + Constants.PERMISSON_DENIED);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isAuthorized(HttpServletRequest request, String requiredRole) {
        User currentUser = (User) request.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);
        if (currentUser == null) {
            return false;
        }
        return currentUser.getRole() != null && currentUser.getRole().equalsIgnoreCase(requiredRole);
    }

    @Override
    public void destroy() {
    }
}
