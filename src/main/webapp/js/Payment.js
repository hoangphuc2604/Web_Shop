document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("sigModal");
    var openBtn = document.getElementById("inputPrivateKeyBtn");
    var closeBtn = document.getElementById("closeModalBtn");
    var confirmBtn = document.getElementById("confirmSigBtn");
    var sigInput = document.getElementById("sigInput");
    var hashInput = document.getElementById("hashDisplay");

    var isVerifiedSecurely = false;

    openBtn.onclick = function() {
        var email = document.querySelector('input[name="email"]').value;
        var phone = document.querySelector('input[name="phone"]').value;
        var fullname = document.querySelector('input[name="fullname"]').value;
        var address = document.querySelector('input[name="address"]').value;

        if (!email || !phone || !fullname || !address) {
            alert("Vui lòng điền đầy đủ thông tin nhận hàng trước khi xác thực!");
            return;
        }
        var params = new URLSearchParams({ email: email, phone: phone, fullname: fullname, address: address });
        fetch('generate-hash?' + params.toString())
            .then(response => response.text())
            .then(hashResult => {
                hashInput.value = hashResult.trim();
                modal.style.display = "block";
            });
    };

    closeBtn.onclick = function() { modal.style.display = "none"; }

    confirmBtn.onclick = function() {
        var signature = sigInput.value.trim();
        var hash = hashInput.value.trim();
        var keyId = document.getElementById("keyId").value;

        if (signature === "") {
            alert("Vui lòng nhập chữ kí điện tử!");
            return;
        }

        var formData = new URLSearchParams();
        formData.append("orderHash", hash);
        formData.append("digitalSig", signature);
        formData.append("keyId", keyId);

        fetch('verify-signature', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData.toString()
        })
            .then(response => response.text())
            .then(result => {
                if (result === "SUCCESS") {
                    isVerifiedSecurely = true;
                    document.getElementById("digitalSig").value = signature;
                    document.getElementById("orderHash").value = hash;

                    alert("XÁC THỰC THÀNH CÔNG: Chữ ký số hoàn toàn trùng khớp với hệ thống!");
                    modal.style.display = "none";
                    openBtn.innerHTML = "Đã xác thực chữ ký";
                    openBtn.style.backgroundColor = "#28a745";
                } else {
                    isVerifiedSecurely = false;
                    alert("CẢNH BÁO BẢO MẬT: " + result);
                }
            })
            .catch(error => alert("Lỗi kết nối xác thực: " + error));
    };

    var checkoutForm = document.querySelector('form[action="payment"]');
    if (checkoutForm) {
        checkoutForm.addEventListener("submit", function(event) {
            if (!isVerifiedSecurely) {
                event.preventDefault();
                alert("LỖI: Chữ ký điện tử của bạn chưa được xác thực thành công. Không thể đặt hàng!");
            }
        });
    }
});