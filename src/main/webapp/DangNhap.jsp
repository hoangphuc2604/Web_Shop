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

    <form action="<%= request.getContextPath() %>/login" method="post">

        <label>Tài khoản (Email hoặc Username)</label>
        <input type="text" name="login" required>

        <label>Mật khẩu</label>
        <input type="password" name="password" required>

        <button type="submit" class="btn-login">Đăng nhập</button>
    </form>

    <p>Chưa có tài khoản? <a href="DangKyTK.jsp">Đăng ký</a></p>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p style="color:red;text-align:center;"><%= error %></p>
    <%
        }
    %>
</div>

</body>
</html>
