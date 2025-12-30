function addToCart() {
    alert("Thêm vào giỏ hàng thành công");
}

// ================= GIÁ =================
const priceElement = document.querySelector(".product-price");
const totalPriceElement = document.querySelector(".product-total span");

const basePrice = Number(priceElement.dataset.price);

// format tiền VND
function formatVND(price) {
    return price.toLocaleString("vi-VN") + " VNĐ";
}

// hiển thị giá ban đầu
priceElement.textContent = formatVND(basePrice);

// ================= SỐ LƯỢNG =================
const minusBtn = document.querySelector(".minus");
const plusBtn = document.querySelector(".plus");
const qtyInput = document.querySelector(".quantity-control input");

function updateTotalPrice() {
    const quantity = Number(qtyInput.value);
    const total = basePrice * quantity;
    totalPriceElement.textContent = formatVND(total);
}

// mặc định load lần đầu
updateTotalPrice();

plusBtn.onclick = () => {
    qtyInput.value = Number(qtyInput.value) + 1;
    updateTotalPrice();
};

minusBtn.onclick = () => {
    if (qtyInput.value > 1) {
        qtyInput.value = Number(qtyInput.value) - 1;
        updateTotalPrice();
    }
};

// ================= SIZE =================
const sizeButtons = document.querySelectorAll(".size-options button");

sizeButtons.forEach(btn => {
    btn.onclick = () => {
        if (btn.classList.contains("disabled")) return;

        sizeButtons.forEach(b => b.classList.remove("active"));
        btn.classList.add("active");

        // sau này: thay đổi basePrice theo size
        updateTotalPrice();
    };
});

// ================= WISHLIST =================
const heartButtons = document.querySelectorAll(".fa-heart");

heartButtons.forEach(icon => {
    icon.addEventListener("click", function () {
        this.classList.toggle("active");

        if (this.classList.contains("active")) {
            alert("Đã thêm sản phẩm vào yêu thích thành công!");
            this.classList.remove("fa-regular");
            this.classList.add("fa-solid");
            this.style.color = "red";
        } else {
            alert("Đã xoá sản phẩm khỏi danh sách yêu thích");
            this.classList.remove("fa-solid");
            this.classList.add("fa-regular");
            this.style.color = "";
        }
    });
});