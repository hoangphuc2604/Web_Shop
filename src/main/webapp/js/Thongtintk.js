window.onload = function () {
    const links = document.querySelectorAll(".tab-link");
    const sections = document.querySelectorAll(".section");

    links.forEach(link => {
        link.onclick = function (e) {
            e.preventDefault();

            links.forEach(l => l.classList.remove("active"));
            this.classList.add("active");

            sections.forEach(sec => sec.classList.remove("active"));
            const target = this.getAttribute("href").substring(1);
            document.getElementById(target).classList.add("active");
        };
    });
};
function openModel(){
    document.getElementById("keyModel").style.display = "block";
}

function closeModel() {
    document.getElementById("keyModel").style.display = "none";
}

function genKey(){
    var ajax = new XMLHttpRequest();
    ajax.open("GET", "generate-key", true);
    ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
            var result = ajax.responseText;
            var arrayKey = result.split("-");
            if (arrayKey.length == 2){
                document.getElementById("txtPublicKey").value = arrayKey[0];
                document.getElementById("txtPrivateKey").value = arrayKey[1];
                alert("Đã tạo khoá thành công!");
            } else{
                alert("Lỗi tạo khoá!")
            }
        }
    };
    ajax.send();
}