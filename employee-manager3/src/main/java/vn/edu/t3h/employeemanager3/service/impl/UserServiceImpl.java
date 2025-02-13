    package vn.edu.t3h.employeemanager3.service.impl;

    import jakarta.servlet.http.HttpServletRequest;
    import vn.edu.t3h.employeemanager3.dao.RoleDao;
    import vn.edu.t3h.employeemanager3.dao.UserDao;
    import vn.edu.t3h.employeemanager3.dao.impl.RoleDaoImpl;
    import vn.edu.t3h.employeemanager3.model.RoleModel;
    import vn.edu.t3h.employeemanager3.model.UserModel;
    import vn.edu.t3h.employeemanager3.service.UserService;
    import vn.edu.t3h.employeemanager3.utils.Constants;
    import vn.edu.t3h.employeemanager3.utils.PasswordUtils;

    public class UserServiceImpl implements UserService {
        private UserDao userDao;
        private RoleDao roleDao;

        public UserServiceImpl(UserDao userDao, RoleDaoImpl roleDao){
            this.userDao = userDao;
            this.roleDao = roleDao;
        }
        @Override
        public String login(String username, String password, HttpServletRequest request) {


            String passwordEncrypted = PasswordUtils.encryptPassword(password);
            UserModel user = userDao.findUserByUserNameAndPassword(username, passwordEncrypted);
            String urlRedirect = "";
            if (user == null) {
                urlRedirect = "/login?message="+Constants.LOGIN_ERROR;
                return urlRedirect;
            }
            RoleModel  roleModel = roleDao.findRoleById(user.getRoleId());
            if (roleModel == null) {
                urlRedirect = "/login?message=" + Constants.PERMISSON_DENIED;
                return urlRedirect;
            }
            request.getSession().setAttribute(Constants.SESSION_ID_CURRENT_USER, user);
            if (roleModel.getCode().equals(Constants.ROLE.ROLE_ADMIN.name())) {
                urlRedirect = "/employee";
            }else {
                urlRedirect = "/";
            }
        return urlRedirect;
        }
    }
