//package vn.edu.t3h.bookshopmanager.dao.impl;
//
//import vn.edu.t3h.bookshopmanager.dao.RoleDao;
//import vn.edu.t3h.bookshopmanager.model.Role;
//import vn.edu.t3h.bookshopmanager.utils.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class RoleDaoImpl implements RoleDao {
//
//    public Role findRoleById(Integer roleId) {
//        Role role = null;
//        String sql = "SELECT id, name, code FROM role WHERE id = ?";
//
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, roleId); // Set roleId
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    // Lấy dữ liệu từ kết quả truy vấn và gán vào đối tượng Role
//                    Integer id = resultSet.getInt("id");
//                    String name = resultSet.getString("name");
//                    String code = resultSet.getString("code");
//
//                    // Tạo đối tượng Role từ dữ liệu truy vấn
//                    role = new Role(id, name, code);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return role;
//    }
//}
