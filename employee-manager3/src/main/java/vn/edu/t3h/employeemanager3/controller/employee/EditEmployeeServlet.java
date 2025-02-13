package vn.edu.t3h.employeemanager3.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager3.dao.EmployeeDao;
import vn.edu.t3h.employeemanager3.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager3.model.Employee;
import vn.edu.t3h.employeemanager3.service.EmployeeService;
import vn.edu.t3h.employeemanager3.service.impl.EmployeeServiceImpl;

import java.io.IOException;

@WebServlet (name = "EditEmployee", value = "/edit-employee")
public class EditEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        employeeService = new EmployeeServiceImpl(employeeDao);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("employeeId"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        Integer department_id = Integer.parseInt(request.getParameter("departmentId"));
        String hireDate = request.getParameter("hireDate");

        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setName(name);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setDepartmentId(department_id);
        employee.setHireDate(hireDate);
        employeeService.editEmployee(employee);
        response.sendRedirect("/employee");



    }

}
