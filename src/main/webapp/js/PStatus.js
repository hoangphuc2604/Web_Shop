function openSigModal(orderId) {
    var modal = document.getElementById('sigModal');
    modal.style.display = 'block';

    document.getElementById('modalOrderId').value = orderId;

    var hashInput = document.getElementById('hashDisplay');
    hashInput.value = "Đang tạo mã Hash...";


    fetch('generate-hash?orderId=' + orderId)
        .then(response => response.text())
        .then(hashResult => {
            hashInput.value = hashResult.trim();
        })
        .catch(error => alert("Lỗi kết nối hệ thống: " + error));
}

document.addEventListener("DOMContentLoaded", function() {
    var confirmBtn = document.getElementById("confirmSigBtn");
    var verifyForm = document.getElementById("verifyForm");

    if (confirmBtn) {
        confirmBtn.onclick = function() {
            var signature = document.getElementById("sigInput").value.trim();
            var hash = document.getElementById("hashDisplay").value.trim();

            if (signature === "") {
                alert("Vui lòng nhập chữ kí điện tử!");
                return;
            }

            document.getElementById("modalOrderHash").value = hash;
            document.getElementById("modalDigitalSig").value = signature;

            verifyForm.submit();
        };
    }
});