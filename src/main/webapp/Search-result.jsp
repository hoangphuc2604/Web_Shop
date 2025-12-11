<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pet-Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/Blog.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="css/Search-result.css">
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
<div class="search-container">
    <div class="search-top">
        <h1>TÌM THẤY <span id="result-count">4</span> KẾT QUẢ CHO “<span id="query-text">ROYAL-CANIN</span>”</h1>
    </div>

    <div class="search-layout">
        <!-- Left -->
        <div class="search-sidebar">
            <div class="side-section">
                <h3>CATEGORIES</h3>
                <div class="line"></div>
                <ul class="side-list">
                    <li>Chó</li>
                    <li>Mèo</li>
                    <li>Thiết bị thông minh</li>
                    <li>Hàng mới về</li>
                    <li>Thương hiệu</li>
                    <li>Pagazine chăm Boss</li>
                    <li>News</li>
                    <li>Khuyến Mãi Mới Nhất</li>
                </ul>
            </div>

            <div class="side-section">
                <h3>LOẠI SẢN PHẨM</h3>
                <div class="line"></div>
                <div class="filter-list">
                    <label><input type="checkbox"> Thức Ăn Ướt (48)</label>
                    <label><input type="checkbox"> Thức Ăn Khô (28)</label>
                    <label><input type="checkbox"> Pate Mèo (35)</label>
                    <label><input type="checkbox"> Pate Chó (2)</label>
                </div>
            </div>
        </div>

        <main class="search-main">
            <div class="search-controls">
                <div class="sort-left">
                    <label class="label-results">Sắp xếp theo:</label>
                    <select id="sort-select" class="sort-select">
                        <option value="relevance">Phù hợp nhất</option>
                        <option value="price-asc">Giá: thấp → cao</option>
                        <option value="price-desc">Giá: cao → thấp</option>
                        <option value="rating">Đánh giá</option>
                    </select>
                </div>

            </div>

            <div class="product-grid" id="product-grid">
                <div class="product-card">
                    <div class="img-wrap">
                        <img src="assets/img/fav1.webp" alt="product">
                        <i class="fa-regular fa-heart fav-icon" title="Thêm yêu thích"></i>
                    </div>
                    <a class="brand" href="product.jsp">Royal Canin</a>
                    <a class="product-name" href="product.jsp">
                        <h3 class="p-title">Thức Ăn Hạt Cho Mèo Con Royal Canin Kitten 36
                        </h3>
                    </a>

                    <div class="rating">
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i><i class="fa-regular fa-star"></i>
                        <span class="rating-count">2 đánh giá</span>
                    </div>
                    <div class="price">135.000₫</div>
                </div>

                <div class="product-card">
                    <div class="img-wrap">
                        <img src="assets/img/fav1.webp" alt="product">
                        <i class="fa-regular fa-heart fav-icon" title="Thêm yêu thích"></i>
                    </div>
                    <a class="brand" href="#">Royal Canin</a>
                    <h3 class="p-title">Thức Ăn Hạt Cho Mèo Sỏi Thận Royal Canin Urinary S/O</h3>
                    <div class="rating">
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                        <span class="rating-count">5 đánh giá</span>
                    </div>
                    <div class="price">185.000₫</div>
                </div>

                <div class="product-card">
                    <div class="img-wrap">
                        <img src="assets/img/fav1.webp" alt="product">
                        <i class="fa-regular fa-heart fav-icon" title="Thêm yêu thích"></i>
                    </div>
                    <a class="brand" href="#">Royal Canin</a>
                    <h3 class="p-title">Thức Ăn Hạt Cho Mèo Trưởng Thành Royal Canin Indoor</h3>
                    <div class="rating">
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                        <span class="rating-count">5 đánh giá</span>
                    </div>
                    <div class="price">132.000₫</div>
                </div>

                <div class="product-card">
                    <div class="img-wrap">
                        <img src="assets/img/fav1.webp" alt="product">
                        <i class="fa-regular fa-heart fav-icon" title="Thêm yêu thích"></i>
                    </div>
                    <a class="brand" href="#">Royal Canin</a>
                    <h3 class="p-title">Thức Ăn Hạt Cho Chó Con Poodle Royal Canin Puppy</h3>
                    <div class="rating">
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i><i class="fa-regular fa-star"></i>
                        <span class="rating-count">1 đánh giá</span>
                    </div>
                    <div class="price">189.000₫</div>
                </div>

            </div>

            <div class="pagination">
                <button class="page-btn"><</button>
                <button class="page-btn active">1</button>
                <button class="page-btn">2</button>
                <button class="page-btn">3</button>
                <button class="page-btn">></button>
            </div>

        </main>

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