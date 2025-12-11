<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trạng thái đơn hàng</title>
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/PStatus_Style.css">
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
<!--Product status-->
<div class="product-status">
    <div class="ps-container">
        <div class="ps-header">
            <h2>Trạng thái đơn hàng đã đặt</h2>
        </div>
        <div class="ps-content">
            <table class="ps-table">
                <thead>
                <tr>
                    <th>SẢN PHẨM</th>
                    <th>GIÁ</th>
                    <th>TRẠNG THÁI</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr class="ps-item">
                    <td class="ps-info">
                        <img src="assets/img/fav4.webp" alt="" class="ps-img">
                        <div>
                            <p class="ps-name">Thức Ăn Hạt Cho Mèo Con Royal Canin Kitten 36</p>
                            <p class="ps-weight">400g</p>
                            <p class="ps-brand">Royal Canin</p>
                        </div>
                    </td>
                    <td class="ps-price">135.000đ</td>
                    <td class="ps-stt-shipping">Đang vận chuyển</td>
                    <td class="ps-details">
                        <a href="StatusDetails.jsp">
                            <button class="ps-button">Xem chi tiết</button>
                        </a>
                    </td>
                </tr>
                </tbody>
                <tbody>
                <tr class="ps-item">
                    <td class="ps-info">
                        <img src="assets/img/fav1.webp" alt="" class="ps-img">
                        <div>
                            <p class="ps-name">Thức Ăn Hạt Cho Mèo Trưởng Thành Nuôi Trong Nhà Royal Canin Indoor 27</p>
                            <p class="ps-weight">400g</p>
                            <p class="ps-brand">Royal Canin</p>
                        </div>
                    </td>
                    <td class="ps-price">132.000đ</td>
                    <td class="ps-stt">Đang đóng gói</td>
                    <td class="ps-details">
                        <a href="StatusDetails.jsp">
                            <button class="ps-button">Xem chi tiết</button>
                        </a>
                    </td>
                </tr>
                </tbody>
                <tbody>
                <tr class="ps-item">
                    <td class="ps-info">
                        <img src="assets/img/deal4.png" alt="" class="ps-img">
                        <div>
                            <p class="ps-name">Thức Ăn Hạt Cho Mèo Trưởng Thành Silver Spoon 1kg</p>
                            <p class="ps-weight">1kg</p>
                            <p class="ps-brand">Silver Spoon</p>
                        </div>
                    </td>
                    <td class="ps-price">150.000đ</td>
                    <td class="ps-stt-shipped">Đã giao</td>
                    <td class="ps-details">
                        <a href="StatusDetails.jsp">
                            <button class="ps-button">Xem chi tiết</button>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>