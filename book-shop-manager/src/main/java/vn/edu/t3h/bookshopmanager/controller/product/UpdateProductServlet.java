package vn.edu.t3h.bookshopmanager.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

@WebServlet(name = "UpdateProducts", value = "/update-product")
public class UpdateProductServlet extends HttpServlet {
    private ProductService productService;
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("productId");
        String name = req.getParameter("add-book-title");
        String author = req.getParameter("add-book-author");
        Integer pages = Integer.parseInt(req.getParameter("add-book-pages"));
        String publisher = req.getParameter("add-book-publisher");
        String yearPublishing = req.getParameter("add-book-yearPublishing");
        Integer categoryId = Integer.parseInt(req.getParameter("add-book-category"));
        Integer price = Integer.parseInt(req.getParameter("add-book-price"));
        Float discount = Float.parseFloat(req.getParameter("add-book-discount"));
        Integer quantity = Integer.parseInt(req.getParameter("add-book-quantity"));
        String description = req.getParameter("add-book-description");
        String imageName = req.getParameter("add-book-imageName");
        HttpSession session = req.getSession(false); // Lấy session hiện tại, không tạo mới nếu chưa có
        System.out.println("sestion :   " + session.getId());
        System.out.println(session.getAttribute("username"));
        String username = (String) session.getAttribute("username");

        Product product = new Product();
        product.setId(Integer.valueOf(id));
        product.setName(name);
        product.setAuthor(author);
        product.setPages(pages);
        product.setPublisher(publisher);
        product.setYearPublishing(Integer.valueOf(yearPublishing));
        product.setCategoryId(categoryId);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setImageName(imageName);
        product.setCreatedBy(username);
        productService.updateProduct(product);
        if(userService.getRoleByUsername(product.getCreatedBy()).equals("ADMIN")) {
            resp.sendRedirect("/admin/products");
            return;
        }
        if(userService.getRoleByUsername(product.getCreatedBy()).equals("EMPLOYEE")) {
            resp.sendRedirect("/employee/products");
            return;
        }
        resp.sendRedirect("/");

    }
}
