package vn.edu.t3h.bookshopmanager.dao;

import vn.edu.t3h.bookshopmanager.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User findUserByUserNameAndPassword(String username, String password);
    List<User> findAllUsersByRole(String role);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    User getUserById(int id);
    String getRoleByUserName(String username);
}
