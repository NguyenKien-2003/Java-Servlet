package vn.edu.t3h.bookshopmanager.service;

import vn.edu.t3h.bookshopmanager.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    int deleteProductById(int id);

    int addProduct(Product product);

    int updateProduct(Product product);

    Product getProductById(int id);

    public List<Product> findByFilter(String name, String author, String publisher, Integer categoryId, Integer yearPublishing);

    List<Product> getProductByUser(String user);
}