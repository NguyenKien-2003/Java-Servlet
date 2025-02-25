package vn.edu.t3h.bookshopmanager.dao.impl;

import com.sun.tools.javac.Main;
import vn.edu.t3h.bookshopmanager.dao.UserDao;
import vn.edu.t3h.bookshopmanager.model.User;
import vn.edu.t3h.bookshopmanager.utils.DatabaseConnection;
import vn.edu.t3h.bookshopmanager.utils.PasswordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";


        try (Connection connection = DatabaseConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setGender(resultSet.getBoolean("gender"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    // Phương thức tìm người dùng theo tên đăng nhập và mật khẩu
    public User findUserByUserNameAndPassword(String username, String password) {
        User user = null;
        String sql = "SELECT u.id, u.username, u.password, u.fullname,u.email,u.gender, u.phoneNumber,u.address, u.role FROM user u WHERE u.username = ? AND u.password = ?";

        // nếu viết trong try thì connection sẽ tự động được close, áp dụng cho version jdbc  moi
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username); // Set tên đăng nhập
            preparedStatement.setString(2, password); // Set mật khẩu
            // tuong tu connection
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Lấy dữ liệu từ kết quả truy vấn và gán vào đối tượng UserModel
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String fullName = resultSet.getString("fullname");
                    String email = resultSet.getString("email");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    boolean gender = resultSet.getBoolean("gender");
                    String address = resultSet.getString("address");
                    String role = resultSet.getString("role");

                    // Tạo đối tượng UserModel từ dữ liệu truy vấn
                    user = new User();
                    user.setId(id);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setFullname(fullName);
                    user.setEmail(email);
                    user.setPhoneNumber(phoneNumber);
                    user.setGender(gender);
                    user.setAddress(address);
                    user.setRole(role);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAllUsersByRole(String role) {
        String sql = "SELECT * from user  where (role = ? or ? is null)";
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            setUserConditionFilter(role, statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
//                String passwordDecrypted = PasswordUtils.decryptPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setGender(resultSet.getBoolean("gender"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return users;
    }

    public void setUserConditionFilter(String role, PreparedStatement statement) throws SQLException {
        if (role != null) {
            statement.setString(1, role );
            statement.setString(2, role);
        } else {
            statement.setNull(1, Types.VARCHAR);
            statement.setNull(2, Types.VARCHAR);
        }
    }

    @Override
    public int addUser(User user) {
        int rowsAffected = 0;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "insert into user(username, password, fullname, email, phoneNumber, gender, address, role)\n" +
                    "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String encryptedPassword = PasswordUtils.encryptPassword(user.getPassword());
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setBoolean(6, user.getGender());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getRole());
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        int rowsAffected = 0;
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "delete from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
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
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT u.id, u.username, u.password, u.fullname,u.email,u.gender, u.phoneNumber,u.address, u.role FROM user u WHERE u.id = ?";

        // nếu viết trong try thì connection sẽ tự động được close, áp dụng cho version jdbc  moi
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id); // Set tên đăng nhập
            // tuong tu connection
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Lấy dữ liệu từ kết quả truy vấn và gán vào đối tượng UserModel
                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFullname(resultSet.getString("fullname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhoneNumber(resultSet.getString("phoneNumber"));
                    user.setGender(resultSet.getBoolean("gender"));
                    user.setAddress(resultSet.getString("address"));
                    user.setRole(resultSet.getString("role"));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String getRoleByUserName(String username) {
        String role = null;
        String sql = "SELECT role FROM user WHERE username = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
         preparedStatement.setString(1,username);
         try (ResultSet resultSet = preparedStatement.executeQuery()) {
             if (resultSet.next()) {
                 role = resultSet.getString("role");
             }
         }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }
}
