package vn.edu.t3h.dao;

import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.UserDTO;

import java.util.List;

public interface UserRepository {
    List<UserDTO> getAllUsers();

    public List<UserDTO> findUsers(String keyword);
}
