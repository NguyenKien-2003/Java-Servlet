package vn.edu.t3h.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import vn.edu.t3h.dao.ProductRepository;
import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.t3h.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    private ProductRepository productRepository;
//
//    @Override
//    public List<ProductDto> getProducts() {
//        return productRepository.findAll();
//    }
//
//
//    @Override
//    public ProductDto getProductById(int id) {
//        return productRepository.getProductById(id);
//    }
//
//    @Override
//    public void addProduct(ProductDto product) {
//        productRepository.addProduct(product);
//    }
//
//    @Override
//    public void updateProduct(ProductDto product) {
//        productRepository.updateProduct(product);
//    }
//
//    @Override
//    public void deleteProduct(int id) {
//        productRepository.deleteProduct(id);
//    }
    @Autowired
    @Qualifier("productHibernateRepositoryImpl")
    private ProductRepository productRepository;

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void addProduct(ProductDTO product) {
        productRepository.addProduct(product);
    }

    @Override
    public void updateProduct(ProductDTO product) {
        productRepository.updateProduct(product);
    }

    @Override
    public List<ProductDTO> findByCondition(Double price, String bookTitle, String publisher, String categoryName) {
        List<ProductEntity> productEntities =  productRepository.findByCondition(price, bookTitle, publisher, categoryName);
        List<ProductDTO> productDTOS = productEntities.stream().map(data -> {
            ProductDTO productDTO = new ProductDTO(data.getId(),
                    data.getBookTitle(), data.getAuthor(), data.getPageCount(),
                    data.getPublisher(), data.getPublicationYear(), data.getGenre(),
                    data.getPrice(), data.getDiscount(), data.getStockQuantity(),
                    data.getDescription(),data.getImage());
            return productDTO;
        }).collect(Collectors.toList());
        return productDTOS;
    }


}