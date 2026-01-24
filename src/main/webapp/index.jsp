<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pet-Shop</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
                <div style="position: relative; display: inline-block;">
                    <i class="fa fa-cart-arrow-down"></i>
                    <c:if test="${sessionScope.cart != null && sessionScope.cart.totalQuantity > 0}">
                        <span class="cart-badge">
                                ${sessionScope.cart.totalQuantity}
                        </span>
                    </c:if>
                </div>
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

<div id="slider-container">
    <div id="slider-cont">
        <div class="slider">
                <div class="slider-item">
                    <img src="./assets/img/banner_web_1880_x_720_px_d0cd69bd-82c2-4d89-ba1f-8fd08f172cc9.webp" alt="">
                    <img src="./assets/img/pet_1.webp" alt="">
                    <img src="./assets/img/pet_2.webp" alt="">
                    <img src="./assets/img/pet_3.webp" alt="">
                </div>
                <div class="dots">
                    <i class="fa-solid fa-circle"></i>
                    <i class="fa-regular fa-circle"></i>
                    <i class="fa-regular fa-circle"></i>
                    <i class="fa-regular fa-circle"></i>
                </div>
        </div>
    </div>
</div>

<!--Support and policy-->

<div id="policy-section">
    <div class="policy-container">
        <div class="policy-item">
            <i class="fa-solid fa-bag-shopping"></i>
            <p>Miễn phí <br> Vận chuyển</p>
        </div>
        <div class="divider"></div>
        <div class="policy-item">
            <i class="fa-solid fa-certificate"></i>
            <p>Sản phẩm <br>Chính hãng</p>
        </div>
        <div class="divider"></div>
        <div class="policy-item">
            <i class="fa-solid fa-wallet"></i>
            <p>Thanh toán <br> Tiện lợi</p>
        </div>
        <div class="divider"></div>
        <div class="policy-item">
            <i class="fa-solid fa-phone-volume"></i>
            <p>Hỗ trợ <br> Chuyên nghiệp</p>
        </div>
    </div>
</div>
<!--Body content-->
<div id="banner-content">
    <div class="header-title">
        <h2>Mua sắm theo giống thú cưng</h2>
    </div>
    <div class="content-item">
        <a href="collections?cid=1" style="flex: 1;">
            <img src="./assets/img/dog_banner.webp" alt="Cún cưng" class="dog-item" style="width: 100%;">
        </a>

        <a href="collections?cid=2" style="flex: 1;">
            <img src="./assets/img/cat_banner.webp" alt="Mèo cưng" class="cat-item" style="width: 100%;">
        </a>
    </div>
</div>
<!-- Collections-container -->
<div id="collections-content">
    <div class="collections-header">
            <h2>Bộ Sưu Tập Cho Thú Cưng</h2>
    </div>
    <div class="collections-item">
        <a href="collections?cid=3">
            <div class="item">
                <img src="./assets/img/collections_1x.webp" alt="">
                <p>Pate Cho Thú Cưng</p>
            </div>
        </a>
        <a href="collections?cid=4">
            <div class="item">
                <img src="./assets/img/collections2.webp" alt="">
                <p>Thức Ăn Cho Thú Cưng</p>
            </div>
        </a>
        <a href="collections?cid=5">
            <div class="item">
                <img src="./assets/img/collections3.webp" alt="">
                <p>Cát Vệ Sinh Mèo</p>
            </div>
        </a>
        <a href="collections?cid=6">
            <div class="item">
                <img src="./assets/img/collections4.webp" alt="">
                <p>Sữa Tắm Cho Thú Cưng</p>
            </div>
        </a>
        <a href="collections?cid=7">
            <div class="item">
                <img src="./assets/img/collections5.webp" alt="">
                <p>Đồ Chơi Cho Thú Cưng</p>
            </div>
        </a>
        <a href="collections?cid=8">
            <div class="item">
                <img src="./assets/img/collections6.webp" alt="">
                <p>Nhà Cho Thú Cưng</p>
            </div>
        </a>
    </div>
</div>
<!--Discount product-->
<div class="discount-product">
    <div class="dp_container">
        <div class="dp_header">
            <h2>Sản phẩm giảm giá</h2>
            <a href="./collections.jsp" class="dp_see_all">Xem tất cả</a>
        </div>

        <div class="dp-slider">
            <div class="dp-wrapper">
                <c:forEach items="${listDiscount}" var="p">
                    <div class="dp-item">
                        <div class="img-container" style="position: relative; overflow: hidden;">
                            <a href="product?id=${p.id}">
                                <img src="${p.image}" alt="${p.name}" style="width: 100%; display: block;">
                            </a>
                            <a href="add-cart?productId=${p.id}&quantity=1" class="add-to-cart-btn">
                                <i class="fa fa-cart-plus"></i> Thêm vào giỏ
                            </a>
                        </div>
                        <a href="product?id=${p.id}" class="info-link">
                            <h4>${p.categoryName}</h4>
                            <p>${p.name}</p>
                            <span class="price1">${p.formattedOriginalPrice}</span>
                            <span class="price2">${p.formattedPrice}</span>
                        </a>
                        <c:choose>
                            <c:when test="${sessionScope.wishlistIds.contains(p.id)}">
                                <i class="fa-solid fa-heart dp-icon liked"
                                   style="cursor: pointer; color: red;"
                                   onclick="event.preventDefault(); toggleWishlist(this, ${p.id}, isUserLoggedIn)">
                                </i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa-regular fa-heart dp-icon"
                                   style="cursor: pointer;"
                                   onclick="event.preventDefault(); toggleWishlist(this, ${p.id}, isUserLoggedIn)">
                                </i>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!--Recommend product-->
<div class="recommend-items">
    <div class="deal_container">
        <div class="deal_header">
            <h2>Sản phẩm đề xuất</h2>
            <a href="./collections.jsp" class="deal_see_all">Xem tất cả</a>
        </div>

            <div class="deal-slider">
                <div class="deal-wrapper">
                    <c:forEach items="${listRecommend}" var="p">
                        <div class="deal-item">
                            <div class="img-container" style="position: relative; overflow: hidden;">
                                <a href="product?id=${p.id}">
                                    <img src="${p.image}" alt="${p.name}" style="width: 100%; display: block;">
                                </a>

                                <a href="add-cart?productId=${p.id}&quantity=1" class="add-to-cart-btn">
                                    <i class="fa fa-cart-plus"></i> Thêm vào giỏ
                                </a>
                            </div>

                            <a href="product?id=${p.id}" class="info-link">
                                <h4>${p.categoryName}</h4>
                                <p>${p.name}</p>
                                <c:choose>
                                    <c:when test="${p.salePrice < p.price}">
                                        <span class="price1">${p.formattedOriginalPrice}</span>
                                        <span class="price2">${p.formattedPrice}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="price">${p.formattedPrice}</span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            <c:choose>
                                <c:when test="${sessionScope.wishlistIds.contains(p.id)}">
                                    <i class="fa-solid fa-heart deal-icon liked"
                                       style="cursor: pointer; color: red;"
                                       onclick="event.preventDefault(); toggleWishlist(this, ${p.id}, isUserLoggedIn)">
                                    </i>
                                </c:when>
                                <c:otherwise>
                                    <i class="fa-regular fa-heart deal-icon"
                                       style="cursor: pointer;"
                                       onclick="event.preventDefault(); toggleWishlist(this, ${p.id}, isUserLoggedIn)">
                                    </i>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </div>
            </div>
<!--        <a href="./HTML/product.jsp">-->
<!--        </a>-->
    </div>
    <script src="./js/main.js"></script>
    <script src="./js/wishlist.js"></script>
</div>
<!--Trademark list-->
<div class="trademark_list">
    <div class="trademark_container">
        <div class="trademark_header">
            <h2>Top Thương Hiệu Boss Thích</h2>
            <p>Xem tất cả</p>
        </div>
        <div class="trademark_item">
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark1.png" alt="">
                </div>
                <p>Nutrience</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark2.png" alt="">
                </div>
                <p>Royal Canin</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark3.png" alt="">
                </div>
                <p>Kit Cat</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark4.png" alt="">
                </div>
                <p>Nekko</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark5.png" alt="">
                </div>
                <p>Monge</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark6.png" alt="">
                </div>
                <p>Silver Spoon</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark7.png" alt="">
                </div>
                <p>Me-O</p>
            </div>
            <div class="trade_logo">
                <div class="trade_img">
                    <img src="./assets/img/trademark8.png" alt="">
                </div>
                <p>Gimcat</p>
            </div>
        </div>
    </div>
</div>
<!--Store list-->
<div class="list-store">
    <div class="store-container">
        <div class="header-line">
            <hr>
            <h2 class="title-store">Mua Trực Tiếp Tại Cửa Hàng</h2>
        </div>
        <div class="store-item">
            <div class="store-img">
                <img src="./assets/img/cuahang1.webp" alt="Paddy Pet Shop - Trường Sa">
            </div>
            <h3>Paddy Pet Shop - Trường Sa</h3>
            <p>168 Trường Sa, P. Gia Định, Tp. HCM (Phường 1, Q. Bình Thạnh cũ)</p>
            <button>Chỉ đường</button>
        </div>
        <div class="store-item">
            <div class="store-img">
                <img src="./assets/img/cuahang2.webp" alt="Paddy Pet Shop - Nơ Trang Long">
            </div>
            <h3>Paddy Pet Shop - Nơ Trang Long</h3>
            <p>412/3 Nơ Trang Long, P. Bình Lợi Trung, Tp. HCM (Phường 13, Q.Bình Thạnh cũ)</p>
            <button>Chỉ đường</button>
        </div>
        <div class="store-item">
            <div class="store-img">
                <img src="./assets/img/cuahang3.webp" alt="Paddy Pet Shop - Trần Não">
            </div>
            <h3>Paddy Pet Shop - Trần Não</h3>
            <p>91B Trần Não, P. An Khánh, Tp. HCM (Quận 2 cũ)</p>
            <button>Chỉ đường</button>
        </div>
        <div class="store-item">
            <div class="store-img">
                <img src="./assets/img/cuahang4.webp" alt="Paddy Pet Shop - Nguyễn Thị Thập">
            </div>
            <h3>Paddy Pet Shop - Nguyễn Thị Thập</h3>
            <p>406 Nguyễn Thị Thập, P. Tân Hưng, Tp. HCM (P. Tân Quy, Q.7 cũ)</p>
            <button>Chỉ đường</button>
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