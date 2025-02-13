package vn.edu.t3h.employeemanager3.service;

import vn.edu.t3h.employeemanager3.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    List<Employee> findByFilter(String name, String salary, String fromDate, String toDate, String position);
    int deleteEmployee(int id);
    int addEmployee(Employee employee);
    int editEmployee(Employee employee);
    Employee findById(int id);
}
