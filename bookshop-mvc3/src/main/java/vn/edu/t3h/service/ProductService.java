package vn.edu.t3h.service;

import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.model.ProductDTO;

import java.util.List;

public interface ProductService {

//    List<ProductDto> getProducts();
//    ProductDto getProductById(int id);
//    void addProduct(ProductDto product);
//    void updateProduct(ProductDto product);
//    void deleteProduct(int id);
List<ProductEntity> getProducts();
ProductEntity getProductById(int id);

public List<ProductDTO> findByCondition(Double price, String bookTitle,String publisher,String categoryName);
}