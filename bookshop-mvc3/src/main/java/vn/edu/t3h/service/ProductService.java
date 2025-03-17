package vn.edu.t3h.service;

import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.model.ProductDTO;

import java.util.List;

public interface ProductService {

//    List<ProductDto> getProducts();
//    ProductDto getProductById(int id);

//
void deleteProduct(int id);
List<ProductEntity> getProducts();
ProductEntity getProductById(int id);
void addProduct(ProductDTO product);
void updateProduct(ProductDTO product);
public List<ProductDTO> findByCondition(Double price, String bookTitle,String publisher,String categoryName);
}