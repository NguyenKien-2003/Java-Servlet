package vn.edu.t3h.bookshopmanager.dao.impl;

import vn.edu.t3h.bookshopmanager.dao.CategoryDao;
import vn.edu.t3h.bookshopmanager.model.Category;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    public static final String GET_ALL_CATEGORY = "select * from category";
    public static final String GET_PRODUCT_BY_CATEGORY_ID = "SELECT product.*, category.name AS category_name, category.id AS category_id \n" +
            "FROM product \n" +
            "INNER JOIN product_category ON product.id = product_category.productId \n" +
            "INNER JOIN category ON product_category.categoryId = category.id\n" +
            "WHERE category.id = ?;\n";
    @Override
    public List<Category> getAllCategory() {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setImageName(resultSet.getString("imageName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return categories;
    }

    @Override
    public List<Product> getProductByCategoryId(int id) {
        Connection connection = null;
        List<Product> productOfCategory = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_CATEGORY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setDescription(resultSet.getString("description"));
                product.setImageName(resultSet.getString("imageName"));
                productOfCategory.add(product);
                System.out.println(product.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }return productOfCategory;
    }

    @Override
    public Category getCategoryById(int id) {
        Connection connection = null;
        Category category = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return category;
    }
}
