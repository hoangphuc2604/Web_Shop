function openSigModal(orderId) {
    var modal = document.getElementById('sigModal');
    modal.style.display = 'block';
    document.getElementById('modalOrderId').value = orderId;
    var hashDisplay = document.getElementById('hashDisplay');
    hashDisplay.value = "Đang tạo mã Hash...";


    fetch('generate-hash?orderId=' + orderId)
        .then(response => response.text())
        .then(hashResult => {
            hashDisplay.value = hashResult.trim();
        })
        .catch(error => {
            hashDisplay.value = "Lỗi khi lấy mã Hash!";
            console.error(error);
        });
}
function submitSignature() {
    var signature = document.getElementById("sigInput").value.trim();
    var hash = document.getElementById("hashDisplay").value.trim();
    var verifyForm = document.getElementById("verifyForm");

    if (signature === "") {
        alert("Vui lòng nhập chữ kí điện tử!");
        return;
    }

    if (!verifyForm) {
        alert("Lỗi giao diện: Không tìm thấy form ẩn (verifyForm) trong file JSP!");
        return;
    }

    document.getElementById("modalOrderHash").value = hash;
    document.getElementById("modalDigitalSig").value = signature;

    verifyForm.submit();
}