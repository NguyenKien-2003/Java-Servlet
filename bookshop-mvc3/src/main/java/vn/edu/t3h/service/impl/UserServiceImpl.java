package vn.edu.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.edu.t3h.dao.ProductRepository;
import vn.edu.t3h.dao.UserRepository;
import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.UserDTO;
import vn.edu.t3h.service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("userHibernateRepositoryImpl")
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<UserDTO> findUser(String keyword) {
        return userRepository.findUsers(keyword);
    }
}
