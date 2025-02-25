package vn.edu.t3h.bookshopmanager.dao.impl;

import vn.edu.t3h.bookshopmanager.dao.ProductDao;
import vn.edu.t3h.bookshopmanager.model.Product;
import vn.edu.t3h.bookshopmanager.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    public static final String GET_ALL_PRODUCTS_AND_CATEGORY = "select product.* , category.name as category_name,category.id as category_id from product inner join\n" +
            "    product_category on product.id = product_category.productId inner join\n" +
            "    category on product_category.categoryId = category.id";

    public static final String DELETE_PRODUCT_BY_ID="DELETE FROM product WHERE id = ?;";
    public static final String UPDATE_PRODUCT = "UPDATE product set name = ?, price=?, discount=?,quantity=?,author=?,pages=?,publisher=?,yearPublishing=?,description=?,imageName=? ,updatedAt=now() WHERE id=?";
    public static final String UPDATE_PRODUCT_CATEGORY = "UPDATE product_category set categoryId = ? where productId = ?";
    public static final String GET_PRODUCT_BY_USER = "SELECT product.*, category.name AS category_name, category.id AS category_id FROM product INNER JOIN product_category ON product.id = product_category.productId INNER JOIN category ON product_category.categoryId = category.id WHERE product.created_by=?";

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
                product.setYearPublishing(Integer.valueOf(String.valueOf(resultSet.getInt("yearPublishing"))));
                product.setDescription(resultSet.getString("description"));
                product.setImageName(resultSet.getString("imageName"));
                product.setShop(resultSet.getBoolean("shop"));
                product.setCreatedAt(resultSet.getString("createdAt"));
                product.setUpdatedAt(resultSet.getString("updatedAt"));
                product.setStartAt(resultSet.getString("startsAt"));
                product.setEndAt(resultSet.getString("endsAt"));
                product.setCreatedBy(resultSet.getString("created_by"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setCategoryName(resultSet.getString("category_name"));
                productsResult.add(product);

            }
            productsResult.sort(Comparator.comparingInt(Product::getId));
            System.out.println("get product success");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productsResult;
    }

    @Override
    public List<Product> getProductByUser(String user) {
        List<Product> productsResult = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_USER);
            statement.setString(1, user);
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
                product.setYearPublishing(Integer.valueOf(String.valueOf(resultSet.getInt("yearPublishing"))));
                product.setDescription(resultSet.getString("description"));
                product.setImageName(resultSet.getString("imageName"));
                product.setShop(resultSet.getBoolean("shop"));
                product.setCreatedAt(resultSet.getString("createdAt"));
                product.setUpdatedAt(resultSet.getString("updatedAt"));
                product.setStartAt(resultSet.getString("startsAt"));
                product.setEndAt(resultSet.getString("endsAt"));
                product.setCreatedBy(resultSet.getString("created_by"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setCategoryName(resultSet.getString("category_name"));
                productsResult.add(product);

            }
            productsResult.sort(Comparator.comparingInt(Product::getId));
            System.out.println("get product success by " + user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productsResult;
    }


    @Override
    public int deleteProduct(int id) {
        int rowsAffected = 0;
        Connection connection = null;

        try{
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if( connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return rowsAffected;
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT p.*, c.id as category_id, c.name as category_name " +
                "FROM product p " +
                "INNER JOIN product_category pc ON p.id = pc.productId " +
                "INNER JOIN category c ON pc.categoryId = c.id " +
                "WHERE p.id = ?";
        Product product = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getFloat("discount"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setTotalBuy(resultSet.getInt("totalBuy"));
                product.setAuthor(resultSet.getString("author"));
                product.setPages(resultSet.getInt("pages"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setYearPublishing(resultSet.getInt("yearPublishing"));
                product.setDescription(resultSet.getString("description"));
                product.setImageName(resultSet.getString("imageName"));
                product.setShop(resultSet.getBoolean("shop"));
                product.setCreatedAt(resultSet.getString("createdAt"));
                product.setUpdatedAt(resultSet.getString("updatedAt"));
                product.setStartAt(resultSet.getString("startsAt"));
                product.setEndAt(resultSet.getString("endsAt"));
                product.setCreatedBy(resultSet.getString("created_by"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setCategoryName(resultSet.getString("category_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public int addProduct(Product product) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement psProduct = null;
        PreparedStatement psCategory = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);  // Bắt đầu transaction

            // Câu lệnh SQL thêm sản phẩm vào bảng product
            String sqlProduct = "INSERT INTO product (name, price, discount, quantity, totalBuy, author, pages, publisher, yearPublishing, description, imageName, shop, createdAt, updatedAt, startsAt, endsAt, created_by) " +
                    "VALUES (?, ?, ?, ?, 0, ?, ?, ?, ?, ?, ?, true, now(), now(), now(), null, ?)";

            psProduct = connection.prepareStatement(sqlProduct, PreparedStatement.RETURN_GENERATED_KEYS);
            psProduct.setString(1, product.getName());
            psProduct.setInt(2, product.getPrice());
            psProduct.setFloat(3, product.getDiscount());
            psProduct.setInt(4, product.getQuantity());
            psProduct.setString(5, product.getAuthor());
            psProduct.setInt(6, product.getPages());
            psProduct.setString(7, product.getPublisher());
            psProduct.setInt(8, product.getYearPublishing());
            psProduct.setString(9, product.getDescription());
            psProduct.setString(10, product.getImageName());
            psProduct.setString(11,product.getCreatedBy());

            psProduct.executeUpdate();

            // Lấy ID của sản phẩm vừa chèn
            rs = psProduct.getGeneratedKeys();
            int lastProductId = 0;
            if (rs.next()) {
                lastProductId = rs.getInt(1);
            }

            // Chèn vào bảng product_category
            String sqlCategory = "INSERT INTO product_category (productId, categoryId) VALUES (?, ?)";
            psCategory = connection.prepareStatement(sqlCategory);
            psCategory.setInt(1, lastProductId);
            psCategory.setInt(2, product.getCategoryId());

            psCategory.executeUpdate();

            // Commit transaction
            connection.commit();
            rowsAffected = 1;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();  // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psProduct != null) psProduct.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psCategory != null) psCategory.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return rowsAffected;
    }

    @Override
    public int updateProduct(Product product) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement psProduct = null;
        PreparedStatement psCategory = null;
        try{
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            psProduct = connection.prepareStatement(UPDATE_PRODUCT);
            psProduct.setString(1, product.getName());
            psProduct.setInt(2, product.getPrice());
            psProduct.setFloat(3, product.getDiscount());
            psProduct.setInt(4, product.getQuantity());
            psProduct.setString(5, product.getAuthor());
            psProduct.setInt(6, product.getPages());
            psProduct.setString(7, product.getPublisher());
            psProduct.setInt(8, product.getYearPublishing());
            psProduct.setString(9, product.getDescription());
            psProduct.setString(10, product.getImageName());
            psProduct.setInt(11, product.getId());
            psProduct.executeUpdate();

            psCategory = connection.prepareStatement(UPDATE_PRODUCT_CATEGORY);
            psCategory.setInt(1, product.getCategoryId());
            psCategory.setInt(2, product.getId());

            psCategory.executeUpdate();
            connection.commit();
            rowsAffected = 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
               try{
                   connection.close();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
            }
        }

        return rowsAffected;
    }


    @Override
    public List<Product> findByCondition(String name, String author, String publisher, Integer categoryId, Integer yearPublishing) {
        String sql = "SELECT product.*, category.name AS category_name, category.id AS category_id " +
                "FROM product " +
                "INNER JOIN product_category ON product.id = product_category.productId " +
                "INNER JOIN category ON product_category.categoryId = category.id " +
                "WHERE (? IS NULL OR product.name LIKE ?) " +
                "AND (? IS NULL OR product.author = ?) " +
                "AND (? IS NULL OR product.publisher = ?) " +
                "AND (? IS NULL OR category.id = ?) " +
                "AND (? IS NULL OR product.yearPublishing = ?)";

        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Gọi hàm setConditionFilter để đặt giá trị tham số
            setConditionFilter(name, author, publisher, categoryId, yearPublishing, statement);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setAuthor(resultSet.getString("author"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setYearPublishing(resultSet.getInt("yearPublishing"));
                product.setCategoryName(resultSet.getString("category_name"));
                products.add(product);
            }
            products.sort(Comparator.comparingInt(Product::getId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Hàm thiết lập giá trị cho các tham số của PreparedStatement
    private void setConditionFilter(String name, String author, String publisher, Integer categoryId, Integer yearPublishing, PreparedStatement statement) throws SQLException {
        if (name != null) {
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
        } else {
            statement.setNull(1, Types.VARCHAR);
            statement.setNull(2, Types.VARCHAR);
        }

        if (author != null) {
            statement.setString(3, author);
            statement.setString(4, author);
        } else {
            statement.setNull(3, Types.VARCHAR);
            statement.setNull(4, Types.VARCHAR);
        }

        if (publisher != null) {
            statement.setString(5, publisher);
            statement.setString(6, publisher);
        } else {
            statement.setNull(5, Types.VARCHAR);
            statement.setNull(6, Types.VARCHAR);
        }

        if (categoryId != null) {
            statement.setInt(7, categoryId);
            statement.setInt(8, categoryId);
        } else {
            statement.setNull(7, Types.INTEGER);
            statement.setNull(8, Types.INTEGER);
        }

        if (yearPublishing != null) {
            statement.setInt(9, yearPublishing);
            statement.setInt(10, yearPublishing);
        } else {
            statement.setNull(9, Types.INTEGER);
            statement.setNull(10, Types.INTEGER);
        }
    }



}
