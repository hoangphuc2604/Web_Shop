<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết trạng thái đơn hàng</title>
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/SDetail_Style.css">
</head>
<body>
<!-- Header trên -->
<header class="header-top">
    <div class="logo">
        <a href="../index.html"><img src="assets/img/logo.avif" alt="Paddy.vn" />
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
            <a href="collections.jsp">Chó</a>
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
            <a href="collections.jsp">Mèo</a>
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
            <a href="collections.jsp">Thiết bị thông minh</a>
            <div class="dropdown-small">
                <p>Máy Ăn Uống Tự Động</p>
                <p>Nhà Vệ Sinh Tự Động</p>
                <p>Đồ Chơi Tương Tác</p>
            </div>
        </li>

        <li><a href="collections.jsp">Hàng mới về</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="Blog.jsp">Pagazine chăm Boss</a></li>
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
            <div class="sd-info">
                <img src="assets/img/fav4.webp" alt="" class="sd-img">
                <div class="sd-item">
                    <p class="sd-name">Thức Ăn Hạt Cho Mèo Con Royal Canin Kitten 36</p>
                    <p class="sd-weight">400g</p>
                    <p class="sd-brand">Royal Canin</p>
                    <p class="sd-price">Giá: <span class="sd-price-detail">135.000đ</span></p>
                </div>
            </div>
            <div class="sd-line">
                <i class="bi bi-dot active"></i>
                <div class="line-between active"></div>
                <i class="bi bi-dot active"></i>
                <div class="line-between active"></div>
                <i class="bi bi-dot active"></i>
                <div class="line-between"></div>
                <i class="bi bi-dot"></i>
            </div>

            <div class="sd-labels">
                <span class="label active">Đã đặt hàng</span>
                <span class="label active">Đang đóng gói</span>
                <span class="label active">Đang vận chuyển</span>
                <span class="label">Đã giao</span>
            </div>
            <div class="sd-return">
                <a href="ProductStatus.jsp">
                    <button class="bt-return">Trở lại trang "Trạng thái đơn hàng"</button>
                </a>
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
                    <img class="small_icon" src="/src/main/webapp/assets/img/fbicon.png" alt>
                </a>
                <a href="https://www.instagram.com/paddypetshop/" class="social_btn">
                    <img class="small_icon" src="/src/main/webapp/assets/img/insicon.png" alt>
                </a>
                <a href="https://www.tiktok.com/@paddypetshop" class="social_btn">
                    <img class="small_icon" src="/src/main/webapp/assets/img/tiktokicon.png" alt>
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