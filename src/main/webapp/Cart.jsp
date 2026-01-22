<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pet-Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="css/Cart_style.css">

</head>
<body>
<!-- Header trên -->
<header class="header-top">
    <div class="logo">
        <a href="index.jsp"><img src="assets/img/logo.avif" alt="Paddy.vn" />
        </a>
    </div>

    <div class="search-bar">
        <input type="text" placeholder="Tìm kiếm sản phẩm...">
        <button><i class="fa fa-search"></i></button>
    </div>

    <div class="right-info">
        <div class="hotline">
            <p>Hotline</p>
            <strong>086 767 7891</strong>
        </div>
        <div class="icons">
            <a href="wishlist.jsp" class="icon-item">
                <i class="fa fa-heart"></i>
                <p>Wishlist</p>
            </a>

            <a href="DangNhap.jsp" class="icon-item">
                <i class="fa fa-user"></i>
                <p>Tài Khoản</p>
            </a>

            <a href="Cart.jsp" class="icon-item">
                <i class="fa fa-cart-arrow-down"></i>
                <p>Giỏ Hàng</p>
            </a>
        </div>

    </div>
</header>

<!-- MENU GIỮ NGUYÊN CẤU TRÚC CŨ -->
<nav class="menu">
    <ul>
        <!-- CHÓ -->
        <li class="has-dropdown">
            <a href="#">Chó</a>
            <div class="dropdown">
                <div class="dropdown-container">
                    <div>
                        <h4>Thức Ăn Cho Chó</h4>
                        <p>Thức Ăn Hạt</p>
                        <p>Thức Ăn Ướt</p>
                        <p>Thức Ăn Hữu Cơ</p>
                        <p>Thức Ăn Không Ngũ Cốc</p>
                    </div>
                    <div>
                        <h4>Chăm Sóc Vệ Sinh Cún</h4>
                        <p>Vệ Sinh Răng Miệng</p>
                        <p>Sữa Tắm & Phụ Kiện</p>
                        <p>Xịt Khử Mùi</p>
                    </div>
                    <div>
                        <h4>Bánh Thưởng</h4>
                        <p>Bánh Quy</p>
                        <p>Súp Thưởng</p>
                        <p>Thịt Sấy Khô</p>
                    </div>
                    <div>
                        <h4>Phụ Kiện</h4>
                        <p>Vòng Cổ & Dây Dắt</p>
                        <p>Nệm - Chuồng Cho Cún</p>
                        <p>Tã Lót & Khay Vệ Sinh</p>
                    </div>
                    <div>
                        <h4>Chăm Sóc Sức Khoẻ</h4>
                        <p>Vitamin</p>
                        <p>Trị Ve Rận</p>
                        <p>Thực Phẩm Chức Năng</p>
                    </div>
                    <div>
                        <h4>Vận Chuyển</h4>
                        <p>Balo & Túi Vận Chuyển</p>
                        <p>Lồng Vận Chuyển</p>
                    </div>
                </div>
            </div>
        </li>

        <!-- MÈO -->
        <li class="has-dropdown">
            <a href="#">Mèo</a>
            <div class="dropdown">
                <div class="dropdown-container dropdown-meo">
                    <div>
                        <h4>Thức Ăn Cho Mèo</h4>
                        <p>Thức Ăn Hạt</p>
                        <p>Thức Ăn Ướt</p>
                        <p>Thức Ăn Cho Mèo Con</p>
                        <p>Thức Ăn Cho Mèo Trưởng Thành</p>
                    </div>
                    <div>
                        <h4>Chăm Sóc Vệ Sinh Mèo</h4>
                        <p>Cát Vệ Sinh</p>
                        <p>Khử Mùi</p>
                        <p>Sữa Tắm & Dụng Cụ Tắm</p>
                    </div>
                    <div>
                        <h4>Phụ Kiện & Đồ Chơi</h4>
                        <p>Chuồng & Nệm Mèo</p>
                        <p>Vòng Cổ & Dây Dắt</p>
                        <p>Đồ Chơi Gãi Móng</p>
                    </div>
                    <div>
                        <h4>Chăm Sóc Sức Khỏe</h4>
                        <p>Vitamin</p>
                        <p>Xổ Giun & Ve Rận</p>
                    </div>
                    <div>
                        <h4>Vận Chuyển</h4>
                        <p>Balo & Túi Vận Chuyển</p>
                        <p>Lồng Vận Chuyển</p>
                    </div>
                </div>
            </div>
        </li>

        <!-- THIẾT BỊ THÔNG MINH -->
        <li class="has-dropdown">
            <a href="#">Thiết bị thông minh</a>
            <div class="dropdown-small">
                <p>Máy Ăn Uống Tự Động</p>
                <p>Nhà Vệ Sinh Tự Động</p>
                <p>Đồ Chơi Tương Tác</p>
            </div>
        </li>

        <li><a href="#">Hàng mới về</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="#">Pagazine chăm Boss</a></li>
        <li><a href="#">News</a></li>
        <li><a href="#">Today's Sale</a></li>
    </ul>
</nav>
<!--Body content-->
<div id="container">
    <div class="cart-page">
        <div class="cart-content">

            <!-- Bảng sản phẩm -->
            <div class="cart-left">
                <h2>GIỎ HÀNG CỦA BẠN</h2>
                <table class="cart-table">
                    <thead>
                    <tr>
                        <th>SẢN PHẨM</th>
                        <th>GIÁ</th>
                        <th>SỐ LƯỢNG</th>
                        <th>TỔNG</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${sessionScope.cart == null || sessionScope.cart.totalQuantity() == 0}">
                        <tr>
                            <td colspan="5" style="text-align:center; padding: 20px;">Giỏ hàng trống</td>
                        </tr>
                    </c:if>

                    <c:forEach items="${sessionScope.cart.items}" var="item">
                        <tr class="cart-item">
                            <td class="product-info">
                                <img src="${item.product.image}" alt="${item.product.name}" class="product-img">
                                <div>
                                    <p class="product-name">${item.product.name}</p>
                                    <p class="product-brand">ID: ${item.product.id}</p>
                                </div>
                            </td>

                            <td class="product-price">
                                <fmt:formatNumber value="${item.price}" type="currency"/>
                            </td>

                            <td class="product-qty">
                                <a href="update-cart?id=${item.product.id}&action=dec" class="qty-btn decrease" style="text-decoration: none; display: inline-block; text-align: center;">−</a>

                                <input type="text" value="${item.quantity}" class="qty-input" readonly>

                                <a href="update-cart?id=${item.product.id}&action=inc" class="qty-btn increase" style="text-decoration: none; display: inline-block; text-align: center;">+</a>
                            </td>

                            <td class="product-total">
                                <fmt:formatNumber value="${item.price * item.quantity}" type="currency"/>
                            </td>

                            <td class="remove-cell">
                                <a href="remove-cart?id=${item.product.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">
                                    <i class="fa-solid fa-x remove-item"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <textarea class="note-box" placeholder="Ghi chú giao hàng"></textarea>
            </div>

            <!-- Tổng thanh toán -->
            <div class="cart-right">
                <h2>TỔNG SỐ TIỀN</h2>
                <div class="cart-summary">

                    <div class="summary-item">
                        <p>Tổng số tiền:</p>
                        <p class="summary-value">
                            <c:choose>
                                <c:when test="${sessionScope.cart != null}">
                                    <fmt:formatNumber value="${sessionScope.cart.total()}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                                </c:when>
                                <c:otherwise>0 đ</c:otherwise>
                            </c:choose>
                        </p>
                    </div>

                    <div class="promo-code">
                        <label for="coupon">Mã khuyến mãi</label>
                        <input type="text" id="coupon" placeholder="Nhập mã khuyến mãi...">
                    </div>

                    <div class="summary-item total">
                        <p>TỔNG</p>
                        <p class="summary-value">
                            <c:choose>
                                <c:when test="${sessionScope.cart != null}">
                                    <fmt:formatNumber value="${sessionScope.cart.total()}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                                </c:when>
                                <c:otherwise>0 đ</c:otherwise>
                            </c:choose>
                        </p>
                    </div>

                    <a href="Payment.jsp">
                        <button class="checkout-btn">Đi Đến Trang Thanh Toán</button>
                    </a>

                    <a href="index.jsp">
                        <button class="continue-btn">Tiếp Tục Mua Sắm</button>
                    </a>

                </div>
            </div>

        </div>
    </div>

    <script src="js/Cart.js"></script>
</div>

<!--Footer-->
<footer class="footer">
    <div class="footer-top">
        <div class="ft_container">
            <h3 class="L1_style">
                <span class="pix_text">Thành viên Paddiers</span>
            </h3>
            <h5 class="L2_style">
                <span class="pix_text">Đăng ký thành viên ngay hôm nay để nhận email về sản phẩm mới và chương trình khuyến mãi của Paddy</span>
            </h5>
            <div class="email_box">
                <input type="text" placeholder="Email của bạn..." required>
                <button type="submit" class="btn_signup">Đăng ký</button>
            </div>
            <div class="ft_col">
                <div class="list_col hover_col">
                    <h4>Shop</h4>
                    <p>Dành Cho Chó</p>
                    <p>Dành Cho Mèo</p>
                    <p>Thương Hiệu</p>
                    <p>Blogs</p>
                    <p>Bộ Sưu Tập</p>
                </div>
                <div class="list_col hover_col">
                    <h4>Paddy Pet Shop</h4>
                    <p>Giới Thiệu</p>
                    <p>Thành Viên Paddier</p>
                    <p>Điều Khoản Sử Dụng</p>
                    <p>Tuyển Dụng</p>
                </div>
                <div class="list_col hover_col">
                    <h4>Hỗ Trợ Khách Hàng</h4>
                    <p>Chính Sách Đổi Trả Hàng</p>
                    <p>Phương Thức Vận Chuyển</p>
                    <p>Chính Sách Bảo Mật</p>
                    <p>Phương Thức Thanh Toán</p>
                    <p>Chính Sách Hoàn Tiền</p>
                </div>
                <div class="list_col">
                    <h4>Liên Hệ</h4>
                    <p>CÔNG TY CỔ PHẦN THƯƠNG MẠI & DỊCH VỤ PADDY</p>
                    <p>MST: 0316459054</p>
                    <p>116 Nguyễn Văn Thủ, Phường Tân Định, Thành phố Hồ Chí Minh, Việt Nam</p>
                    <div class="icon_ft">
                        <div class="call-item"><i class="fa-solid fa-phone"></i><p>Hotline: 0867677891</p></div>
                        <div class="email-item"><i class="fa-solid fa-envelope"></i><p>Email: contact@paddy.vn</p></div>
                    </div>
                </div>
            </div>
            <div class="icon_social">
                <a href="https://www.facebook.com/PaddyPetShop" class="social_btn">
                    <img class="small_icon" src="assets/img/fbicon.png" alt>
                </a>
                <a href="https://www.instagram.com/paddypetshop/" class="social_btn">
                    <img class="small_icon" src="assets/img/insicon.png" alt>
                </a>
                <a href="https://www.tiktok.com/@paddypetshop" class="social_btn">
                    <img class="small_icon" src="assets/img/tiktokicon.png" alt>
                </a>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="ft_container">
            <div class="f_style">
                <span class="pix_text">
                    @2024 Paddy VN. All Rights Reserved.
                </span>
            </div>
        </div>
    </div>
</footer>
</body>
</html>