//package vn.edu.t3h.dao.impl;
//
//import vn.edu.t3h.dao.ProductRepository;
//import vn.edu.t3h.model.ProductDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class ProductJdbcTemplateRepositoryImpl implements ProductRepository {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public List<ProductDTO> findAll() {
//        String sql = "select * from products";
//        List<ProductDTO> products = jdbcTemplate.query(sql,new RowMapper<ProductDTO>(){
//            @Override
//            public ProductDTO mapRow(ResultSet rs, int i) throws SQLException {
//                System.out.println("Bắt đầu map dữ liệu dạng sql sang object java của sản phẩm thứ: " + i);
//                ProductDTO product = new ProductDTO();
//                product.setId(rs.getInt("id"));
//                product.setId(rs.getInt("id"));
//                product.setBookTitle(rs.getString("book_title"));
//                product.setAuthor(rs.getString("author"));
//                product.setPageCount(rs.getInt("page_count"));
//                product.setPublisher(rs.getString("publisher"));
//                product.setPublicationYear(rs.getInt("publication_year"));
//                product.setGenre(rs.getString("genre"));
//                product.setPrice(rs.getDouble("price"));
//                product.setDiscount(rs.getDouble("discount"));
//                product.setStockQuantity(rs.getInt("stock_quantity"));
//                product.setDescription(rs.getString("description"));
//                return product;
//            }
//        });
//        return products;
//    }
//
//    @Override
//    public int addProduct(ProductDTO product) {
//        String sql = "INSERT INTO products (book_title, author, page_count, publisher, publication_year, genre, price, discount, stock_quantity, description, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, product.getBookTitle(), product.getAuthor(), product.getPageCount(), product.getPublisher(),
//                product.getPublicationYear(), product.getGenre(), product.getPrice(), product.getDiscount(),
//                product.getStockQuantity(), product.getDescription(), product.getImage());
//    }
//
//    @Override
//    public ProductDTO getProductById(int id) {
//        String sql = "SELECT * FROM products WHERE id = ?";
//
//        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ProductDTO>() {
//            @Override
//            public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                ProductDTO product = new ProductDTO();
//                product.setId(rs.getInt("id"));
//                product.setBookTitle(rs.getString("book_title"));
//                product.setAuthor(rs.getString("author"));
//                product.setPageCount(rs.getInt("page_count"));
//                product.setPublisher(rs.getString("publisher"));
//                product.setPublicationYear(rs.getInt("publication_year"));
//                product.setGenre(rs.getString("genre"));
//                product.setPrice(rs.getDouble("price"));
//                product.setDiscount(rs.getDouble("discount"));
//                product.setStockQuantity(rs.getInt("stock_quantity"));
//                product.setDescription(rs.getString("description"));
//                product.setImage(rs.getString("image"));
//                return product;
//            }
//        });
//    }
//
//    @Override
//    public void updateProduct(ProductDTO product) {
//        String sql = "UPDATE products SET book_title = ?,author = ?,page_count = ?,publisher = ? ,publication_year = ? ,genre = ? ,price = ?,discount = ?,stock_quantity = ?,description = ? ,image =?    WHERE id = ?";
//        jdbcTemplate.update(sql, product.getBookTitle(),product.getAuthor(),product.getPageCount(),product.getPublisher(),product.getPublicationYear(),product.getGenre(), product.getPrice(),product.getDiscount(),product.getStockQuantity(),product.getDescription(),product.getImage(),product.getId());
//    }
//
//    @Override
//    public void deleteProduct(int id) {
//        String sql = "DELETE FROM products WHERE id = ?";
//        jdbcTemplate.update(sql, id);
//    }
//}