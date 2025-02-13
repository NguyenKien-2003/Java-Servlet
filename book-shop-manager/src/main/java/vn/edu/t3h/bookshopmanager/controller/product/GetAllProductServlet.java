package vn.edu.t3h.bookshopmanager.controller.product;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.ProductService;
import vn.edu.t3h.bookshopmanager.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/products", "/employee/products"})
public class GetAllProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        resp.setContentType("application/json");
////        resp.setCharacterEncoding("UTF-8");
//        List<Product> products =productService.getAllProduct();
//        req.setAttribute("products", products);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/product-manager.jsp");
//        requestDispatcher.forward(req, resp);
////        String json = new Gson().toJson(products);
////        PrintWriter out = resp.getWriter();
//

    /// /        out.print(json);
    /// /        out.flush();
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy tham số từ request
        if (req.getRequestURI().equals("/employee/products")) {
            HttpSession session = req.getSession(false); // Lấy session hiện tại, không tạo mới nếu chưa có
            System.out.println("sestion :   " + session.getId());
            System.out.println(session.getAttribute("username"));
            String username = (String) session.getAttribute("username");
            if (session != null && username != null) {
                List<Product> employeeProduct = productService.getProductByUser(username);
                System.out.println("list :" + employeeProduct.toString());
                req.setAttribute("employeeProduct", employeeProduct);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employee/product-manager-by-user.jsp");
                requestDispatcher.forward(req, resp);


            } else {
                System.out.println("No user logged in.");
            }
        } else {
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            String publisher = req.getParameter("publisher");
            String categoryIdStr = req.getParameter("categoryId");
            String yearPublishingStr = req.getParameter("yearPublishing");

            Integer categoryId = (categoryIdStr != null && !categoryIdStr.isEmpty()) ? Integer.parseInt(categoryIdStr) : null;
            Integer yearPublishing = (yearPublishingStr != null && !yearPublishingStr.isEmpty()) ? Integer.parseInt(yearPublishingStr) : null;
            List<Product> products = productService.findByFilter(name, author, publisher, categoryId, yearPublishing);
            req.setAttribute("products", products);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/product-manager.jsp");
            requestDispatcher.forward(req, resp);

        }
    }

}
