function kichHoatChonFile() {
    document.getElementById("filePrivateKey").click();
}
function docFilePrivateKey() {
    const fileInput = document.getElementById("filePrivateKey");
    const txtPrivateKey = document.getElementById("privateKeyInput");
    const file = fileInput.files[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = function (e) {
        txtPrivateKey.value = e.target.result;
    };
    reader.readAsText(file);
}
function taoChuKy() {
    const hashValue = document.getElementById("hashInput").value.trim();
    const privateKeyValue = document.getElementById("privateKeyInput").value.trim();
    const txtResult = document.getElementById("signatureResult");

    if (hashValue === "") {
        alert("Vui lòng nhập mã Hash của đơn hàng!");
        return;
    }

    if (privateKeyValue === "") {
        alert("Vui lòng nhập khóa riêng tư!");
        return;
    }
    try {
        txtResult.value = "MOCK_SIGNATURE_BASE64_CHAY_THU_NGHIEM_CHO_KHANG";
        alert("Tạo chữ ký số thành công! Hãy copy chuỗi kết quả bên dưới.");
    } catch (error) {
        alert("Có lỗi xảy ra trong quá trình ký: " + error.message);
    }
}