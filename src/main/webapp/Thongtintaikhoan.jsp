<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pet-Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/Thongtintaikhoan.css">
    <link rel="stylesheet" href="../style.css">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</head>

<body>
<!-- Header -->
<header class="header-top">
    <div class="logo">
        <a href="../index.html"><img src="assets/img/logo.avif" alt="Paddy.vn"/></a>
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

            <a href="../HTML/Thongtintaikhoan.jsp" class="icon-item">
                <i class="fa fa-user"></i>
                <p>Thông tin tài khoản</p>
            </a>

            <a href="Cart.jsp" class="icon-item">
                <i class="fa fa-cart-arrow-down"></i>
                <p>Giỏ Hàng</p>
            </a>
        </div>
    </div>
</header>

<!-- MENU -->
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

<!-- ==== GIAO DIỆN TÀI KHOẢN ==== -->
<div class="account-container">

    <!-- Sidebar -->
    <div class="sidebar">

        <!-- Tài khoản của tôi -->
        <details open class="menu-group">
            <summary class="menu-title">Tài Khoản Của Tôi</summary>
            <ul class="menu-list">
                <li class="menu-item"><a href="#profile" class="tab-link active">Hồ Sơ</a></li>
                <li class="menu-item"><a href="#address" class="tab-link">Địa Chỉ</a></li>
                <li class="menu-item"><a href="#password" class="tab-link">Đổi Mật Khẩu</a></li>
            </ul>
        </details>

        <!-- Đơn hàng -->
        <ul class="menu-list">
            <li class="menu-item">
                <a href="#orders" class="tab-link">Đơn Hàng</a>
            </li>
        </ul>

        <!-- Đăng Xuất -->
        <ul class="logout-list">
            <li class="logout-item">
                <i class="fa-solid fa-right-from-bracket logout-icon"></i>
                <a href="DangNhap.jsp" class="logout-link">Đăng Xuất</a>
            </li>
        </ul>

    </div>

    <!-- Content -->
    <div class="content">

        <!-- Hồ sơ -->
        <div id="profile" class="section active">
            <h2>Hồ Sơ Của Tôi</h2>
            <p>Quản lý thông tin hồ sơ để bảo mật tài khoản</p>

            <div class="profile-wrapper">
                <div class="left-form">
                    <div class="form-group">
                        <div class="form-label">Tên đăng nhập</div>
                        <div class="form-input">
                            <input type="text" readonly>
                            <p class="note">Tên đăng nhập chỉ được đổi một lần.</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-label">Tên</div>
                        <div class="form-input"><input type="text"></div>
                    </div>
                    <div class="form-group">
                        <div class="form-label">Email</div>
                        <div class="form-input"><input type="text" value="@gmail.com" readonly></div>
                    </div>
                    <div class="form-group">
                        <div class="form-label">Số điện thoại</div>
                        <div class="form-input"><input type="text" readonly></div>
                    </div>
                    <div class="form-group">
                        <div class="form-label">Giới tính</div>
                        <div class="gender-box">
                            <input type="radio" name="gender"> Nam
                            <input type="radio" name="gender"> Nữ
                            <input type="radio" name="gender"> Khác
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-label">Ngày sinh</div>
                        <div class="form-input"><input type="text" readonly></div>
                    </div>
                    <button class="save-btn">Lưu</button>
                </div>
            </div>
        </div>

        <!-- Địa Chỉ -->
        <div id="address" class="section">
            <h2>Địa Chỉ</h2>
            <p><strong>Nguyễn văn a</strong> — 190010097</p>
            <p>Số 3 Yên Lãng<br>Quận Tây Hồ, Hà Nội</p>
            <button class="btn-default">Mặc định</button>

        </div>

        <!--  ĐỔI MẬT KHẨU  -->
        <div id="password" class="section">
            <h2>Đổi Mật Khẩu</h2>
            <p>Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác.</p>

            <form action="change-password" method="post">

                <div class="form-group">
                    <label for="oldPassword">Mật khẩu hiện tại</label>
                    <input type="password"
                           id="oldPassword"
                           name="oldPassword"
                           placeholder="Nhập mật khẩu hiện tại"
                           required>
                </div>

                <div class="form-group">
                    <label for="newPassword">Mật khẩu mới</label>
                    <input type="password"
                           id="newPassword"
                           name="newPassword"
                           placeholder="Nhập mật khẩu mới"
                           required>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Nhập lại mật khẩu mới</label>
                    <input type="password"
                           id="confirmPassword"
                           name="confirmPassword"
                           placeholder="Nhập lại mật khẩu mới"
                           required>
                </div>

                <button type="submit" class="save-btn">
                    Lưu thay đổi
                </button>
            </form>

            <!-- HIỂN THỊ LỖI -->
            <c:if test="${not empty error}">
                <p style="color:red; margin-top:10px;">
                        ${error}
                </p>
            </c:if>

            <!-- HIỂN THỊ THÀNH CÔNG -->
            <c:if test="${not empty success}">
                <p style="color:green; margin-top:10px;">
                        ${success}
                </p>
            </c:if>
        </div>


        <!-- Đơn Hàng -->
        <div id="orders" class="section">
            <h2>Đơn Hàng</h2>
            <div class="order-tabs">
                <button class="order-tab active">Tất cả</button>
                <button class="order-tab">Hoàn thành</button>
                <button class="order-tab">Đã hủy</button>
                <button class="order-tab">Trả hàng/Hoàn tiền</button>
            </div>
            <div class="order-search">
                <input type="text" placeholder="Bạn có thể tìm theo tên Shop, ID đơn hàng hoặc Tên sản phẩm">
            </div>
            <div class="order-empty">
                <img src="https://cf.shopee.vn/file/e5fafff459d8e7f0c1e2f7d0e9e2c3d4" class="empty-img">
                <p>Chưa có đơn hàng</p>
            </div>
        </div>

    </div>
</div>
<script src="js/Thongtintk.js" defer></script>
</body>
</html>
