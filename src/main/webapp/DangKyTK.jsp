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

    <form action="<%= request.getContextPath() %>/register"
          method="post"
          onsubmit="return register();">

        <label>Họ và tên</label>
        <input type="text" id="fullname" name="fullname" placeholder="Nhập họ và tên">
        <small class="error" id="err-fullname"></small>

        <label>Email (tài khoản đăng nhập)</label>
        <input type="email" id="email" name="email" placeholder="Nhập email">
        <small class="error" id="err-email"></small>

        <label>Mật khẩu</label>
        <input type="password" id="password" name="password" placeholder="Tạo mật khẩu">
        <small class="error" id="err-password"></small>

        <label>Nhập lại mật khẩu</label>
        <input type="password" id="confirm" name="confirm" placeholder="Nhập lại mật khẩu">
        <small class="error" id="err-confirm"></small>

        <button type="submit" class="btn-register">Đăng ký</button>
    </form>

    <p>
        Đã có tài khoản? <a href="DangNhap.jsp">Đăng nhập</a>
    </p>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p style="color:red; text-align:center;"><%= error %></p>
    <%
        }
    %>
</div>

<script src="<%= request.getContextPath() %>/js/DangKyTK.js"></script>
</body>
</html>
