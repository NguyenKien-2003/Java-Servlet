package vn.edu.t3h.employeemanager3.service.impl;

import vn.edu.t3h.employeemanager3.dao.DepartmentDao;
import vn.edu.t3h.employeemanager3.model.Department;
import vn.edu.t3h.employeemanager3.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;

    public  DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
