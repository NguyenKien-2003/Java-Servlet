package vn.edu.t3h.bookshopmanager.dao;

import vn.edu.t3h.bookshopmanager.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();
    List<Product> getProductByUser(String user);
    int deleteProduct(int id);
    Product getProductById(int id);
    int addProduct(Product product);
    int updateProduct(Product product);
    List<Product> findByCondition(String name, String author, String publisher, Integer categoryId, Integer yearPublishing);

}
