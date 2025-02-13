package vn.edu.t3h.employeemanager3.controller.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager3.dao.EmployeeDao;
import vn.edu.t3h.employeemanager3.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager3.model.Employee;
import vn.edu.t3h.employeemanager3.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {

    private EmployeeServiceImpl employeeService;

    @Override
    public void init() throws ServletException {
        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        this.employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // laays tham so tu req
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        String position = req.getParameter("position");
        List<Employee> employees = employeeService.findByFilter(name, salary, fromDate, toDate, position);
        req.setAttribute("employees", employees);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employees.jsp");
        requestDispatcher.forward(req, resp);
    }



}
