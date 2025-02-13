package vn.edu.t3h.bookshopmanager.controller.product;

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

@WebServlet(name = "UpdateProducts", value = "/update-product")
public class UpdateProductServlet extends HttpServlet {
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
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
        productService.updateProduct(product);
        resp.sendRedirect("/admin/products");

    }
}
