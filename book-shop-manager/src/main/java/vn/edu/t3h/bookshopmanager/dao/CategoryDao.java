package vn.edu.t3h.bookshopmanager.dao;

import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.model.Product;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategory();
    List<Product> getProductByCategoryId(int Id);
    Category getCategoryById(int id);
}
