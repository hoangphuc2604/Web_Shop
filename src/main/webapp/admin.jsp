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
    <a class="${empty activeTab ? 'tab-link active' : 'tab-link'}" href="#dashboard">Bảng điều khiển</a>

    <a class="${activeTab == 'san-pham' ? 'tab-link active' : 'tab-link'}"
       href="${pageContext.request.contextPath}/admin-product">
        Sản phẩm
    </a>

    <a class="tab-link" href="#danh-muc">Danh mục</a>
    <a class="${activeTab == 'nguoi-dung' ? 'tab-link active' : 'tab-link'}"
       href="${pageContext.request.contextPath}/admin-user">
        Người dùng
    </a>
    <a class="${activeTab == 'don-hang' ? 'tab-link active' : 'tab-link'}"
       href="${pageContext.request.contextPath}/admin-order">
        Đơn hàng
    </a>
    <a class="tab-link" href="#thanh-toan">Thanh toán</a>
    <a class="tab-link" href="#doanh-thu">Doanh thu</a>
    <a class="tab-link" href="#logout">Đăng xuất</a>
</div>

<div class="main">
    <div class="topbar">
        <span>Bảng điều khiển</span>
        <span>Xin chào, Admin</span>
    </div>

    <div id="dashboard" class="section ${empty activeTab ? 'active' : ''}">
        <div class="stats">
            <div class="card">Tổng số sản phẩm <div class="number">200</div></div>
            <div class="card">Người dùng <div class="number">20</div></div>
            <div class="card">Đơn hàng <div class="number">20</div></div>
            <div class="card">Doanh thu tháng <div class="number">15.800.000 VND</div></div>
        </div>
    </div>

    <div id="san-pham" class="section ${activeTab == 'san-pham' ? 'active' : ''}">
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
                            <i class="fa-regular fa-pen-to-square"></i>
                        </a>
                        <button class="btn-delete" onclick="deleteProduct('${p.id}')" style="margin: 0;">
                            <i class="fa-regular fa-trash-can"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="danh-muc" class="section"><h2>Danh sách danh mục</h2></div>
    <div id="nguoi-dung" class="section ${activeTab == 'nguoi-dung' ? 'active' : ''}">
        <div class="title-bar">
            <h2>Danh sách người dùng</h2>
        </div>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Tên người dùng</th>
                <th>Trạng thái</th>
                <th>Vai trò</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listU}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.email}</td>
                    <td>${u.username}</td>

                    <td>
                        <c:choose>
                            <c:when test="${u.locked == 1}">
                                <span style="color: red; font-weight: bold;">Đã bị khóa</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green; font-weight: bold;">Bình thường</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${u.role == 'ADMIN'}">
                                <span style="color: blue; font-weight: bold;">ADMIN</span>
                            </c:when>
                            <c:otherwise>
                                Người dùng
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td style="display: flex; gap: 5px; align-items: center; justify-content: center;">
                        <c:if test="${u.locked == 0}">
                            <button class="btn-delete" style="cursor: pointer;">Khóa</button>
                        </c:if>
                        <c:if test="${u.locked == 1}">
                            <button class="btn-add" style="cursor: pointer; background-color: green;">Mở khóa</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="don-hang" class="section ${activeTab == 'don-hang' ? 'active' : ''}">
        <div class="title-bar">
            <h2>Quản lý đơn hàng</h2>
        </div>

        <table>
            <thead>
            <tr>
                <th>ID đơn</th>
                <th>ID người dùng</th>
                <th>Trạng thái</th>
                <th>Tổng tiền</th>
                <th>Chi tiết</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOrders}" var="o">
                <tr>
                    <td>#${o.id}</td>
                    <td>
                        <c:choose>
                            <c:when test="${o.userId > 0}">
                                ${o.userId}
                            </c:when>
                            <c:otherwise>
                                Khách
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                    <span style="font-weight: bold;">
                            ${o.statusVN}
                    </span>
                    </td>

                    <td style="color: red; font-weight: bold;">
                            ${o.formattedTotal}
                    </td>

                    <td>
                        <button class="btn-edit"
                                onclick="toggleOrderDetail(${o.id})">
                            Xem
                        </button>
                    </td>
                </tr>

                <!-- Chi tiết đơn -->
                <tr id="order-detail-${o.id}" style="display: none; background: #f9f9f9;">
                    <td colspan="5">
                        <table style="width: 100%;">
                            <thead>
                            <tr>
                                <th>Sản phẩm</th>
                                <th>Ảnh</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${o.items}" var="i">
                                <tr>
                                    <td>${i.product.name}</td>
                                    <td>
                                        <img src="${i.product.image}"
                                             style="width: 50px; height: 50px; object-fit: contain;">
                                    </td>
                                    <td>${i.quantity}</td>
                                    <td>
                                        <fmt:formatNumber value="${i.unitPrice}"
                                                          type="currency"
                                                          currencySymbol="đ"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="thanh-toan" class="section"><h2>Thanh toán</h2></div>
    <div id="doanh-thu" class="section"><h2>Doanh thu</h2></div>
    <div id="logout" class="section"><h2>Đăng xuất</h2></div>

</div>
<script src="./js/admin.js"></script>
</body>
</html>