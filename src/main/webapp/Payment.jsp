<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="vn.edu.hcmuaf.fit.Web_Shop.cart.Cart" %>
<%@ page import="vn.edu.hcmuaf.fit.Web_Shop.cart.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thanh toán sản phẩm</title>
    <link rel="stylesheet" href="css/Payment_style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="js/Payment.js"></script>
</head>
<body>
<%
    // Lấy giỏ hàng từ session
    Cart cart = (Cart) session.getAttribute("cart");

    // Tạo công cụ định dạng tiền tệ Việt Nam (VNĐ)
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    // Tính toán số liệu để dùng ở dưới
    double totalAmount = (cart != null) ? cart.total() : 0;
    double shippingFee = 25000; // Phí ship mặc định
    double finalTotal = totalAmount + shippingFee;
%>
<header>
    <div class="pay-header">
        <div class="payment-container">
            <h2>Paddy Pet Shop</h2>
            <div class="bag-shop">
                <a href="Cart.jsp">
                    <i class="fa-solid fa-bag-shopping"></i>
                </a>
            </div>
        </div>
    </div>
</header>
<hr>
<form action="payment" method="post">
    <div class="payment-page">
        <div class="payment-container">

            <div class="payment-left">
                <div class="pay-inf">
                    <h2>Thông tin người nhận hàng</h2>
                    <input type="text" name="email" placeholder="Email" required
                           value="${requestScope.preEmail != null ? requestScope.preEmail : ''}">
                    <input type="text" name="phone" placeholder="Số điện thoại" required
                           value="${requestScope.prePhone != null ? requestScope.prePhone : ''}">
                </div>

                <div class="ship-inf">
                    <div class="shipping-form">
                        <div class="form-group">
                            <input type="text" name="fullname" class="input" placeholder="Họ và tên đầy đủ" required
                                   value="${requestScope.preName != null ? requestScope.preName : ''}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="address" class="input" placeholder="Địa chỉ (Số nhà, Tên đường, Phường/Xã...)" required
                                   value="${requestScope.preAddress != null ? requestScope.preAddress : ''}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="note" class="input" placeholder="Ghi chú đơn hàng (không bắt buộc)">
                        </div>

                        <div class="shipping-method">
                            <h4>Phương thức vận chuyển</h4>
                            <div class="radio-method-shipping">
                                <div class="radio-item">
                                    <input type="radio" name="ship" value="25000" checked>
                                    <span>Vận Chuyển Nhanh (HCM)</span>
                                    <span class="ship-price">25.000₫</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="payment-method">
                    <h2>Thanh toán</h2>
                    <div class="radio-payment-method">
                        <div class="radio-item">
                            <input type="radio" name="payment" value="OnePAY" checked>
                            <span>OnePAY</span>
                        </div>
                        <div class="radio-item">
                            <input type="radio" name="payment" value="COD">
                            <span>Thanh toán khi nhận hàng (COD)</span>
                        </div>
                    </div>
                </div>

                <div class="bt-success">
                    <button type="submit" class="complete-order-btn">Hoàn tất đơn hàng</button>
                </div>
            </div>

            <div class="payment-right">

                <div class="product-list-container">
                    <%
                        // Kiểm tra giỏ hàng có rỗng không
                        if (cart != null && cart.getTotalQuantity() > 0) {
                            List<CartItem> items = cart.getItems(); // Lấy list items từ Cart.java
                            for (CartItem item : items) {
                    %>
                    <div class="product-summary">
                        <img src="<%= item.getProduct().getImage() %>"
                             alt="<%= item.getProduct().getName() %>"
                             onerror="this.src='assets/img/no-image.png'">

                        <div class="product-info">
                            <p class="product-name"><%= item.getProduct().getName() %></p>
                            <p class="product-quantity">Số lượng: <%= item.getQuantity() %></p>
                            <p class="product-price">
                                <%= currencyVN.format(item.getPrice()) %>
                            </p>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <p style="text-align: center; color: #666; padding: 20px;">
                        Giỏ hàng trống
                    </p>
                    <%
                        }
                    %>
                </div>

                <div class="discount">
                    <input type="text" placeholder="Mã giảm giá">
                    <button type="button">Áp dụng</button>
                </div>

                <div class="summary">
                    <p>Tổng sản phẩm:
                        <span><%= currencyVN.format(totalAmount) %></span>
                    </p>
                    <p>Phí vận chuyển:
                        <span><%= currencyVN.format(shippingFee) %></span>
                    </p>
                    <p>Giảm giá: <span>0₫</span></p>

                    <p class="total">Tổng cộng:
                        <span><%= currencyVN.format(finalTotal) %></span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>