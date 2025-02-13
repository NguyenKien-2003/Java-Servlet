package vn.edu.t3h.demoemployeelist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.demoemployeelist.dao.EmployeeDao;
import vn.edu.t3h.demoemployeelist.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.demoemployeelist.model.Employee;
import vn.edu.t3h.demoemployeelist.service.EmployeeService;
import vn.edu.t3h.demoemployeelist.service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
        List<Employee> employees = employeeService.getAllEmployee();
        request.setAttribute("employees", employees);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("employee-list.jsp");


    }
}
