package vn.edu.t3h.bookshopmanager.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.UserDao;
import vn.edu.t3h.bookshopmanager.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookshopmanager.model.User;
import vn.edu.t3h.bookshopmanager.service.UserService;
import vn.edu.t3h.bookshopmanager.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManager", value = "/user-manager")
public class UserManagerServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        List<User> users = userService.getUsersByRole(role);
        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/user-manager.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
