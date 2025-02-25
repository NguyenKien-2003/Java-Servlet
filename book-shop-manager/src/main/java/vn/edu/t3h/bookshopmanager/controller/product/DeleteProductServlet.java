package vn.edu.t3h.bookshopmanager.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.dao.UserDao;
import vn.edu.t3h.bookshopmanager.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookshopmanager.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.ProductService;
import vn.edu.t3h.bookshopmanager.service.UserService;
import vn.edu.t3h.bookshopmanager.service.impl.ProductServiceImpl;
import vn.edu.t3h.bookshopmanager.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/delete-product")
public class DeleteProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    private ProductService productService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductServiceImpl(productDao);
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductById(id);
        String role = userService.getRoleByUsername(product.getCreatedBy());
        productService.deleteProductById(id);
        if(userService.getRoleByUsername(role).equals("ADMIN")) {
            resp.sendRedirect("/admin/products");
            return;
        }
        if(userService.getRoleByUsername(role).equals("EMPLOYEE")) {
            resp.sendRedirect("/employee/products");
            return;
        }
        resp.sendRedirect("/");
    }
}
