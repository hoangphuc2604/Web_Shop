<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả tìm kiếm - Pet Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/Blog.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/Search-result.css">
</head>
<body>
<!-- Header trên -->
<header class="header-top">
    <div class="logo">
        <a href="index"><img src="assets/img/logo.avif" alt="Paddy.vn" /></a>
    </div>

    <form action="search" method="get" class="search-bar">
        <input type="text" name="txt" placeholder="Tìm kiếm sản phẩm..." value="${txtS}">
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>

    <script>
        // Biến kiểm tra đăng nhập cho Wishlist.js dùng
        var isUserLoggedIn = ${not empty sessionScope.user};
    </script>

    <div class="right-info">
        <div class="hotline">
            <p>Hotline</p><strong>086 767 7891</strong>
        </div>
        <div class="icons">
            <a href="wishlist" class="icon-item" onclick="checkLoginForWishlist(event, isUserLoggedIn)">
                <i class="fa fa-heart"></i><p>Wishlist</p>
            </a>

            <c:if test="${not empty sessionScope.user}">
                <a href="Thongtintaikhoan.jsp" class="icon-item">
                    <i class="fa fa-user"></i><p>${sessionScope.user.username}</p>
                </a>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <a href="DangNhap.jsp" class="icon-item">
                    <i class="fa fa-user"></i><p>Tài Khoản</p>
                </a>
            </c:if>

            <a href="Cart.jsp" class="icon-item">
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
        <!-- CHÓ -->
        <li class="has-dropdown">
            <a href="./collections.jsp">Chó</a>
        </li>

        <!-- MÈO -->
        <li class="has-dropdown">
            <a href="./collections.jsp">Mèo</a>
        </li>

        <!-- THIẾT BỊ THÔNG MINH -->
        <li class="has-dropdown">
            <a href="./collections.jsp">Thiết bị thông minh</a>
        </li>

        <li><a href="./collections.jsp">Hàng mới về</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="./collections.jsp">Pagazine chăm Boss</a></li>
        <li><a href="#">News</a></li>
        <li><a href="#">Today's Sale</a></li>
    </ul>
</nav>

<!--Body-->
<div class="search-container">
    <div class="search-top">
        <h1>TÌM THẤY <span id="result-count" style="color: #234BBB;">${resultCount}</span> KẾT QUẢ CHO “<span id="query-text">${txtS}</span>”</h1>
    </div>

    <div class="search-layout">
        <main class="search-main">
            <div class="product-grid" id="product-grid" style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px;">
                <c:forEach items="${listSearch}" var="p">
                    <div class="dp-item" style="width: 100%; margin: 0; background: #fff; border-radius: 10px; padding: 10px; position: relative; transition: 0.3s;">

                        <div class="img-container" style="position: relative; overflow: hidden; border-radius: 8px;">
                            <a href="product?id=${p.id}">
                                <img src="${p.image}" alt="${p.name}" style="width: 100%; height: 250px; object-fit: contain; display: block;">
                            </a>
                            <a href="add-cart?productId=${p.id}&quantity=1" class="add-to-cart-btn">
                                <i class="fa fa-cart-plus"></i> Thêm vào giỏ
                            </a>
                        </div>

                        <a href="product?id=${p.id}" class="info-link" style="margin-top: 10px;">
                            <h4 style="color: #234BBB; font-size: 14px; margin-bottom: 5px;">${p.categoryName}</h4>
                            <p style="color: #000000; font-size: 16px; font-weight: 500; height: 40px; overflow: hidden;">${p.name}</p>
                            <div style="margin-top: 5px;">
                                <c:choose>
                                    <c:when test="${p.salePrice < p.price}">
                                        <span class="price1" style="text-decoration: line-through; color: #000000; margin-right: 5px; font-size: 14px;">${p.formattedOriginalPrice}</span>
                                        <span class="price2" style="color: red; font-weight: bold; font-size: 16px;">${p.formattedPrice}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="price" style="color: #333; font-weight: bold; font-size: 16px;">${p.formattedPrice}</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </a>
                        <c:choose>
                            <c:when test="${sessionScope.wishlistIds.contains(p.id)}">
                                <i class="fa-solid fa-heart dp-icon liked"
                                   style="color: red; position: absolute; top: 20px; right: 15px; cursor: pointer;"
                                   onclick="toggleWishlist(this, ${p.id}, isUserLoggedIn)"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa-regular fa-heart dp-icon"
                                   style="color: #888; position: absolute; top: 20px; right: 15px; cursor: pointer;"
                                   onclick="toggleWishlist(this, ${p.id}, isUserLoggedIn)"></i>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>

            </div>

            <c:if test="${empty listSearch}">
                <div style="text-align: center; margin-top: 50px; width: 100%;">
                    <p>Không tìm thấy sản phẩm nào phù hợp.</p>
                    <a href="index" style="color: blue; text-decoration: underline;">Quay lại trang chủ</a>
                </div>
            </c:if>

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
<script src="js/wishlist.js"></script>
</body>
</html>