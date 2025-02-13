package vn.edu.t3h.employeeservlet.dao;

import vn.edu.t3h.employeeservlet.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployee();
}
