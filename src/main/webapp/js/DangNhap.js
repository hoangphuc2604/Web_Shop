function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let userData = localStorage.getItem("user_" + username);

    if (!userData) {
        alert("Tài khoản không tồn tại");
        return;
    }

    let user = JSON.parse(userData);

    if (user.password !== password) {
        alert("Sai mật khẩu");
        return;
    }

    alert("Đăng nhập thành công!");
    window.location.href = "../index.html"; // đổi nếu cần
}
