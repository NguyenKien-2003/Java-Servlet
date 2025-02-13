package vn.edu.t3h.employeemanager3.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    public String login(String username, String password, HttpServletRequest req);


}
