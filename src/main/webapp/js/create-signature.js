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
    const algoValue = document.getElementById("selectAlgo").value;
    const params = new URLSearchParams();
    params.append("hashInput", hashValue);
    params.append("privateKeyInput", privateKeyValue);
    params.append("algo", algoValue);
    fetch("create-signature", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: params
    })
        .then(response => response.text())
        .then(data => {
            if (data.startsWith("Lỗi")) {
                alert(data);
            } else {
                txtResult.value = data;
                alert("Tạo chữ ký số thành công!");
            }
        })
        .catch(error => {
            alert("Lỗi kết nối đến Server!");
            console.error(error);
        });
    function copyChuKy() {
        const txtResult = document.getElementById("signatureResult");
        if (txtResult.value === "") {
            alert("Chưa có chữ ký để copy!");
            return;
        }
        navigator.clipboard.writeText(txtResult.value);
        alert("Đã copy chữ ký thành công!");
    }
}