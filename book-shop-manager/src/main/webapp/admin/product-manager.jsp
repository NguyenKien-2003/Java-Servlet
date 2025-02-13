<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vn.edu.t3h.bookshopmanager.model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 19/02/2025
  Time: 3:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Admin: Quản lý sản phẩm</title>

    <!--    <link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">


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
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-11 py-3">
                    <a class="text-body" href="/home">
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
            <div id="userInfo" class="user-info">
                <c:if test="${not empty username}">
                    <span>Xin chao ${username}</span>
                    <a href="/logout">Logout</a>
                </c:if>
                <c:if test="${empty username}">
                    <a href="/login">Login</a>
                </c:if>
            </div>
        </div>
    </div> <!-- container.// -->
</nav> <!-- navbar-main.// -->
<form class="search-form" action="products" method="get">
    <div>
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" placeholder="Search by Name"/>
    </div>

    <div>
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" value="${param.author}" placeholder="Search by Author"/>
    </div>

    <div>
        <label for="publisher">Publisher:</label>
        <input type="text" id="publisher" name="publisher" value="${param.publisher}" placeholder="Search by Publisher"/>
    </div>

    <div>
        <label for="categoryId">Category:</label>
        <select id="categoryId" name="categoryId">
            <option value="">-- Select Category --</option>
            <option value="1" ${param.categoryId == '1' ? 'selected' : ''}>Sách giáo khoa</option>
            <option value="2" ${param.categoryId == '2' ? 'selected' : ''}>Sách khoa học</option>
            <option value="3" ${param.categoryId == '3' ? 'selected' : ''}>Truyện tranh</option>
            <option value="4" ${param.categoryId == '4' ? 'selected' : ''}>Tiểu thuyết</option>
            <option value="5" ${param.categoryId == '5' ? 'selected' : ''}>Truyện ngắn</option>
            <option value="6" ${param.categoryId == '6' ? 'selected' : ''}>Truyện dài</option>
            <option value="7" ${param.categoryId == '7' ? 'selected' : ''}>Sách giáo trình</option>
            <option value="8" ${param.categoryId == '8' ? 'selected' : ''}>Báo in</option>
            <option value="9" ${param.categoryId == '9' ? 'selected' : ''}>Tạp chí</option>
            <option value="10" ${param.categoryId == '10' ? 'selected' : ''}>Tập san</option>
            <option value="11" ${param.categoryId == '11' ? 'selected' : ''}>Sách nấu ăn</option>
            <option value="12" ${param.categoryId == '12' ? 'selected' : ''}>Sách kỹ thuật</option>
            <option value="13" ${param.categoryId == '13' ? 'selected' : ''}>Sách nông nghiệp</option>
            <option value="14" ${param.categoryId == '14' ? 'selected' : ''}>Sách thiếu nhi</option>
            <option value="15" ${param.categoryId == '15' ? 'selected' : ''}>Sách kỹ năng sống</option>

            <!-- Thêm các danh mục khác -->
        </select>
    </div>

    <div>
        <label for="yearPublishing">Year of Publishing:</label>
        <input type="number" id="yearPublishing" name="yearPublishing" value="${param.yearPublishing}" placeholder="Enter Year"/>
    </div>

    <div>
        <input type="submit" value="Search"/>
    </div>
</form>
<section class="section-content">
    <div class="container">
        <header class="section-heading py-4 d-flex justify-content-between">
            <h3 class="section-title">Quản lý sản phẩm</h3>
            <a class="btn btn-primary" href="/form-product" role="button" style="height: fit-content;">Thêm sản phẩm</a>
        </header> <!-- section-heading.// -->
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
                <tbody id="productTable">
                <c:choose>
                    <c:when test="${not empty products}">
                        <c:forEach var="product" items="${products}" varStatus="loop">
                            <tr>
                                <th scope="row">${loop.index + 1}</th>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.author}</td>
                                <td>${product.pages}</td>
                                <td>${product.publisher}</td>
                                <td>${product.categoryName}</td>
                                <td>${product.yearPublishing}</td>
                                <td>${product.totalBuy}</td>
                                <td class="text-center text-nowrap">
                                    <a class="btn btn-primary me-2" href="/view-product?id=${product.id}">Xem</a>
                                    <a class="btn btn-success me-2 btn-insert-to-update-form" href="/form-product?id=${product.id}">Sửa</a>
                                    <a class="btn btn-danger" href="/delete-product?id=${product.id}"
                                       onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="10" class="text-center">Không có sản phẩm nào</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </main> <!-- book-manager-table.// -->
        <nav aria-label="...">
            <ul class="pagination" id="pagination">
                <!-- <li class="page-item disabled" id="prev-page">
                  <a class="page-link" href="#">Previous</a>
                </li>
                <li class="page-item active">
                  <a class="page-link" href="#">1</a></li>
                <li class="page-item" aria-current="page">
                  <a class="page-link" href="#">2</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item" id="next-page">
                  <a class="page-link" href="#">Next</a>
                </li> -->
            </ul>
        </nav>
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<footer class="section-footer">
    <section class="footer-bottom text-center bg-light border-top py-3">
        <div class="container-fluid">© 2021 — Shop Bán Sách</div> <!-- container-fluid.// -->
    </section> <!-- footer-bottom.// -->
</footer> <!-- section-footer.// -->
</body>
</html>
