<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/DangKyTK.css">
</head>
<body>

<div class="register-box">
    <h2>Đăng ký tài khoản</h2>

    <label>Họ và tên</label>
    <input type="text" id="fullname" placeholder="Nhập họ và tên">
    <small class="error" id="err-fullname"></small>

    <label>Email</label>
    <input type="email" id="email" placeholder="Nhập email">
    <small class="error" id="err-email"></small>

    <label>Tên đăng nhập</label>
    <input type="text" id="username" placeholder="Nhập tên đăng nhập">
    <small class="error" id="err-username"></small>

    <label>Mật khẩu</label>
    <input type="password" id="password" placeholder="Tạo mật khẩu">
    <small class="error" id="err-password"></small>

    <label>Nhập lại mật khẩu</label>
    <input type="password" id="confirm" placeholder="Nhập lại mật khẩu">
    <small class="error" id="err-confirm"></small>

    <button type="button" class="btn-register" onclick="register()">Đăng ký</button>


    <p>
        Đã có tài khoản? <a href="DangNhap.jsp">Đăng nhập</a>
    </p>
</div>

<script src="<%= request.getContextPath() %>/js/DangKyTK.js"></script>

</body>
</html>
