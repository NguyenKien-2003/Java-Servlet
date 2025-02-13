<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 15/02/2025
  Time: 6:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="vn.edu.t3h.bookshopmanager.model.Product" %>
<%
    // Lấy thông tin sản phẩm từ request (nếu có)
    Product product = (Product) request.getAttribute("product");
    boolean isEdit = (product != null); // Nếu có dữ liệu product, đây là chỉnh sửa
%>
<html>
<head>
    <title>Admin: <%= isEdit ? "Chỉnh sửa sản phẩm" : "Thêm sản phẩm mới" %></title>
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
            <h3 class="section-title"><%= isEdit ? "Chỉnh sửa sản phẩm" : "Thêm sản phẩm mới" %></h3>
        </header> <!-- section-heading.// -->
        <main class="add-product-form mb-5">
            <form class="w-50" id="add-product-form" method="post" action="<%= isEdit ? "/update-product?id=" + product.getId() : "/add-product" %>">
            <% if (isEdit) { %>
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <% } %>
                <div class="mb-3">
                    <label for="add-book-title" class="form-label">Tên sách</label>
                    <input type="text" class="form-control" id="add-book-title" name="add-book-title" value="<%= isEdit ? product.getName() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-author" class="form-label">Tác giả</label>
                    <input type="text" class="form-control" id="add-book-author" name="add-book-author" value="<%= isEdit ? product.getAuthor() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-pages" class="form-label">Số trang</label>
                    <input type="number" class="form-control" id="add-book-pages" name="add-book-pages" value="<%= isEdit ? product.getPages() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-publisher" class="form-label">Nhà xuất bản</label>
                    <input type="text" class="form-control" id="add-book-publisher" name="add-book-publisher" value="<%= isEdit ? product.getPublisher() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-yearPublishing" class="form-label">Năm phát hành</label>
                    <input type="number" class="form-control" id="add-book-yearPublishing" name="add-book-yearPublishing" min="1900" max="2100" required value="<%= isEdit ? product.getYearPublishing() : "1900" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-category" class="form-label">Thể loại</label>
                    <select id="add-book-category" class="form-select" name="add-book-category">
                        <option value="default" selected>Chọn thể loại</option>
                        <option value="1" <%= isEdit && product.getCategoryId() == 1 ? "selected" : "" %>>Sách giáo khoa</option>
                        <option value="2" <%= isEdit && product.getCategoryId() == 2 ? "selected" : "" %>>Sách khoa học</option>
                        <option value="3" <%= isEdit && product.getCategoryId() == 3 ? "selected" : "" %>>Truyện tranh</option>
                        <option value="4" <%= isEdit && product.getCategoryId() == 4 ? "selected" : "" %>>Tiểu thuyết</option>
                        <option value="5" <%= isEdit && product.getCategoryId() == 5 ? "selected" : "" %>>Truyện ngắn</option>
                        <option value="6" <%= isEdit && product.getCategoryId() == 6 ? "selected" : "" %>>Truyện dài</option>
                        <option value="7" <%= isEdit && product.getCategoryId() == 7 ? "selected" : "" %>>Sách giáo trình</option>
                        <option value="8" <%= isEdit && product.getCategoryId() == 8 ? "selected" : "" %>>Báo in</option>
                        <option value="9" <%= isEdit && product.getCategoryId() == 9 ? "selected" : "" %>>Tạp chí</option>
                        <option value="10" <%= isEdit && product.getCategoryId() == 10 ? "selected" : "" %>>Tập san</option>
                        <option value="11" <%= isEdit && product.getCategoryId() == 11 ? "selected" : "" %>>Sách nấu ăn</option>
                        <option value="12" <%= isEdit && product.getCategoryId() == 12 ? "selected" : "" %>>Sách kỹ thuật</option>
                        <option value="13" <%= isEdit && product.getCategoryId() == 13 ? "selected" : "" %>>Sách nông nghiệp</option>
                        <option value="14" <%= isEdit && product.getCategoryId() == 14 ? "selected" : "" %>>Sách thiếu nhi</option>
                        <option value="15" <%= isEdit && product.getCategoryId() == 15 ? "selected" : "" %>>Sách kỹ năng sống</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="add-book-price" class="form-label">Giá</label>
                    <input type="number" class="form-control" id="add-book-price" name="add-book-price" value="<%= isEdit ? product.getPrice() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-discount" class="form-label">Khuyến mãi</label>
                    <input type="number" class="form-control" id="add-book-discount" name="add-book-discount" value="<%= isEdit ? product.getDiscount() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-quantity" class="form-label">Số lượng trong kho</label>
                    <input type="number" class="form-control" id="add-book-quantity" name="add-book-quantity" value="<%= isEdit ? product.getQuantity() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="add-book-description" class="form-label">Mô tả</label>
                    <textarea class="form-control" id="add-book-description" name="add-book-description" rows="5" ><%= isEdit ? product.getDescription() : "" %></textarea>
                </div>
                <div class="mb-3">
                    <label for="add-book-imageName" class="form-label">Hình</label>
                    <input type="file" class="form-control" id="add-book-imageName" name="add-book-imageName" value="<%= isEdit ? product.getImageName() : "" %>">
                </div>
                <button type="submit" class="btn btn-primary" ><%= isEdit ? "Cập nhật" : "Thêm sản phẩm" %></button>
                <button type="reset" class="btn btn-warning ms-2" id="btn-reset-input">Tẩy trống</button>
                <a class="btn btn-light ms-2" href="/products">Hủy</a>
            </form>
        </main> <!-- add-book-form.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<footer class="section-footer">
    <section class="footer-bottom text-center bg-light border-top py-3">
        <div class="container-fluid">© 2021 — Shop Bán Sách</div> <!-- container-fluid.// -->
    </section> <!-- footer-bottom.// -->
</footer> <!-- section-footer.// -->
<script src="../js/product-manager-script.js"></script>
</body>
</html>
