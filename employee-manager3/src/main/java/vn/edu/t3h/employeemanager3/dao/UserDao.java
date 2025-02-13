package vn.edu.t3h.employeemanager3.dao;


import vn.edu.t3h.employeemanager3.model.UserModel;

public interface UserDao {

    UserModel findUserByUserNameAndPassword(String username, String password);
}
