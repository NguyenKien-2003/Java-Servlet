package vn.edu.t3h.employeemanager3.service.impl;



import vn.edu.t3h.employeemanager3.dao.RoleDao;
import vn.edu.t3h.employeemanager3.model.RoleModel;
import vn.edu.t3h.employeemanager3.service.RoleService;

import javax.management.relation.Role;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleModel getRoleById(Integer roleId) {
        return roleDao.findRoleById(roleId);
    }
}
