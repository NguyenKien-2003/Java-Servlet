package vn.edu.t3h.bookshopmanager.dao.impl;

import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    public static final String GET_ALL_PRODUCTS_AND_CATEGORY = "select product.* , category.name as category_name from product inner join\n" +
            "    product_category on product.id = product_category.productId inner join\n" +
            "    category on product_category.categoryId = category.id";


    @Override
    public List<Product> getAllProduct() {
        List<Product> productsResult = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS_AND_CATEGORY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getFloat(("discount")));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setTotalBuy(resultSet.getInt("totalBuy"));
                product.setAuthor(resultSet.getString("author"));
                product.setPages(resultSet.getInt("pages"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setYearPublishing(resultSet.getString("yearPublishing"));
                product.setDescription(resultSet.getString("description"));
                product.setImageName(resultSet.getString("imageName"));
                product.setShop(resultSet.getBoolean("shop"));
                product.setCreatedAt(resultSet.getString("createdAt"));
                product.setUpdatedAt(resultSet.getString("updatedAt"));
                product.setStartAt(resultSet.getString("startsAt"));
                product.setEndAt(resultSet.getString("endsAt"));
                product.setCreatedBy(resultSet.getString("created_by"));
                product.setCategoryName(resultSet.getString("category_name"));

                productsResult.add(product);


            }
            System.out.println("get product success");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productsResult;
    }
}
