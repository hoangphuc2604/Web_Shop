<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${detail.name} - Paddy Shop</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/Product_Style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

</head>
<body>
<header class="header-top">
    <div class="logo">
        <a href="./index.jsp"><img src="./assets/img/logo.avif" alt="Paddy.vn" />
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
                    <a href="DangNhap.jsp" class="icon-item">
                        <i class="fa fa-user"></i>
                        <p>Tài khoản</p>
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
    </div>
</header>

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


        <li><a href="./collections.jsp">Hàng mới về</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="./Blog.jsp">Pagazine chăm Boss</a></li>
        <li><a href="#">News</a></li>
        <li><a href="#">Today's Sale</a></li>
    </ul>
</nav>


<!--Body product content-->
<div id="container">
    <div id="product_detail">
        <div class="product-detail-left">
            <div class="product-thumbnails">
                <i class="fa-solid fa-chevron-up"></i>
                <img src="${proDetail.image}" alt="thumb">
                <img src="${proDetail.image}" alt="thumb">
                <img src="${proDetail.image}" alt="thumb">
                <img src="${proDetail.image}" alt="thumb">
                <i class="fa-solid fa-chevron-down"></i>
            </div>

            <div class="product-main-image">
                <img src="${proDetail.image}" alt="${proDetail.name}">
            </div>
        </div>

        <div class="product-detail-right">
            <div class="breadcrumb">
                <a href="./index.jsp">Trang chủ</a> >
                <a href="./collections">Sản phẩm</a> >
                <span>${proDetail.name}</span>
            </div>

            <h2>${proDetail.name}</h2>

            <div class="rating">
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <span>(Xem đánh giá ở bên dưới)</span>
            </div>

            <p class="product-brand">Thương hiệu: <span>${proDetail.categoryName}</span></p>

            <div class="product-price" id="display-price">
                <c:choose>
                    <c:when test="${not empty variants}">${variants[0].formattedPrice}</c:when>
                    <c:otherwise>${proDetail.formattedPrice}</c:otherwise>
                </c:choose>
            </div>

            <div class="product-size">
                <p>Khối lượng:</p>
                <div class="size-options">
                    <c:if test="${not empty variants}">
                        <c:forEach items="${variants}" var="v" varStatus="status">
                            <button type="button" class="variant-btn ${status.first ? 'active' : ''}"
                                    data-price="${v.formattedPrice}"
                                    data-id="${v.id}">
                                    ${v.formattedWeight}
                            </button>
                        </c:forEach>
                        <input type="hidden" id="selectedVariantId" value="${variants[0].id}">
                    </c:if>

                    <c:if test="${empty variants}">
                        <button class="active">Tiêu chuẩn</button>
                        <input type="hidden" id="selectedVariantId" value="">
                    </c:if>
                </div>
            </div>

            <div class="product-quantity">
                <p>Số lượng:</p>
                <div class="quantity-control">
                    <button class="minus" type="button">−</button>
                    <input type="text" id="quantityInput" value="1" readonly>
                    <button class="plus" type="button">+</button>
                </div>
            </div>

            <div class="product-total">
                Tổng số tiền: <span id="total-calc">
                    <c:choose>
                        <c:when test="${not empty variants}">${variants[0].formattedPrice}</c:when>
                        <c:otherwise>${proDetail.formattedPrice}</c:otherwise>
                    </c:choose>
                </span>
            </div>

            <div class="product-buttons">
                <button class="add-cart" id="addToCartBtn">
                    <i class="fa-solid fa-cart-shopping"></i> Thêm vào giỏ hàng
                </button>

                <c:choose>
                    <c:when test="${sessionScope.wishlistIds.contains(proDetail.id)}">
                        <i class="fa-solid fa-heart" style="cursor: pointer; font-size: 24px; color: red;" onclick="toggleWishlist(this, ${proDetail.id}, isUserLoggedIn)"></i>
                    </c:when>
                    <c:otherwise>
                        <i class="fa-regular fa-heart" style="cursor: pointer; font-size: 24px;" onclick="toggleWishlist(this, ${proDetail.id}, isUserLoggedIn)"></i>
                    </c:otherwise>
                </c:choose>
                <button class="share"><i class="fa-solid fa-share-nodes"></i></button>
            </div>

            <div class="product-shipping">
                <i class="fa-solid fa-truck-fast"></i>
                <div>
                    <p><strong>Miễn phí vận chuyển</strong></p>
                    <p>Tối đa 30k cho đơn hàng từ 500k<br>Hoả tốc 4h trong nội thành HCM</p>
                </div>
            </div>
        </div>
    </div>

    <div class="product-description">
        <div class="description-title">
            <h3>Mô Tả</h3>
            <div class="underline"></div>
        </div>

        <h4>Chi tiết sản phẩm</h4>
        <p>${proDetail.description}</p>

        <div class="review-section">
            <h2 class="review-title">ĐÁNH GIÁ SẢN PHẨM</h2>
            <div class="review-summary-box">
                <div class="left-rating">
                    <div class="avg-score">5 <span>trên 5</span></div>
                    <div class="summary-stars">
                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/wishlist.js"></script>
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
<script>
    const currentProductId = ${proDetail.id};
</script>
<script src="./js/Product.js?v=1"></script>
</body>
</html>