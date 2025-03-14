<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body class="container mt-4">

<div class="card">
    <div class="card-header bg-primary text-white">
        <h4><c:if test="${empty product.id}">Thêm sản phẩm</c:if>
            <c:if test="${not empty product.id}">Chỉnh sửa sản phẩm</c:if>
        </h4>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/save" method="post">
            <input type="hidden" name="id" value="${product.id}">

            <div class="mb-3">
                <label class="form-label">Tên sách:</label>
                <input type="text" class="form-control" name="bookTitle" value="${product.bookTitle}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Tác giả:</label>
                <input type="text" class="form-control" name="author" value="${product.author}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Số trang:</label>
                <input type="number" class="form-control" name="pageCount" value="${product.pageCount}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Nhà xuất bản:</label>
                <input type="text" class="form-control" name="publisher" value="${product.publisher}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Năm xuất bản:</label>
                <input type="number" class="form-control" name="publicationYear" value="${product.publicationYear}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Thể loại:</label>
                <input type="text" class="form-control" name="genre" value="${product.genre}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Giá bán:</label>
                <input type="number" step="0.01" class="form-control" name="price" value="${product.price}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Giảm giá (%):</label>
                <input type="number" step="0.01" class="form-control" name="discount" value="${product.discount}">
            </div>

            <div class="mb-3">
                <label class="form-label">Số lượng:</label>
                <input type="number" class="form-control" name="stockQuantity" value="${product.stockQuantity}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mô tả:</label>
                <textarea class="form-control" name="description">${product.description}</textarea>
            </div>

            <div class="mb-3">
                <label class="form-label">Hình ảnh (URL):</label>
                <input type="text" class="form-control" name="image" value="${product.image}">
            </div>

            <button type="submit" class="btn btn-success">
                <c:if test="${empty product.id}">Thêm sản phẩm</c:if>
                <c:if test="${not empty product.id}">Cập nhật</c:if>
            </button>
            <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Quay lại</a>
        </form>
    </div>
</div>

</body>
</html>
