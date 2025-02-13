package vn.edu.t3h.bookshopmanager.service.impl;

import vn.edu.t3h.bookshopmanager.dao.CategoryDao;
import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    @Override
    public List<Product> getProductByCategoryId(int id) {
        return categoryDao.getProductByCategoryId(id);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }
}
