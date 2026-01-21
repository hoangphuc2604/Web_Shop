function fillDemo() {
    document.getElementById("fullname").value = "Nguyễn Văn A";
    document.getElementById("email").value = "user@gmail.com";
    document.getElementById("username").value = "user1";
    document.getElementById("password").value = "123456";
    document.getElementById("confirm").value = "123456";

    clearErrors();
}

function clearErrors() {
    document.querySelectorAll(".error").forEach(e => e.innerText = "");
}

function register() {
    clearErrors();
    let valid = true;

    const fullname = document.getElementById("fullname").value.trim();
    const email = document.getElementById("email").value.trim();
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirm").value;

    if (!fullname) {
        document.getElementById("err-fullname").innerText =
            "Vui lòng nhập họ và tên";
        valid = false;
    }

    if (!email) {
        document.getElementById("err-email").innerText =
            "Vui lòng nhập email";
        valid = false;
    }

    if (!username) {
        document.getElementById("err-username").innerText =
            "Vui lòng nhập tên đăng nhập";
        valid = false;
    }

    if (!password) {
        document.getElementById("err-password").innerText =
            "Vui lòng nhập mật khẩu";
        valid = false;
    }

    if (!confirm) {
        document.getElementById("err-confirm").innerText =
            "Vui lòng nhập lại mật khẩu";
        valid = false;
    } else if (password !== confirm) {
        document.getElementById("err-confirm").innerText =
            "Mật khẩu nhập lại không khớp";
        valid = false;
    }

    if (!valid) return;

    // ✅ Thành công
    alert("Đăng ký thành công!");
    window.location.href = "DangNhap.jsp";
}
