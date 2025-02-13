package vn.edu.t3h.employeemanager3.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager3.dao.EmployeeDao;
import vn.edu.t3h.employeemanager3.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager3.service.EmployeeService;
import vn.edu.t3h.employeemanager3.service.impl.EmployeeServiceImpl;

import java.io.IOException;

@WebServlet(name = "DeleteEmployeeServlet", value = "/delete-employee")
public class DeleteEmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = new EmployeeServiceImpl(new EmployeeDaoMysqlImpl());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(id);
        response.sendRedirect("/employee");

    }
}
