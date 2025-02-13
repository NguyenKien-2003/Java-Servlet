package vn.edu.t3h.bookshopmanager.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookshopmanager.service.ProductService;
import vn.edu.t3h.bookshopmanager.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/delete-product")
public class DeleteProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductServiceImpl(productDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProductById(id);
        resp.sendRedirect("/admin/products");
    }
}
