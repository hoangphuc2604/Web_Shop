<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/DangNhap.css">

</head>
<body>
<div class="login-box">
    <h2>Đăng nhập</h2>

    <label>Tài khoản</label>
    <input type="text" id="email" placeholder="Nhập tài khoản">
    <small class="error" id="err-email"></small>

    <label>Mật khẩu</label>
    <input type="password" id="password" placeholder="Nhập mật khẩu">
    <small class="error" id="err-password"></small>

    <button type="button" class="btn-login" onclick="login()">Đăng nhập</button>

    <p>
        Chưa có tài khoản? <a href="DangKyTK.jsp">Đăng ký</a>
    </p>
</div>
<script src="<%= request.getContextPath() %>/js/DangNhap.js"></script>

</body>
</html>
