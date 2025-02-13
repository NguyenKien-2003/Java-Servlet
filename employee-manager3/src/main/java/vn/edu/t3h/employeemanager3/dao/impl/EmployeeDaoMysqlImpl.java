package vn.edu.t3h.employeemanager3.dao.impl;

import vn.edu.t3h.employeemanager3.dao.EmployeeDao;
import vn.edu.t3h.employeemanager3.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoMysqlImpl implements EmployeeDao {
    public static final String ADD_EMPLOYEE = "INSERT INTO employees (employee_id, name,position,salary, department_id, hire_date) VALUES ( ?,?,?,?,?,?)";
    public static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id = ?" ;
    public static final String GET_EMPLOYEE_BY_ID = "SELECT e.employee_id, e.name, e.position, e.salary, e.department_id, e.hire_date\n" +
            "FROM employees e WHERE e.employee_id = ?";
     public static final String UPDATE_EMPLOYEE = "UPDATE employees SET name = ?, position = ?, salary = ?, department_id = ?, hire_date = ? WHERE employee_id = ?";

    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = getConnection();
        List<Employee> employeesResult = new ArrayList<>();
        // tạo câu query
        String sql = "select * from employees emp " +
                "inner join departments dept on emp.department_id = dept.department_id;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // thực thi câu query
            ResultSet resultSet = statement.executeQuery();
            // lấy ra dữ liệu từ câu query đưa vào object java
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));

                employeesResult.add(employee);
            }
            System.out.println("get employee success");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // trả về kết quả là danh sach employee
        return employeesResult;
    }




    @Override
    public int deleteEmployee(int id) {
        int rowsAffected = 0;
        Connection connection = getConnection();
        try(
        PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if( connection!= null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return rowsAffected;
    }

    @Override
    public int addEmployee(Employee employee) {
        int rowsAffected = 0;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYEE);
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3,employee.getPosition());
            preparedStatement.setDouble(4,employee.getSalary());
            preparedStatement.setInt(5,employee.getDepartmentId());
            preparedStatement.setString(6,employee.getHireDate());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public int editEmployee(Employee employee) {
        int rowsAffected = 0;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getPosition());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setInt(4,employee.getDepartmentId());
            preparedStatement.setString(5,employee.getHireDate());
            preparedStatement.setInt(6,employee.getEmployeeId());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return rowsAffected;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                employee.setHireDate(resultSet.getString("hire_date"));
                System.out.println("get employee success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection !=null){
                try{
                    connection.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return employee;
    }


    public List<Employee> findByCondition(String name, String salary, String fromDate, String toDate, String position) {
        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date\n" +
                "FROM employees e\n" +
                "         LEFT JOIN departments d ON e.department_id = d.department_id\n" +
                "WHERE (e.name LIKE ? OR ? IS NULL)\n" +
                "  AND (e.salary = ? OR ? IS NULL)\n" +
                "  AND (e.hire_date >= ? or ? is NULL)\n" +
                "  AND (e.hire_date <= ? or ? is NULL)\n" +
                "  AND (e.position LIKE ? OR ? IS NULL)\n;";
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            setConditionFilter(name,salary,fromDate,toDate,position,statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employees.add(employee);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return employees;
    }

    private static void closeConnection(Connection connection) {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setConditionFilter(String name, String salary, String fromDate, String toDate, String position, PreparedStatement statement) throws SQLException {
        if(name != null){
            statement.setString(1, "%" + name + "%");
            statement.setString(2,"%" + name + "%");
        }else {
            statement.setNull(1, Types.VARCHAR);
            statement.setNull(2, Types.VARCHAR);
        }

        if (salary != null){
            statement.setLong(3, Long.parseLong(salary));
            statement.setLong(4, Long.parseLong(salary));
        }else {
            statement.setNull(3, Types.DECIMAL);
            statement.setNull(4, Types.DECIMAL);
        }

        if (fromDate != null){
            statement.setString(5, fromDate);
            statement.setString(6, fromDate);
        }else {
            statement.setNull(5, Types.VARCHAR);
            statement.setNull(6, Types.VARCHAR);
        }

        if (toDate != null){
            statement.setString(7, toDate);
            statement.setString(8, toDate);
        }else {
            statement.setNull(7, Types.VARCHAR);
            statement.setNull(8, Types.VARCHAR);
        }

        if (position != null){
            statement.setString(9, "%" + position + "%");
            statement.setString(10, "%" + position + "%");
        }else {
            statement.setNull(9, Types.VARCHAR);
            statement.setNull(10, Types.VARCHAR);
        }
    }

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/quanlynhansu";
        String username = "root";
        String password = "12345678";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("get connection success");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
