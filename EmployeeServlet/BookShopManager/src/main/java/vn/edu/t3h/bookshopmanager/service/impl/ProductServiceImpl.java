package vn.edu.t3h.bookshopmanager.service.impl;

import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
