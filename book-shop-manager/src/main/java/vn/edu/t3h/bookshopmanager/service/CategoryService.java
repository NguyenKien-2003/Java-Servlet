package vn.edu.t3h.bookshopmanager.service;

import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.model.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    List<Product> getProductByCategoryId(int id);
    Category getCategoryById(int id);
}
