package vn.edu.t3h.employeemanager3.dao;


import vn.edu.t3h.employeemanager3.model.RoleModel;

public interface RoleDao {

    RoleModel findRoleById(Integer roleId);
}
