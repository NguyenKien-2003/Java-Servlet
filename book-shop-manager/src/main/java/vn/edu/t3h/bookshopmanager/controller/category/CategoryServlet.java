package vn.edu.t3h.bookshopmanager.controller.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.CategoryDao;
import vn.edu.t3h.bookshopmanager.dao.impl.CategoryDaoImpl;
import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.CategoryService;
import vn.edu.t3h.bookshopmanager.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryService = new CategoryServiceImpl(categoryDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");

        if (categoryId != null) {
            int id = Integer.parseInt(categoryId);
            Category category = categoryService.getCategoryById(id);
            List<Product> products = categoryService.getProductByCategoryId(id);

            req.setAttribute("category", category);
            req.setAttribute("products-of-category", products);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/category.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("home");
        }
    }
}
