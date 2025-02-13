package vn.edu.t3h.employeemanager3.controller.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager3.dao.DepartmentDao;
import vn.edu.t3h.employeemanager3.dao.EmployeeDao;
import vn.edu.t3h.employeemanager3.dao.impl.DepartmentDaoImpl;
import vn.edu.t3h.employeemanager3.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager3.model.Department;
import vn.edu.t3h.employeemanager3.model.Employee;
import vn.edu.t3h.employeemanager3.service.DepartmentService;
import vn.edu.t3h.employeemanager3.service.EmployeeService;
import vn.edu.t3h.employeemanager3.service.impl.DepartmentServiceImpl;
import vn.edu.t3h.employeemanager3.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FormUpdate", value = "/form-update")
public class FormUpdate extends HttpServlet {
    DepartmentService departmentService;
    EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        departmentService = new DepartmentServiceImpl(departmentDao);
        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.findById(id);
        List<Department> departments = departmentService.getAllDepartments();
        req.setAttribute("departments", departments);
        req.setAttribute("employee", employee);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/edit-employee.jsp");
        dispatcher.forward(req, resp);
    }
}
