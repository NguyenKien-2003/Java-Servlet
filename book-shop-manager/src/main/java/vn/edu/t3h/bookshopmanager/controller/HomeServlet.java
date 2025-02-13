package vn.edu.t3h.bookshopmanager.controller.category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.t3h.bookshopmanager.dao.CategoryDao;
import vn.edu.t3h.bookshopmanager.dao.impl.CategoryDaoImpl;
import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.service.CategoryService;
import vn.edu.t3h.bookshopmanager.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryService = new CategoryServiceImpl(categoryDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Category> categories = categoryService.getAllCategory();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/home.jsp");
        dispatcher.forward(req, resp);
    }
}
