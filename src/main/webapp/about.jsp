<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pet-Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="css/about.css">
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
<!--Body-->

<div class="container">
    <h1 class="title">VỀ PADDY</h1>
    <h2 class="heading">Chào mừng đến với Paddy.vn!</h2>
    <p class="about-text">
        Paddy.vn tự hào là nhà bán lẻ hàng đầu chuyên cung cấp các sản phẩm cao cấp cho chó mèo, từ thức ăn, snack, phụ kiện, thời trang, đến mỹ phẩm chăm sóc và đồ dùng vệ sinh từ những thương hiệu uy tín trên thế giới.
    </p>
    <p class="about-text">
        Năm 2020 đánh dấu cột mốc quan trọng khi Paddy.vn chính thức khai trương cửa hàng đầu tiên và triển khai thành công hệ thống bán hàng đa kênh, bao gồm website chính thức và các nền tảng thương mại điện tử hàng đầu như Shopee, Lazada, và Tiktok. Hiện tại, Paddy đã mở rộng quy mô với 3 cửa hàng tại thành phố Hồ Chí Minh, mang đến hơn 3.000 sản phẩm chất lượng cao từ các thương hiệu nổi tiếng như Royal Canin, Nutrience, Taste of the Wild, Natural Core, Zenith, Nekko, Ganador, Minino, Kit Cat, The Pet, và nhiều hơn nữa.
    </p>
    <p class="about-text">
        Tại Paddy.vn, chúng tôi không chỉ cam kết cung cấp sản phẩm chất lượng với giá cả hợp lý mà còn hướng đến việc mang lại trải nghiệm mua sắm thoải mái và tiện lợi nhất cho khách hàng. Đội ngũ nhân viên tư vấn tận tâm, dịch vụ giao hàng nhanh chóng, cùng quy trình xử lý yêu cầu chuyên nghiệp, tất cả đã giúp Paddy.vn xây dựng được niềm tin vững chắc từ phía khách hàng. Chúng tôi luôn nỗ lực không ngừng để trở thành người bạn đồng hành đáng tin cậy của mọi gia đình nuôi thú cưng.
    </p>
</div>
<div class="about-banner">
    <img src="assets/img/about.webp" alt="" class="about-image">
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