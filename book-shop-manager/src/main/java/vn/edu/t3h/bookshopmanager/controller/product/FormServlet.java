package vn.edu.t3h.bookshopmanager.controller.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.CategoryDao;
import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.dao.impl.CategoryDaoImpl;
import vn.edu.t3h.bookshopmanager.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.CategoryService;
import vn.edu.t3h.bookshopmanager.service.ProductService;
import vn.edu.t3h.bookshopmanager.service.impl.CategoryServiceImpl;
import vn.edu.t3h.bookshopmanager.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FormAddProduct", value = "/form-product")
public class FormServlet extends HttpServlet {
    CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryService = new CategoryServiceImpl(categoryDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        Product product = null;
        ProductService productService;


        if (productId != null && !productId.isEmpty()) {
            try {
                int id = Integer.parseInt(productId);
                ProductDao productDao = new ProductDaoImpl();
                productService = new ProductServiceImpl(productDao);
                product = productService.getProductById(id);
                System.out.println(product.getId());
                System.out.println(product.getCategoryId());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        List<Category> categories = categoryService.getAllCategory();
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);

        RequestDispatcher view = req.getRequestDispatcher("admin/add-product.jsp");
        view.forward(req, resp);
    }

}
