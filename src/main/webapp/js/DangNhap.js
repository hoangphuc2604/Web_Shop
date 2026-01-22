function clearErrors() {
    document.querySelectorAll(".error").forEach(e => e.innerText = "");
}

function login() {
    clearErrors();
    let valid = true;

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    if (!email) {
        document.getElementById("err-email").innerText = "Vui lòng nhập tài khoản";
        valid = false;
    }

    if (!password) {
        document.getElementById("err-password").innerText = "Vui lòng nhập mật khẩu";
        valid = false;
    }

    if (!valid) return;


    if (email === "admin@gmail.com" && password === "a123456") {
        alert("Đăng nhập thành công!");
        window.location.href = "../index.jsp";
    } else {
        document.getElementById("err-email").innerText = "Tài khoản hoặc mật khẩu không chính xác";
    }
}