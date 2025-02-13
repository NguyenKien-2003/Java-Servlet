<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 15/02/2025
  Time: 6:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="vn.edu.t3h.bookshopmanager.model.user" %>
<%@ page import="vn.edu.t3h.bookshopmanager.model.User" %>
<%
    // Lấy thông tin sản phẩm từ request (nếu có)
    User user = (User) request.getAttribute("user");
    boolean isEdit = (user != null);
%>
<html>
<head>
    <title>Admin: <%= isEdit ? "Chỉnh sửa user" : "Thêm user mới" %></title>
    <%--    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">--%>
    <%--    <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>--%>
    <link href="../css/bootstrap-icons.css" type="text/css" rel="stylesheet">
    <link href="../css/styles.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-11 py-3">
                    <a class="text-body" href="./home.html">
                        <h3>Shop Bán Sách : Admin</h3>
                    </a>
                </div> <!-- col.// -->
                <div class="col-1">
                    <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                        <li>
                            <a href="#" class="nav-link text-body">
                                <i class="bi bi-window d-block text-center fs-3"></i>
                                Client
                            </a>
                        </li>
                    </ul>
                </div> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- header-main.// -->
</header> <!-- section-header.// -->

<nav class="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-people"></i> Quản lý người dùng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-tags"></i> Quản lý thể loại</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#"><i class="bi bi-book"></i> Quản lý sản phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-cart"></i> Quản lý giỏ hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-inboxes"></i> Quản lý đơn hàng</a>
                </li>
            </ul>
            <a class="btn btn-primary" href="#" role="button">Đăng nhập</a>
        </div>
    </div> <!-- container.// -->
</nav> <!-- navbar-main.// -->

    <section class="section-content">
        <div class="container">
            <header class="section-heading py-4 d-flex justify-content-between">
                <h3 class="section-title"><%= isEdit ? "Chỉnh sửa User" : "Thêm User mới" %></h3>
            </header> <!-- section-heading.// -->
            <main class="add-user-form mb-5">
                <form class="w-50" id="add-user-form" method="post" action="<%= isEdit ? "/update-user?id=" + user.getId() : "/add-user" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="userId" value="<%= user.getId() %>">
                    <% } %>
                    <div class="mb-3">
                        <label for="username" class="form-label">Tên đăng nhập</label>
                        <input type="text" class="form-control" id="username" name="username" value="<%= isEdit ? user.getUsername() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= isEdit ? user.getEmail() : "" %>">
                    </div>

                    <% if (!isEdit) { %> <!-- Chỉ hiển thị mật khẩu khi thêm mới -->
                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <% } %>

                    <div class="mb-3">
                        <label for="role" class="form-label">Vai trò</label>
                        <select id="role" class="form-select" name="role">
                            <option value="USER" <%= isEdit && user.getRole().equals("USER") ? "selected" : "" %>>Người dùng</option>
                            <option value="ADMIN" <%= isEdit && user.getRole().equals("ADMIN") ? "selected" : "" %>>Quản trị viên</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary"><%= isEdit ? "Cập nhật" : "Thêm User" %></button>
                    <a class="btn btn-light ms-2" href="/users">Hủy</a>
                </form>
            </main> <!-- add-book-form.// -->
        </div> <!-- container.// -->
    </section> <!-- section-content.// -->

<footer class="section-footer">
    <section class="footer-bottom text-center bg-light border-top py-3">
        <div class="container-fluid">© 2021 — Shop Bán Sách</div> <!-- container-fluid.// -->
    </section> <!-- footer-bottom.// -->
</footer> <!-- section-footer.// -->
<script src="../js/user-manager-script.js"></script>
</body>
</html>
