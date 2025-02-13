<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 14/02/2025
  Time: 2:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin: Quản lý sản phẩm</title>

    <!-- Context path for resources -->
    <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

    <!-- Bootstrap resources -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>

    <!-- Bootstrap Icons -->
    <link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet">

    <!-- Custom Styles -->
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>

<body>

<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-11 py-3">
                    <a class="text-body" href="./home.jsp">
                        <h3>Shop Bán Sách : Admin</h3>
                    </a>
                </div>
                <div class="col-1">
                    <ul class="nav justify-content-end">
                        <li>
                            <a href="${pageContext.request.contextPath}/client" class="nav-link text-body">
                                <i class="bi bi-window d-block text-center fs-3"></i>
                                Client
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</header>

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
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>
    </div>
</nav>

<section class="section-content">
    <div class="container">
        <header class="section-heading py-4 d-flex justify-content-between">
            <h3>Quản lý sản phẩm</h3>
            <a class="btn btn-primary" href="add-product.jsp" role="button">Thêm sản phẩm</a>
        </header>

        <main class="table-responsive-xl mb-5">
            <table class="table table-bordered table-striped table-hover align-middle">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Tên sách</th>
                    <th scope="col">Tác giả</th>
                    <th scope="col">Số trang</th>
                    <th scope="col">Nhà xuất bản</th>
                    <th scope="col">Thể loại</th>
                    <th scope="col">Năm phát hành</th>
                    <th scope="col">Số lượt mua</th>
                    <th scope="col">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index + 1}</th>
                        <td>${product.id}</td>
                        <td>${(product.name)}</td>
                        <td>${(product.author)}</td>
                        <td>${product.pages}</td>
                        <td>${(product.publisher)}</td>
                        <td>${(product.categoryName)}</td>
                        <td>${product.yearPublishing}</td>
                        <td>${product.totalBuy}</td>
                        <td class="text-center text-nowrap">
                            <a class="btn btn-primary me-2" href="view-product.jsp?id=${product.id}">Xem</a>
                            <a class="btn btn-success me-2" href="edit-product.jsp?id=${product.id}">Sửa</a>
                            <a class="btn btn-danger" href="delete-product.jsp?id=${product.id}"
                               onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>

        <nav class="mt-3 mb-5">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}">Trang trước</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}">Trang sau</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</section>

<footer class="section-footer">
    <section class="footer-bottom text-center bg-light border-top py-3">
        <div class="container-fluid">
            © <%= new java.util.Date().getYear() + 1900 %> — Shop Bán Sách
        </div>
    </section>
</footer>

</body>
</html>
