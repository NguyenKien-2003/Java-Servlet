//package vn.edu.t3h.controller;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.*;
//import vn.edu.t3h.model.ProductDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import vn.edu.t3h.service.ProductService;
//
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/")
//@Scope("prototype")
//public class ProductController {
//
//     //@Autowired
//    private ProductService productService;
//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//
//    @GetMapping("/products")
//    public String products(Model model) {
//        List<ProductDTO> products = productService.getProducts();
////        Gson gson = new Gson();
//        // convert object java to String json
////        String jsonProjection = gson.toJson(products);
//        model.addAttribute("products", products);
//        return "cms/product-manager";
//    }
//
//    @GetMapping({"/add", "/edit/{id}"})
//    public String showForm(@PathVariable(value = "id", required = false) Integer id, Model model) {
//        ProductDTO product = (id != null) ? productService.getProductById(id) : new ProductDTO();
//        model.addAttribute("product", product);
//        return "cms/product-form"; // Sử dụng chung file JSP
//    }
//
//    @PostMapping("/save")
//    public String saveProduct(@ModelAttribute ProductDTO product) {
//        if (product.getId() != null) {
//            productService.updateProduct(product);
//        } else {
//            productService.addProduct(product);
//        }
//        return "redirect:/products";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateProduct(@PathVariable("id") int id, @ModelAttribute ProductDTO product) {
//        product.setId(id);
//        productService.updateProduct(product);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable("id") int id) {
//        productService.deleteProduct(id);
//        return "redirect:/products";
//    }
//
//}