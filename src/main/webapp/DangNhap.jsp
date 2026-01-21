<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="css/DangNhap.css">
    <script src="js/DangNhap.js"></script>
</head>
<body>
<div class="login-box">
    <h2>Đăng nhập</h2>

    <label>Tên đăng nhập</label>
    <input type="text" id="username" placeholder="Nhập tên đăng nhập">

    <label>Mật khẩu</label>
    <input type="password" id="password" placeholder="Nhập mật khẩu">

    <button class="btn-login" onclick="login()">Đăng nhập</button>

    <p>
        Chưa có tài khoản? <a href="DangKyTK.jsp">Đăng ký</a>
    </p>
</div>
</body>
</html>
