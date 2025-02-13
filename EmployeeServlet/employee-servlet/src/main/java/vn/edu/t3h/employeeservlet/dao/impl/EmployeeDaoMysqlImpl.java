package vn.edu.t3h.employeeservlet.dao.impl;

import vn.edu.t3h.employeeservlet.dao.EmployeeDAO;
import vn.edu.t3h.employeeservlet.model.Employee;

import java.sql.Connection;
import java.util.List;


public class EmployeeDaoMysqlImpl implements EmployeeDAO {

    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = getConnection();

    }
}
