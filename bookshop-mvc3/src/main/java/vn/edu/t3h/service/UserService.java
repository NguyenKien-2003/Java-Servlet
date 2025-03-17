package vn.edu.t3h.service;

import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> findUser(String keyword);
    UserDTO getUserById(int id);
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUserById(int id);

}
