package vn.edu.t3h.bookshopmanager.controller.auth;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.impl.UserDaoImpl;;
import vn.edu.t3h.bookshopmanager.service.UserService;
import vn.edu.t3h.bookshopmanager.service.impl.UserServiceImpl;
import vn.edu.t3h.bookshopmanager.utils.Constants;
import vn.edu.t3h.bookshopmanager.utils.SessionUtil;

import java.io.IOException;
@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserDaoImpl());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().contains("/logout")){
            //deleted session when logout
            req.getSession().invalidate();
            resp.sendRedirect("/");

        }else {
            String message = req.getParameter("message");
            req.setAttribute("message", message);
            req.getRequestDispatcher("auth/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.getSession().setAttribute("username", username);
        System.out.println(username);
        String urlRedirect = userService.login(username, password,req);
        System.out.println("Session ID after login: " + req.getSession().getId());
        System.out.println("User stored in session: " + SessionUtil.getValue(req, SessionUtil.SESSION_ID_CURRENT_USER));
        resp.sendRedirect(urlRedirect);
    }
}