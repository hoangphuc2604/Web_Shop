document.addEventListener("DOMContentLoaded", function () {
    let currentPrice = 0;

    const priceElement = document.getElementById("display-price");
    const quantityInput = document.getElementById("quantityInput");
    const totalElement = document.getElementById("total-calc");
    const variantInput = document.getElementById("selectedVariantId");

    const plusBtn = document.querySelector(".plus");
    const minusBtn = document.querySelector(".minus");
    const addToCartBtn = document.getElementById("addToCartBtn");
    const variantButtons = document.querySelectorAll(".variant-btn");

    function parsePrice(str) {
        return parseInt(str.replace(/\./g, '').replace(/\D/g, '')) || 0;
    }

    function formatVND(amount) {
        return amount.toLocaleString("vi-VN") + " VNĐ";
    }

    function updateTotalPrice() {
        if (!quantityInput || !totalElement) return;
        const qty = parseInt(quantityInput.value) || 1;
        const total = currentPrice * qty;
        totalElement.innerText = formatVND(total);
    }

    if (priceElement) {
        currentPrice = parsePrice(priceElement.innerText);
    }

    if (variantButtons) {
        variantButtons.forEach(btn => {
            btn.addEventListener("click", function () {
                // 1. Đổi class active
                variantButtons.forEach(b => b.classList.remove("active"));
                this.classList.add("active");

                // Lấy dữ liệu từ nút bấm (data-price, data-id)
                const priceStr = this.getAttribute("data-price");
                const vId = this.getAttribute("data-id");

                //Cập nhật giao diện
                if (priceElement) priceElement.innerText = priceStr;
                if (variantInput) variantInput.value = vId;

                //Cập nhật biến giá hiện tại & tính lại tổng
                currentPrice = parsePrice(priceStr);
                if (quantityInput) quantityInput.value = 1; // Reset số lượng về 1
                updateTotalPrice();
            });
        });
    }

    if (plusBtn && quantityInput) {
        plusBtn.addEventListener("click", function () {
            let qty = parseInt(quantityInput.value) || 1;
            quantityInput.value = qty + 1;
            updateTotalPrice();
        });
    }

    if (minusBtn && quantityInput) {
        minusBtn.addEventListener("click", function () {
            let qty = parseInt(quantityInput.value) || 1;
            if (qty > 1) {
                quantityInput.value = qty - 1;
                updateTotalPrice();
            }
        });
    }

    if (addToCartBtn) {
        addToCartBtn.addEventListener("click", function () {
            // Kiểm tra biến ID sản phẩm
            if (typeof currentProductId === 'undefined') {
                alert("Lỗi: Không tìm thấy ID sản phẩm.");
                return;
            }

            const qty = quantityInput ? quantityInput.value : 1;
            const vId = variantInput ? variantInput.value : "";

            let url = `add-to-cart?id=${currentProductId}&quantity=${qty}`;
            if (vId) {
                url += `&variantId=${vId}`;
            }

            window.location.href = url;
        });
    }
});