function paymentSuccess(){
    alert("Bạn đã hoàn tất thủ tục thanh toán");
}
document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("sigModal");
    var openBtn = document.getElementById("inputPrivateKeyBtn");
    var closeBtn = document.getElementById("closeModalBtn");
    var confirmBtn = document.getElementById("confirmSigBtn");
    var sigInput = document.getElementById("sigInput");
    openBtn.onclick = function() {
        modal.style.display = "block";
    }
    closeBtn.onclick = function() {
        modal.style.display = "none";
    }
    confirmBtn.onclick = function() {
        var signature = sigInput.value.trim();

        if (signature === "") {
            alert("Vui lòng nhập chữ kí điện tử!");
            return;
        }
        alert("Xác thực chữ kí thành công!");
        modal.style.display = "none";

        openBtn.innerHTML = "Đã xác thực chữ ký";
        openBtn.style.backgroundColor = "#28a745";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});