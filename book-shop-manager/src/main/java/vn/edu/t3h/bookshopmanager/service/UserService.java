package vn.edu.t3h.bookshopmanager.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.bookshopmanager.model.User;

import java.util.List;

public interface UserService {
        String login(String username, String password, HttpServletRequest req);
        String getRoleByUsername(String username);
        List<User> getUsersByRole(String role);
        int addUser(User user);
        User getUserById(int id);
        int deleteUserById(int id);
}
