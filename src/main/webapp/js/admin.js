//Chuyển tab
const links = document.querySelectorAll(".tab-link");
const sections = document.querySelectorAll(".section");

links.forEach(link => {
    link.addEventListener("click", (e) => {
        e.preventDefault();
        links.forEach(l => l.classList.remove("active"));
        link.classList.add("active");

        const target = link.getAttribute("href").replace("#", "");
        sections.forEach(sec => sec.classList.remove("active"));

        const targetSection = document.getElementById(target);
        if(targetSection) targetSection.classList.add("active");
    });
});

//Xóa sp
function deleteProduct(id) {
    if (confirm("Bạn có chắc chắn muốn xóa sản phẩm có ID: " + id + " không?")) {
        let form = document.createElement("form");
        form.method = "POST";
        form.action = "admin-product";

        let inputAction = document.createElement("input");
        inputAction.type = "hidden";
        inputAction.name = "action";
        inputAction.value = "delete";
        form.appendChild(inputAction);

        let inputId = document.createElement("input");
        inputId.type = "hidden";
        inputId.name = "id";
        inputId.value = id;
        form.appendChild(inputId);

        document.body.appendChild(form);
        form.submit();
    }
}
