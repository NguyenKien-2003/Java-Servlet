package vn.edu.t3h.dao;
import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.model.ProductDTO;

import java.util.List;

public interface ProductRepository {
//
//    List<ProductDTO> findAll();
//    int addProduct(ProductDTO product);
//    ProductDTO getProductById(int id);
//    void updateProduct(ProductDTO product);
//    void deleteProduct(int id);
    List<ProductEntity> findAll();
    ProductEntity getProductById(int id);
    public List<ProductEntity> findByCondition(Double price, String bookTitle, String publisher, String categoryName);

}