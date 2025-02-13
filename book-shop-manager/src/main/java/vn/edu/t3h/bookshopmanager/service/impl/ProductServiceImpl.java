package vn.edu.t3h.bookshopmanager.service.impl;

import com.mysql.cj.util.StringUtils;
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

    @Override
    public int deleteProductById(int id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }


    @Override
    public List<Product> findByFilter(String name, String author, String publisher, Integer categoryId, Integer yearPublishing) {

        // Kiểm tra chuỗi rỗng, nếu rỗng thì gán null
        if (StringUtils.isNullOrEmpty(name)) {
            name = null;
        }
        if (StringUtils.isNullOrEmpty(author)) {
            author = null;
        }
        if (StringUtils.isNullOrEmpty(publisher)) {
            publisher = null;
        }

        // Kiểm tra giá trị số, nếu là 0 thì gán null (vì categoryId và yearPublishing là Integer)
        if (categoryId != null && categoryId == 0) {
            categoryId = null;
        }
        if (yearPublishing != null && yearPublishing == 0) {
            yearPublishing = null;
        }

        // Gọi DAO để tìm sản phẩm dựa trên điều kiện
        List<Product> products = productDao.findByCondition(name, author, publisher, categoryId, yearPublishing);

        return products;
    }

    @Override
    public List<Product> getProductByUser(String user) {
        List<Product> employeeProducts = productDao.getProductByUser(user);
        return employeeProducts;
    }

}
