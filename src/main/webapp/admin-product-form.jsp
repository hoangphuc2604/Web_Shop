<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${product != null && product.id > 0 ? "Cập nhật sản phẩm" : "Thêm sản phẩm mới"}</title>
    <link rel="stylesheet" href="./css/admin-form.css">
</head>
<body>

<div class="form-box">
    <h2>${product != null && product.id > 0 ? "Cập nhật sản phẩm" : "Thêm sản phẩm mới"}</h2>
    <c:if test="${not empty error}">
        <p style="color: red; text-align: center; font-weight: bold; margin-bottom: 10px;">
                ${error}
        </p>
    </c:if>

    <form action="admin-product" method="post">
        <input type="hidden" name="action" value="save">
        <input type="hidden" name="id" value="${product.id}">

        <div class="form-group">
            <label>Tên sản phẩm</label>
            <input type="text" name="name" value="${product.name}" required placeholder="Nhập tên sản phẩm...">
        </div>

        <div class="form-row">
            <div style="flex: 1;">
                <label>Giá gốc (VNĐ)</label>
                <input type="number" name="price" value="${product.price > 0 ? product.price : ''}" required>
            </div>
            <div style="flex: 1;">
                <label>Giá bán (VNĐ)</label>
                <input type="number" name="sale_price" value="${product.salePrice > 0 ? product.salePrice : ''}" required>
            </div>
        </div>

        <div class="form-group">
            <label>Danh mục</label>
            <select name="category_id">
                <option value="1" ${product.categoryId == 1 ? 'selected' : ''}>Thức ăn cho chó</option>
                <option value="2" ${product.categoryId == 2 ? 'selected' : ''}>Thức ăn cho mèo</option>
                <option value="3" ${product.categoryId == 3 ? 'selected' : ''}>Thiết bị thông minh</option>
                <option value="4" ${product.categoryId == 4 ? 'selected' : ''}>Khác</option>
            </select>
        </div>

        <div class="form-group">
            <label>Mô tả chi tiết</label>
            <textarea name="desc" rows="4" placeholder="Nhập mô tả sản phẩm...">${product.description}</textarea>
        </div>

        <button type="submit" class="btn-submit">
            ${product != null && product.id > 0 ? "Lưu cập nhật" : "Thêm sản phẩm"}
        </button>
    </form>

    <a href="admin-product" class="btn-back">Quay lại danh sách</a>
</div>
</body>
</html>