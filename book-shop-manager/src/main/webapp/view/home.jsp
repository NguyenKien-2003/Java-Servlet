<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vn.edu.t3h.bookshopmanager.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.t3h.bookshopmanager.model.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 19/02/2025
  Time: 12:07 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% session.invalidate(); %>--%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Trang chủ</title>

<%--    <link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">--%>

    <!-- Bootstrap v5.0.1 -->
<%--    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">--%>
<%--    <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <!-- Bootstrap Icons v1.5.0 -->
    <link href="../css/bootstrap-icons.css" type="text/css" rel="stylesheet">

    <!-- Custom Styles -->
    <link href="../css/styles.css" type="text/css" rel="stylesheet">


</head>
<body>
<script src="../js/product-manager-script.js" type="text/javascript" ></script>
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-3 py-3">
                    <a class="text-body" href="./home.html">
                        <h3>Shop Bán Sách</h3>
                    </a>
                </div> <!-- col.// -->
                <div class="col-lg-4 col-xl-5">
                    <form action="#" class="search">
                        <div class="input-group w-100">
                            <input type="text" class="form-control" placeholder="Nhập từ khóa cần tìm ...">
                            <button class="btn btn-primary" type="button">
                                <i class="bi bi-search"></i>
                            </button>
                        </div>
                    </form>
                </div> <!-- col.// -->
                <div class="col-lg-5 col-xl-4">
                    <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                        <li>
                            <a href="#" class="nav-link text-body">
                                <i class="bi bi-person d-block text-center fs-3"></i>
                                Tài khoản
                            </a>
                        </li>
                        <li>
                            <a href="#" class="nav-link text-body">
                                <i class="bi bi-list-check d-block text-center fs-3"></i>
                                Đơn hàng
                            </a>
                        </li>
                        <li>
                            <a href="#" class="nav-link text-body">
                                <i class="bi bi-cart d-block text-center fs-3"></i>
                                Giỏ hàng
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <strong><i class="bi bi-list"></i> Danh mục sản phẩm</strong>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Sách giáo khoa</a></li>
                        <li><a class="dropdown-item" href="#">Sách khoa học</a></li>
                        <li><a class="dropdown-item" href="#">Truyện tranh</a></li>
                        <li><a class="dropdown-item" href="#">Tiểu thuyết</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Tất cả danh mục</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sản phẩm mới</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sản phẩm bán chạy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Khuyến mãi</a>
                </li>
            </ul>
            <div id="userInfo" class="user-info">
                <c:if test="${not empty username}">
                    <span>Xin chao ${username}</span>
                    <a href="/logout">Logout</a>
                </c:if>
                <c:if test="${empty username}">
                    <a href="/login">Login</a>
                </c:if>
        </div>
    </div> <!-- container.// -->
</nav> <!-- navbar-main.// -->

<section class="section-content mb-2">
    <div class="container">
        <header class="section-heading py-4 d-flex justify-content-between">
            <h3 class="section-title">Danh mục sản phẩm</h3>
<%--            <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>--%>
        </header> <!-- section-heading.// -->
        <div class="row item-grid">
            <div class="row item-grid">
                <%
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if (categories != null && !categories.isEmpty()) {
                        for (Category category : categories) {
                %>
                <div class="col-lg-3 col-md-6">
                    <div class="card mb-4">
                        <div class="card-body">
                            <a href="category?categoryId=<%= category.getId() %>" class="stretched-link">
                                <div class="d-flex align-items-center">
<%--                                    <img src="img/50px.png" alt="Category Image">--%>
                                    <span class="category-title ms-3"><%= category.getName() %></span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div> <!-- col -->
                <%
                    }
                } else {
                %>
                <div class="col-12">
                    <p>Không có danh mục nào.</p>
                </div>
                <%
                    }
                %>
            </div> <!-- row -->



<%--            <div class="col-lg-3 col-md-6">--%>
<%--                <div class="card mb-4">--%>
<%--                    <div class="card-body">--%>
<%--                        <a href="#" class="stretched-link">--%>
<%--                            <div class="d-flex align-items-center">--%>
<%--                                <img src="img/50px.png">--%>
<%--                                <span class="category-title ms-3">Sách giáo khoa</span>--%>
<%--                            </div>--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div> <!-- col.// -->--%>
        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<section class="section-content mb-5">
    <div class="container">
        <header class="section-heading py-4 d-flex justify-content-between">
            <h3 class="section-title">Sản phẩm mới nhất</h3>
            <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
        </header> <!-- section-heading.     // -->
        <div class="row item-grid" id="productHome">
            <%  List<Product> products = (List<Product>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    for (Product product : products) {
            %>
            <div class="col-lg-4 col-md-6">
                <div class="card p-3 mb-4">
                    <a href="#" class="img-wrap text-center">
                        <img class="img-fluid" src="img/200px.png">
                    </a>
                    <figcaption class="info-wrap mt-2">
                        <a href="" class="title"><%=product.getName()%></a>
                        <div class="price mt-1 fw-bold"><%=product.getPrice()%>₫</div>
                    </figcaption>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p>Không có sản phẩm nào trong danh mục này.</p>
            <%
                }
            %>
<%--        </div> <!-- col.// -->--%>
<%--            <div class="col-lg-3 col-md-6">--%>
<%--                <div class="card p-3 mb-4">--%>
<%--                    <a href="#" class="img-wrap text-center">--%>
<%--                        <img class="img-fluid" src="img/200px.png">--%>
<%--                    </a>--%>
<%--                    <figcaption class="info-wrap mt-2">--%>
<%--                        <a href="#" class="title">Tên một sản phẩm</a>--%>
<%--                        <div class="price mt-1 fw-bold">450.000₫</div>--%>
<%--                    </figcaption>--%>
<%--                </div>--%>
<%--            </div> <!-- col.// -->--%>
        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<footer class="section-footer">
    <section class="footer-top py-5 bg-light">
        <div class="container">
            <div class="row">
                <aside class="col-sm-6 col-lg-3">
                    <h6 class="pb-2">Giới thiệu</h6>
                    <ul class="list-unstyled">
                        <li><a href="#">Về Shop</a></li>
                        <li><a href="#">Tuyển dụng</a></li>
                        <li><a href="#">Chính sách thanh toán</a></li>
                        <li><a href="#">Chính sách bảo mật</a></li>
                        <li><a href="#">Giải quyết khiếu nại</a></li>
                        <li><a href="#">Hợp tác</a></li>
                    </ul>
                </aside>
                <aside class="col-sm-6 col-lg-3">
                    <h6 class="pb-2">Hỗ trợ khách hàng</h6>
                    <ul class="list-unstyled">
                        <li>Hotline: 1900-80xx</li>
                        <li><a href="#">Câu hỏi thường gặp</a></li>
                        <li><a href="#">Hướng dẫn đặt hàng</a></li>
                        <li><a href="#">Phương thức vận chuyển</a></li>
                        <li><a href="#">Chính sách đổi trả</a></li>
                    </ul>
                </aside>
                <aside class="col-lg-5">
                    <h6 class="pb-2">Đăng ký nhận tin</h6>
                    <form action="#">
                        <div class="input-group w-100">
                            <input type="text" class="form-control" placeholder="Email của bạn ...">
                            <button class="btn btn-primary" type="button">
                                Đăng ký
                            </button>
                        </div>
                    </form>
                </aside>
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- footer-top.// -->

    <section class="footer-bottom text-center bg-light border-top py-3">
        <div class="container-fluid">© 2021 — Shop Bán Sách</div> <!-- container-fluid.// -->
    </section> <!-- footer-bottom.// -->
</footer> <!-- section-footer.// -->
</body>
</html>
