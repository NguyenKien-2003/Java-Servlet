<%--
  Created by IntelliJ IDEA.
  User: Minh
  Date: 23/01/2025
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>Sửa Nhân Viên</h2>
    <form action="edit-employee" method="post">
        <input type="hidden" name="employeeId" value="${employee.employeeId}" >
        <div class="form-group mt-5">
            <label for="name">Tên Nhân Viên:</label>
            <input type="text" class="form-control" id="name" name="name" value="${employee.name}" required>
        </div>
        <div class="form-group mt-5">
            <label for="position">Chức Vụ:</label>
            <input type="text" class="form-control" id="position" name="position" value="${employee.position}" required>
        </div>
        <div class="form-group mt-5">
            <label for="salary">Lương:</label>
            <input type="number" class="form-control" id="salary" name="salary" value="${employee.salary}" required>
        </div>
        <div class="form-group mt-5">
            <label for="departmentId">Phòng Ban:</label>
            <select class="form-select" id="departmentId" name="departmentId" required>
                <c:forEach var="department" items="${departments}">
                    <option value="${department.departmentId}" <c:if test="${department.departmentId == employee.departmentId}">selected</c:if>>${department.departmentName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group mt-5">
            <label for="hireDate">Ngày Tuyển Dụng:</label>
            <input type="date" class="form-control" id="hireDate" name="hireDate" value="${employee.hireDate}" required>
        </div>
        <button type="submit" class="btn btn-primary">Sửa Nhân Viên</button>
    </form>
</div>
</body>
</html>