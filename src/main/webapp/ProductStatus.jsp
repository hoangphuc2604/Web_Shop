<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trạng thái đơn hàng</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/PStatus_Style.css">
    <script src="js/PStatus.js?V=4"></script>

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
                <a href="account" class="icon-item">
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
<c:if test="${not empty sessionScope.success}">
    <div style="color: green; background: #e6ffe6; padding: 15px; text-align: center; font-weight: bold; border: 1px solid green; margin: 10px auto; max-width: 800px; border-radius: 5px;">
            ${sessionScope.success}
    </div>
    <c:remove var="success" scope="session" />
</c:if>

<c:if test="${not empty sessionScope.error}">
    <div style="color: red; background: #ffe6e6; padding: 15px; text-align: center; font-weight: bold; border: 1px solid red; margin: 10px auto; max-width: 800px; border-radius: 5px;">
            ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session" />
</c:if>


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
                    <th>TỔNG TIỀN</th>
                    <th>TRẠNG THÁI</th>
                    <th></th> <th style="text-align: center;">XÁC THỰC</th> </tr>
                </thead>

                <c:forEach items="${listOrders}" var="o">
                    <tbody style="${o.fake ? 'background-color: #fff5f5;' : ''}"> <tr class="ps-item">

                        <td class="ps-info">
                            <img src="${o.items[0].product.image}" alt="" class="ps-img">
                            <div>
                                <p class="ps-name">${o.items[0].product.name}</p>
                                <c:if test="${o.items.size() > 1}">
                                    <p class="ps-weight" style="font-size: 12px; color: #888;">(và ${o.items.size() - 1} sản phẩm khác)</p>
                                </c:if>
                                <p class="ps-brand">Mã đơn: #${o.id}</p>
                            </div>
                        </td>

                        <td class="ps-price" style="font-weight: bold; color: #d32f2f;">${o.formattedTotal}</td>

                        <td class="${o.status == 'Delivered' ? 'ps-stt-shipped' : (o.status == 'Cancelled' ? 'ps-stt' : 'ps-stt-shipping')}">
                            <c:choose>
                                <c:when test="${o.fake}">
                                    <span style="color: red; font-weight: bold; font-size: 14px;">Tạm ngưng (Lỗi bảo mật)</span>
                                </c:when>
                                <c:otherwise>
                                    ${o.statusVN}
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td class="ps-details" style="text-align: center;">
                            <a href="order-detail?id=${o.id}">
                                <button class="ps-button">Xem chi tiết</button>
                            </a>
                        </td>

                        <td class="ps-verify-col" style="text-align: center; border-left: 1px dashed #ddd;">
                            <c:choose>

                                <c:when test="${o.fake}">
                                    <span class="status-unverified" style="color: red; font-weight: bold; display: block;">Đã ký (Bị sửa đổi)</span>
                                    <button class="btn-sign" style="background-color: #ff9800; border-color: #ff9800; margin-top: 5px;" onclick="openResignModal('${o.id}')">Ký Lại</button>
                                </c:when>

                                <c:when test="${not empty o.digitalSig}">
                                    <span class="status-verified" style="color: green; font-weight: bold; display: block;">
                                        Đã ký <i class="fa-solid fa-circle-check" style="font-size: 1.2em; vertical-align: middle;"></i>
                                    </span>
                                </c:when>

                                <c:otherwise>
                                    <span class="status-unverified" style="color: gray; font-weight: bold; display: block;">Chưa ký</span>
                                    <button class="btn-sign" style="margin-top: 5px;" onclick="openSigModal('${o.id}')">Ký ngay</button>
                                </c:otherwise>
                            </c:choose>
                        </td>

                    </tr>
                    </tbody>
                </c:forEach>

                <c:if test="${empty listOrders}">
                    <tbody><tr><td colspan="5" style="text-align:center; padding:30px;">Bạn chưa có đơn hàng nào.</td></tr></tbody>
                </c:if>
            </table>
        </div>
    </div>
</div>

<div id="sigModal" class="modal-overlay">
    <div class="modal-box">
        <div class="modal-header">
            <h2 class="modal-title">Xác thực chữ kí điện tử</h2>
            <div class="modal-header-actions">
                <a href="create-signature.jsp" target="_blank" class="btn-goto-create-sig">Tải về tool tạo chữ ký</a>
                <span id="closeModalBtn" class="close-icon" onclick="document.getElementById('sigModal').style.display='none';">&times;</span>
            </div>
        </div>

        <div class="modal-body">
            <p class="warning-text">Vui lòng tạo chữ kí với văn bản dưới đây để xác nhận chính chủ</p>
            <div class="input-group">
                <label for="hashDisplay">Mã Hash:</label>
                <textarea id="hashDisplay" class="hash-textarea" readonly></textarea>
            </div>
            <div class="input-group">
                <label for="sigInput">Nhập chữ kí điện tử:</label>
                <textarea id="sigInput" class="signature-textarea"></textarea>
            </div>
            <form id="verifyForm" action="SignOrderController" method="post" style="display: none;">
                <input type="hidden" id="modalOrderId" name="orderId" value="">
                <input type="hidden" id="modalOrderHash" name="orderHash" value="">
                <input type="hidden" id="modalDigitalSig" name="digitalSig" value="">
            </form>
        </div>

        <div class="modal-footer">
            <button type="button" id="confirmSigBtn" class="btn-confirm-sig" onclick="submitSignature()">Xác Nhận Chữ Kí</button>
        </div>
    </div>
</div>
<div id="resignModal" class="modal-overlay" style="display: none;">
    <div class="modal-box">
        <div class="modal-header">
            <h2 class="modal-title" style="color: #d32f2f;">Yêu cầu ký lại đơn hàng</h2>
            <div class="modal-header-actions">
                <span class="close-icon" onclick="document.getElementById('resignModal').style.display='none';">&times;</span>
            </div>
        </div>

        <div class="modal-body">
            <p class="warning-text">Hệ thống phát hiện thông tin đơn hàng đã bị thay đổi. Vui lòng ký lại xác nhận với mã Hash mới dưới đây để tiếp tục.</p>

            <form action="order-history?action=update-sig" method="post" id="resignForm">
                <input type="hidden" name="orderId" id="resignOrderId">

                <div class="input-group">
                    <label style="font-weight: bold;">Mã Hash mới (Đã cập nhật):</label>
                    <textarea id="resignHashDisplay" name="orderHash" class="hash-textarea" readonly style="background: #f5f5f5;"></textarea>
                </div>

                <div class="input-group">
                    <label style="font-weight: bold;">Nhập chữ ký điện tử của bạn:</label>
                    <textarea name="digitalSig" id="resignSigInput" class="signature-textarea" required placeholder="Dán chữ ký vào đây..."></textarea>
                </div>

                <div class="modal-footer" style="display: flex; justify-content: flex-end; gap: 15px; margin-top: 15px; padding: 0;">
                    <button type="button" class="btn-confirm-sig" style="background: #ccc; width: auto; padding: 10px 25px; margin: 0; box-sizing: border-box;" onclick="document.getElementById('resignModal').style.display='none';">Hủy</button>
                    <button type="submit" class="btn-confirm-sig" style="background: #28a745; width: auto; padding: 10px 25px; margin: 0; box-sizing: border-box;">Xác nhận ký lại</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function openResignModal(orderId) {
        document.getElementById('resignOrderId').value = orderId;

        fetch('order-history?action=get-new-hash&id=' + orderId)
            .then(response => response.text())
            .then(hash => {
                document.getElementById('resignHashDisplay').value = hash.trim();
                document.getElementById('resignSigInput').value = '';
                document.getElementById('resignModal').style.display = 'flex';
            })
            .catch(error => alert("Lỗi tải dữ liệu Hash: " + error));
    }
</script>

</body>
</html>