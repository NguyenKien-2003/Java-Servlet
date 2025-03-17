package vn.edu.t3h.dao;

import org.hibernate.Session;
import vn.edu.t3h.entity.IdentityCard;
import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.entity.RoleEntity;
import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.UserDTO;

import java.util.List;

public interface UserRepository {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    List<UserDTO> findUsers(String keyword);
    void deleteUser(int id);
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    RoleEntity getRoleByName(Session session, String roleName);
}
