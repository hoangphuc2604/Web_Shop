document.addEventListener("DOMContentLoaded", function() {

    var modal = document.getElementById("sigModal");
    var openBtn = document.getElementById("inputPrivateKeyBtn");
    var closeBtn = document.getElementById("closeModalBtn");
    var confirmBtn = document.getElementById("confirmSigBtn");
    var sigInput = document.getElementById("sigInput");
    var hashInput = document.getElementById("hashDisplay");

    openBtn.onclick = function() { modal.style.display = "block"; }
    closeBtn.onclick = function() { modal.style.display = "none"; }
    window.onclick = function(event) { if (event.target == modal) modal.style.display = "none"; }

    confirmBtn.onclick = function() {
        var signature = sigInput.value.trim();
        if (signature === "") {
            alert("Vui lòng nhập chữ kí điện tử!");
            return;
        }

        document.getElementById("digitalSig").value = signature;
        document.getElementById("orderHash").value = hashInput.value;

        alert("Xác thực chữ kí thành công!");
        modal.style.display = "none";
        openBtn.innerHTML = "Đã xác thực chữ ký";
        openBtn.style.backgroundColor = "#28a745";
    }

    const checkoutForm = document.querySelector('form[action="payment"]');
    if (checkoutForm) {
        checkoutForm.addEventListener("submit", function(event) {
            const currentSig = document.getElementById("digitalSig").value;
            if (currentSig.trim() === "") {
                event.preventDefault();
                alert("LỖI: Bạn chưa xác thực chữ ký điện tử. Vui lòng bấm vào nút 'xác thực chữ ký điện tử' trước.");
            }
        });
    }
});