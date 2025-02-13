package vn.edu.t3h.employeemanager3.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager3.dao.UserDao;
import vn.edu.t3h.employeemanager3.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemanager3.model.RoleModel;
import vn.edu.t3h.employeemanager3.model.UserModel;
import vn.edu.t3h.employeemanager3.service.RoleService;
import vn.edu.t3h.employeemanager3.service.impl.RoleServiceImpl;
import vn.edu.t3h.employeemanager3.utils.Constants;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private RoleService roleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        roleService = new RoleServiceImpl(new RoleDaoImpl());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String urlRedirect = "";
        if(uri.startsWith("/employee")){
            UserModel currentUser = (UserModel) request.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);
            if(currentUser != null){
                RoleModel roleCurrentUser = roleService.getRoleById(currentUser.getRoleId());
                if(roleCurrentUser != null && roleCurrentUser.getCode().equalsIgnoreCase(Constants.ROLE.ROLE_ADMIN.name())){
                    filterChain.doFilter(request, response);
                }else {
                    response.sendRedirect("login?message="+Constants.PERMISSON_DENIED);
                }
            }else {
                response.sendRedirect("login?message="+Constants.DONT_LOGIN);
            }
            //kiem tra roll

        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
