package vn.edu.t3h.employeemanager3.service.impl;

import com.mysql.cj.util.StringUtils;
import vn.edu.t3h.employeemanager3.dao.EmployeeDao;
import vn.edu.t3h.employeemanager3.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager3.model.Employee;
import vn.edu.t3h.employeemanager3.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    public EmployeeServiceImpl(EmployeeDao employeeDao) {this.employeeDao = employeeDao;}

    @Override
    public List<Employee> getAllEmployee(){
        return employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> findByFilter(String name, String salary, String fromDate, String toDate, String position) {

        if (StringUtils.isNullOrEmpty(name)){
            name = null;
        }
        if (StringUtils.isNullOrEmpty(salary)){
            salary = null;
        }
        if (StringUtils.isNullOrEmpty(fromDate)){
            fromDate = null;
        }
        if (StringUtils.isNullOrEmpty(toDate)){
            toDate = null;
        }
        if (StringUtils.isNullOrEmpty(position)){
            position = null;
        }
        List<Employee> employees = employeeDao.findByCondition(name, salary, fromDate, toDate, position);
        return employees;
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public int editEmployee(Employee employee) {
        return  employeeDao.editEmployee(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.getEmployeeById(id);
    }


}
