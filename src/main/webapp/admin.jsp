<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="./css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>

<body>

<div class="sidebar">
    <h2>Admin Control</h2>
    <a class="tab-link active" href="#dashboard">Bảng điều khiển</a>
    <a class="tab-link" href="#san-pham">Sản phẩm</a>
    <a class="tab-link" href="#danh-muc">Danh mục</a>
    <a class="tab-link" href="#nguoi-dung">Người dùng</a>
    <a class="tab-link" href="#don-hang">Đơn hàng</a>
    <a class="tab-link" href="#thanh-toan">Thanh toán</a>
    <a class="tab-link" href="#doanh-thu">Doanh thu</a>
    <a class="tab-link" href="#logout">Đăng xuất</a>
</div>

<div class="main">
    <div class="topbar">
        <span>Bảng điều khiển</span>
        <span>Xin chào, Admin</span>
    </div>

    <div id="dashboard" class="section active">
        <div class="stats">
            <div class="card">Tổng số sản phẩm <div class="number">200</div></div>
            <div class="card">Người dùng <div class="number">20</div></div>
            <div class="card">Đơn hàng <div class="number">20</div></div>
            <div class="card">Doanh thu tháng <div class="number">15.800.000 VND</div></div>
        </div>
    </div>

    <div id="san-pham" class="section">
        <div class="title-bar">
            <h2>Danh sách sản phẩm</h2>
            <a href="admin-product?action=add" class="btn-add" style="text-decoration: none;">
                + Thêm sản phẩm
            </a>
        </div>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Giá gốc</th>
                <th>Giá bán</th>
                <th>Danh mục</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listP}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <img src="${p.image}" alt="img" style="width: 50px; height: 50px; object-fit: contain; border: 1px solid #ddd; border-radius: 4px;">
                    </td>
                    <td style="max-width: 200px;">${p.name}</td>
                    <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/></td>
                    <td style="color: red; font-weight: bold;"><fmt:formatNumber value="${p.salePrice}" type="currency" currencySymbol="đ"/></td>
                    <td>${p.categoryName}</td>
                    <td style="display: flex; gap: 5px; align-items: center; justify-content: center;">
                        <a href="admin-product?action=edit&id=${p.id}" class="btn-edit" style="text-decoration: none; white-space: nowrap;">
                            Sửa
                        </a>
                        <button class="btn-delete" onclick="deleteProduct('${p.id}')" style="margin: 0;">
                            Xóa
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="danh-muc" class="section"><h2>Danh sách danh mục</h2></div>
    <div id="nguoi-dung" class="section"><h2>Danh sách người dùng</h2></div>
    <div id="don-hang" class="section"><h2>Quản lý đơn hàng</h2></div>
    <div id="thanh-toan" class="section"><h2>Thanh toán</h2></div>
    <div id="doanh-thu" class="section"><h2>Doanh thu</h2></div>
    <div id="logout" class="section"><h2>Đăng xuất</h2></div>

</div>

<script src="./js/admin.js"></script>

</body>
</html>