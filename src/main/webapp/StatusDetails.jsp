<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết trạng thái đơn hàng</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/SDetail_Style.css">
</head>
<body>
<!-- Header trên -->
<header class="header-top">
    <div class="logo">
        <a href="index"><img src="./assets/img/logo.avif" alt="Paddy.vn" />
        </a>
    </div>

    <form action="search" method="get" class="search-bar">
        <input type="text" name="txt" placeholder="Tìm kiếm sản phẩm..." value="${txtS}">
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>

    <script>
        // Kiểm tra xem user có tồn tại trong session không
        var isUserLoggedIn = ${not empty sessionScope.user};
    </script>
    <div class="right-info">
        <div class="hotline">
            <p>Hotline</p>
            <strong>086 767 7891</strong>
        </div>
        <div class="icons">
            <a href="wishlist" class="icon-item" onclick="checkLoginForWishlist(event, isUserLoggedIn)">
                <i class="fa fa-heart"></i>
                <p>Wishlist</p>
            </a>

            <c:if test="${not empty sessionScope.user}">
                <a href="Thongtintaikhoan.jsp" class="icon-item">
                    <i class="fa fa-user"></i>
                    <p>${sessionScope.user.username}</p>
                </a>
            </c:if>

            <c:if test="${empty sessionScope.user}">
                <a href="./DangNhap.jsp" class="icon-item">
                    <i class="fa fa-user"></i>
                    <p>Tài Khoản</p>
                </a>
            </c:if>

            <a href="./Cart.jsp" class="icon-item">
                <i class="fa fa-cart-arrow-down"></i>
                <c:if test="${sessionScope.cart != null && sessionScope.cart.totalQuantity > 0}">
                    <span style="position: absolute; top: -5px; right: 15px; background: red; color: white; border-radius: 50%; padding: 2px 6px; font-size: 10px; font-weight: bold;">
                            ${sessionScope.cart.totalQuantity}
                    </span>
                </c:if>
                <p>Giỏ Hàng</p>
            </a>
        </div>

    </div>
</header>

<!-- MENU GIỮ NGUYÊN CẤU TRÚC CŨ -->
<nav class="menu">
    <ul>
        <li><a href="#">Chó</a></li>
        <li><a href="#">Mèo</a></li>
        <li><a href="#">Thiết bị thông minh</a></li>
        <li><a href="#">Hàng mới về</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="#">Pagazine chăm Boss</a></li>
        <li><a href="#">News</a></li>
        <li><a href="#">Today's Sale</a></li>
    </ul>
</nav>
<!--Status details-->
<div class="status-detail">
    <div class="sd-container">
        <div class="sd-header">
            <h2>Chi tiết trạng thái đơn hàng</h2>
        </div>
        <div class="sd-content">
            <c:forEach items="${order.items}" var="item">
                <div class="sd-info" style="border-bottom: 1px solid #eee; padding-bottom: 15px; margin-bottom: 15px;">
                    <img src="${item.product.image}" alt="" class="sd-img">
                    <div class="sd-item">
                        <p class="sd-name">${item.product.name}</p>
                        <p class="sd-weight">Số lượng: x${item.quantity}</p>
                        <p class="sd-price">Đơn giá: <fmt:formatNumber value="${item.unitPrice}" type="currency" currencySymbol="đ"/></p>
                    </div>
                </div>
            </c:forEach>

            <div style="text-align: right; font-weight: bold; font-size: 20px; margin: 20px 0; color: #d0011b;">
                TỔNG CỘNG: ${order.formattedTotal}
            </div>

            <c:choose>
                <c:when test="${order.status == 'Cancelled'}">
                    <h3 style="color:red; text-align:center; border: 2px solid red; padding: 10px; margin: 20px;">ĐƠN HÀNG ĐÃ BỊ HỦY</h3>
                </c:when>
                <c:otherwise>
                    <div class="sd-line">
                        <i class="bi bi-dot active"></i>
                        <div class="line-between ${order.status == 'Packing' || order.status == 'Shipping' || order.status == 'Delivered' ? 'active' : ''}"></div>
                        <i class="bi bi-dot ${order.status == 'Packing' || order.status == 'Shipping' || order.status == 'Delivered' ? 'active' : ''}"></i>
                        <div class="line-between ${order.status == 'Shipping' || order.status == 'Delivered' ? 'active' : ''}"></div>
                        <i class="bi bi-dot ${order.status == 'Shipping' || order.status == 'Delivered' ? 'active' : ''}"></i>
                        <div class="line-between ${order.status == 'Delivered' ? 'active' : ''}"></div>
                        <i class="bi bi-dot ${order.status == 'Delivered' ? 'active' : ''}"></i>
                    </div>
                    <div class="sd-labels">
                        <span class="label active">Đã đặt</span>
                        <span class="label ${order.status == 'Packing' || order.status == 'Shipping' || order.status == 'Delivered' ? 'active' : ''}">Đóng gói</span>
                        <span class="label ${order.status == 'Shipping' || order.status == 'Delivered' ? 'active' : ''}">Vận chuyển</span>
                        <span class="label ${order.status == 'Delivered' ? 'active' : ''}">Đã giao</span>
                    </div>
                </c:otherwise>
            </c:choose>

            <div class="sd-return">
                <a href="order-history"><button class="bt-return">Quay lại</button></a>
            </div>
        </div>
    </div>
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
                    <img class="small_icon" src="./assets/img/fbicon.png" alt>
                </a>
                <a href="https://www.instagram.com/paddypetshop/" class="social_btn">
                    <img class="small_icon" src="./assets/img/insicon.png" alt>
                </a>
                <a href="https://www.tiktok.com/@paddypetshop" class="social_btn">
                    <img class="small_icon" src="./assets/img/tiktokicon.png" alt>
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