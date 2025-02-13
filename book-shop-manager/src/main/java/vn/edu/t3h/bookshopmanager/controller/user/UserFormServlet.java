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

@WebServlet(name = "UserForm", value = "/form-user")
public class UserFormServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId =req.getParameter("userId");
        User user = null;
        if (userId != null && !userId.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(userId);
                user = userService.getUserById(id);
                if (user == null) {
                    req.setAttribute("errorMessage", "User không tồn tại!");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "ID user không hợp lệ!");
            }
        }
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/form-user.jsp");
        requestDispatcher.forward(req, resp);
    }
}
