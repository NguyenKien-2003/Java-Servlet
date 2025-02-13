package vn.edu.t3h.bookshopmanager.service.impl;

import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

import vn.edu.t3h.bookshopmanager.dao.UserDao;

//import vn.edu.t3h.bookshopmanager.model.Role;
import vn.edu.t3h.bookshopmanager.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookshopmanager.model.User;
import vn.edu.t3h.bookshopmanager.service.UserService;
import vn.edu.t3h.bookshopmanager.utils.Constants;
import vn.edu.t3h.bookshopmanager.utils.PasswordUtils;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public String login(String username, String password, HttpServletRequest request) {


        String passwordEncrypted = PasswordUtils.encryptPassword(password);
        User user = userDao.findUserByUserNameAndPassword(username, passwordEncrypted);
        String urlRedirect = "";
        if (user == null) {
            urlRedirect = "/login?message="+ Constants.LOGIN_ERROR;
            return urlRedirect;
        }
       if (user.getRole() == null) {
            urlRedirect = "/login?message=" + Constants.PERMISSON_DENIED;
            return urlRedirect;
        }
        request.getSession().setAttribute(Constants.SESSION_ID_CURRENT_USER, user);
        if (user.getRole().equals(Constants.ROLE.ADMIN.name())) {
            urlRedirect = "/admin/products";
        } else if (user.getRole().equals(Constants.ROLE.EMPLOYEE.name())) {
            urlRedirect = "/employee/products";
        } else {
            urlRedirect = "/";
        }
        return urlRedirect;
    }

    @Override
    public List<User> getUsersByRole(String role) {
        if (StringUtils.isNullOrEmpty(role)){
            role = null;
        }
        List<User> users = userDao.findAllUsersByRole(role);
        return users;
    }

    @Override
    public int addUser(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(int id) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserById(id);
    }

    @Override
    public int deleteUserById(int id) {
        UserDao userDao = new UserDaoImpl();
        return userDao.deleteUser(id);
    }
}
