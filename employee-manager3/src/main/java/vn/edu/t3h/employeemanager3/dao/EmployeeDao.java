package vn.edu.t3h.employeemanager3.dao;


import vn.edu.t3h.employeemanager3.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployee();
    int deleteEmployee(int id);
    int addEmployee(Employee employee);
    int editEmployee(Employee employee);
    Employee getEmployeeById(int id);

    List<Employee> findByCondition(String name, String salary, String fromDate, String toDate, String position);
}

