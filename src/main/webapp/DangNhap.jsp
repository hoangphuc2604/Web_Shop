<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="css/DangNhap.css">
    <script src="js/DangNhap.js"></script>
</head>
<body>
<div class="login-box">
    <h2>Đăng nhập</h2>

    <label for="username">Tên đăng nhập</label>
    <input type="text" id="username" placeholder="Nhập tên đăng nhập" required>

    <label for="password">Mật khẩu</label>
    <input type="password" id="password" placeholder="Nhập mật khẩu" required>

    <a href="../index.html" class="btn-login" onclick="goToIndex()">Đăng nhập</a>

    <p class="login-link">
        Chưa có tài khoản? <a href="DangKyTK.jsp">Đăng ký</a>
    </p>
    <a href="Forget-password.jsp">Quên mật khẩu</a>
</div>
</body>
</html>
