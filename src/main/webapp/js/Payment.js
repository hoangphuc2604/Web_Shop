document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("sigModal");
    var closeBtn = document.getElementById("closeModalBtn");
    var confirmBtn = document.getElementById("confirmSigBtn");
    var sigInput = document.getElementById("sigInput");
    var hashInput = document.getElementById("hashDisplay");
    var checkoutForm = document.querySelector('form[action="payment"]');
    if (closeBtn) {
        closeBtn.onclick = closeModal;
    }
    if (confirmBtn) {
        confirmBtn.onclick = confirmSignature;
    }
    if (checkoutForm) {
        checkoutForm.addEventListener("submit", handleFormSubmit);
    }
    function closeModal() {
        modal.style.display = "none";
    }
    function handleFormSubmit(event) {
        var sigValue = document.getElementById("digitalSig").value;

    if (!sigValue) {
        event.preventDefault();

        var email = document.querySelector('input[name="email"]').value;
        var phone = document.querySelector('input[name="phone"]').value;
        var fullname = document.querySelector('input[name="fullname"]').value;
        var address = document.querySelector('input[name="address"]').value;
        if (!email || !phone || !fullname || !address) {
            alert("Vui lòng điền đầy đủ thông tin nhận hàng trước khi xác thực!");
            return;
        }
        //// Gọi API lấy mã Hash và hiện Pop-up
        var params = new URLSearchParams({ email: email, phone: phone, fullname: fullname, address: address });
        fetch('generate-hash?' + params.toString())
            .then(response => response.text())
            .then(hashResult => {
                hashInput.value = hashResult.trim();
                modal.style.display = "block";
            })
            .catch(error => alert("Lỗi kết nối hệ thống: " + error));
    }}
    function confirmSignature() {
        var signature = sigInput.value.trim();
        var hash = hashInput.value.trim();

        if (signature === "") {
            alert("Vui lòng nhập chữ kí điện tử!");
            return;
        }
        document.getElementById("digitalSig").value = signature;
        document.getElementById("orderHash").value = hash;
        closeModal();
        checkoutForm.submit();
    }
});