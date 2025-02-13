<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 09/01/2025
  Time: 8:15 CH
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <h1>home page t3h</h1>
    <div id="userInfo" class="user-info">
        <c:if test="${not empty username}">
            <span>Xin chao ${username}</span>
            <a href="/logout">Logout</a>
        </c:if>
        <c:if test="${empty username}">
            <a href="/login">Login</a>
        </c:if>
    </div>
    <a href="home-servlet">Home Servlet</a>
    <br>
    <a href="employee">Danh sách nhân viên</a>
    </body>
    </html>
