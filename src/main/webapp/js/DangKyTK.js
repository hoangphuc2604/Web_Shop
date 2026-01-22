function fillDemo() {
    document.getElementById("fullname").value = "Nguyễn Văn A";
    document.getElementById("email").value = "user@gmail.com";
    document.getElementById("password").value = "abc12345";
    document.getElementById("confirm").value = "abc12345";
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
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirm").value;

    // mật khẩu: ít nhất 8 ký tự, có chữ ,số
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,32}$/;

    if (!fullname) {
        document.getElementById("err-fullname").innerText =
            "Vui lòng nhập họ và tên";
        valid = false;
    }

    if (!email) {
        document.getElementById("err-email").innerText =
            "Vui lòng nhập email";
        valid = false;
    } else if (!email.includes("@")) {
        document.getElementById("err-email").innerText =
            "Email không hợp lệ";
        valid = false;
    }

    if (!password) {
        document.getElementById("err-password").innerText =
            "Vui lòng nhập mật khẩu";
        valid = false;
    } else if (!passwordRegex.test(password)) {
        document.getElementById("err-password").innerText =
            "Mật khẩu phải từ 8–32 ký tự, gồm chữ và số";
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


    return valid;
}
