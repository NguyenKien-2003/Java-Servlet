//package vn.edu.t3h.employeemanager3.controller;
//
//import com.oracle.wls.shaded.org.apache.bcel.classfile.Constant;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//@WebServlet(urlPatterns = {"/login", "/logout"})
//public class LoginServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.getRequestURI().contains("/logout")){
//            //deleted session when logout
//            req.getSession().removeAttribute(Constants.SESSION_ID_CURRENT_USER);
//            resp.sendRedirect("/home");
//        }
//        String message = req.getParameter("message");
//        req.setAttribute("message", message);
//        req.getRequestDispatcher("login.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
