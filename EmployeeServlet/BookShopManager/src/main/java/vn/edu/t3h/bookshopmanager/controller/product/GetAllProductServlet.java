package vn.edu.t3h.bookshopmanager.controller.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.ProductService;
import vn.edu.t3h.bookshopmanager.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAllProduct", value = "/products")
public class GetAllProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String pages = req.getParameter("pages");
        String publisher = req.getParameter("publisher");
        String categoryName = req.getParameter("categoryName");
        String yearPublishing = req.getParameter("yearPublishing");
        String totalBuy = req.getParameter("totalBuy");
        List<Product> products =productService.getAllProduct();
        req.setAttribute("products",products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/product-manager.jsp");
        requestDispatcher.forward(req, resp);
    }
}
