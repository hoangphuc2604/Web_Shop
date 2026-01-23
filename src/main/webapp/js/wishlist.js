
function checkLoginForWishlist(event, isLoggedIn) {
    if (!isLoggedIn) {
        event.preventDefault();//Chặn
        alert("Bạn vui lòng đăng nhập để xem danh sách yêu thích!");
    } else {

    }
}

function toggleWishlist(element, productId, isLoggedIn) {
    if (!isLoggedIn) {
        alert("Bạn vui lòng đăng nhập để có thể dùng chức năng thêm sản phẩm yêu thích");
        return;
    }

    //Gửi Ajax xuống Server
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "api/wishlist-toggle", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            const response = this.responseText.trim();
            //Chưa đăng nhập
            if (response === "not_login") {
                alert("Vui lòng đăng nhập!");
                window.location.href = "DangNhap.jsp";
            }

            //Thêm sp thành công
            else if (response === "added") {
                alert("Đã thêm sản phẩm vào yêu thích thành công!");
                //Đổi icon khi thêm thành công(element)
                element.classList.remove("fa-regular");
                element.classList.add("fa-solid");
                element.style.color = "red";
                element.classList.add("liked");
            }

            //Xoá sp thành công
            else if (response === "removed") {
                alert("Đã xoá sản phẩm khỏi danh sách yêu thích");
                // Kiểm tra đang ở trang nào?
                // Nếu đang ở trang wishlist, sp nằm trong khối .wl-item
                const wishlistItemBox = element.closest('.wl-item');
                if (wishlistItemBox) {
                    //Đang ở wishlist: Xóa luôn sp đó
                    wishlistItemBox.remove();

                    //Nếu xóa hết sạch thì hiện thông báo trống
                    const remainingItems = document.querySelectorAll('.wl-item');
                    if (remainingItems.length === 0) {
                        const container = document.querySelector('.wl-wrapper');
                        if (container) {
                            container.innerHTML = '<p style="text-align: center; width: 100%; margin-top: 20px;">Bạn chưa có sản phẩm yêu thích nào.</p>';
                        }
                    }
                } else {
                    // => Đang ở trang khác: đổi màu icon về như cũ
                    element.classList.remove("fa-solid");
                    element.classList.add("fa-regular");
                    element.style.color = "";
                    element.classList.remove("liked");
                }
            }
            else {
                console.error("Lỗi server: ", response);
            }
        }
    };
    // Gửi ID sản phẩm đi
    xhr.send("pid=" + productId);
}