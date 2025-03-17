package vn.edu.t3h.dao;
import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.model.ProductDTO;

import java.util.List;

public interface ProductRepository {
//
//    List<ProductDTO> findAll();

//    ProductDTO getProductById(int id);


    List<ProductEntity> findAll();
    ProductEntity getProductById(int id);
    public List<ProductEntity> findByCondition(Double price, String bookTitle, String publisher, String categoryName);
    void deleteProduct(int id);
    void addProduct(ProductDTO productDTO);
    void updateProduct(ProductDTO product);
}