<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pate cho mèo con</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/collections_Style.css">
</head>
<body>
<header class="header-top">
    <div class="logo">
        <a href="./index.jsp"><img src="assets/img/logo.avif" alt="Paddy.vn" />
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
                <p>Giỏ Hàng</p>
            </a>
        </div>

    </div>
</header>

<nav class="menu">
    <ul>
        <!--Dog-->
        <li class="has-dropdown">
            <a href="#">Chó</a>
        </li>

        <!--Cat-->
        <li class="has-dropdown">
            <a href="#">Mèo</a>
        </li>

        <!--Thiết bị thông minh-->
        <li class="has-dropdown">
            <a href="#">Thiết bị thông minh</a>
        </li>

        <li><a href="#">Hàng mới về</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="#">Pagazine chăm Boss</a></li>
        <li><a href="#">News</a></li>
        <li><a href="#">Today's Sale</a></li>
    </ul>
</nav>
<!--Collections-->
<div class="collections-shop">
    <div class="cs-header">
        <a href="./index.jsp">Paddy Pet Shop</a> >
        <a href="#">Collections</a> >
        <span>${categoryName}</span>
    </div>
    <div class="cs-container">
        <div class="cs-title-header">
            <h2>${categoryName}</h2>
        </div>
        <hr>
        <div class="cs-layout">
            <div class="cs-main">
                <div class="cs-softbox">
                    <span class="sb-label">SẮP XẾP THEO</span>
                    <select class="soft-select" onchange="window.location.href='collections?cid=${cid}&page=1&sort='+this.value">
                        <option value="alpha-asc" ${sort == 'alpha-asc' ? 'selected' : ''}>Thứ tự bảng chữ cái (từ A-Z)</option>
                        <option value="alpha-desc" ${sort == 'alpha-desc' ? 'selected' : ''}>Thứ tự bảng chữ cái (từ Z-A)</option>
                        <option value="price-desc" ${sort == 'price-desc' ? 'selected' : ''}>Giá giảm dần</option>
                        <option value="price-asc" ${sort == 'price-asc' ? 'selected' : ''}>Giá tăng dần</option>
                    </select>
                </div>
                <div class="cs-slider">
                    <div class="cs-wrapper">
                        <c:forEach items="${listProduct}" var="p">
                            <a href="product?id=${p.id}" style="display: contents;">
                                <div class="cs-item">
                                    <img src="${p.image}" alt="${p.name}">
                                    <h4>${p.categoryName}</h4>
                                    <p>${p.name}</p>
                                    <div class="cs-rating">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <span>(5 đánh giá)</span>
                                    </div>
                                    <c:choose>
                                        <c:when test="${p.salePrice < p.price}">
                                            <span class="price1">${p.formattedOriginalPrice}</span>
                                            <span class="price2">${p.formattedPrice}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="price">${p.formattedPrice}</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${sessionScope.wishlistIds.contains(p.id)}">
                                            <i class="fa-solid fa-heart cs-icon liked"
                                               style="color: red;"
                                               onclick="event.preventDefault(); toggleWishlist(this, ${p.id}, isUserLoggedIn)">
                                            </i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa-regular fa-heart cs-icon"
                                               onclick="event.preventDefault(); toggleWishlist(this, ${p.id}, isUserLoggedIn)">
                                            </i>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </a>
                        </c:forEach>
                        <c:if test="${empty listProduct}">
                            <p style="margin: 20px;">Đang cập nhật sản phẩm cho danh mục này...</p>
                        </c:if>
                    </div>
                </div>
                <div class="cs-see-more">
                    <div class="pagination-wrapper" style="display: flex; align-items: center; justify-content: center; gap: 20px;">
                        <c:choose>
                            <c:when test="${tag > 1}">
                                <a href="collections?cid=${cid}&page=${tag - 1}&sort=${sort}" class="page-btn">
                                    <i class="fa-solid fa-circle-chevron-left"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <span class="page-btn disabled">
                                    <i class="fa-solid fa-circle-chevron-left"></i>
                                </span>
                            </c:otherwise>
                        </c:choose>
                        <span class="page-info" style="font-weight: bold; font-size: 16px; color: #333;">
                            Trang ${tag} / ${endPage}
                        </span>
                        <c:choose>
                            <c:when test="${tag < endPage}">
                                <a href="collections?cid=${cid}&page=${tag + 1}&sort=${sort}" class="page-btn">
                                    <i class="fa-solid fa-circle-chevron-right"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <span class="page-btn disabled">
                                    <i class="fa-solid fa-circle-chevron-right"></i>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="cs-description">
            <h3>Thông tin danh mục</h3>
            <div class="cs-des-container">
                <c:if test="${not empty categoryDesc}">
                    <c:out value="${categoryDesc}" escapeXml="false" />
                </c:if>

                <c:if test="${empty categoryDesc}">
                    <p>Hiện chưa có mô tả cho danh mục này.</p>
                </c:if>
            </div>
        </div>
    </div>
    <script src="js/Collections.js"></script>
    <script src="./js/wishlist.js"></script>
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