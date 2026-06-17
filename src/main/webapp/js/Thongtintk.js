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

function luuPubKey() {
    var pubKey = document.getElementById("txtPublicKey").value;
    var algo = document.getElementById("selectAlgo").value;

    if (pubKey == "" || pubKey == null){
        alert("Không có khoá public để lưu. Hãy dùng tool của trang để lấy khoá!");
        return;
    }

    var ajax = new XMLHttpRequest();
    ajax.open("POST", "user-key", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4 && ajax.status == 200){
            alert(ajax.responseText);
            closeModel();
        }
    };
    ajax.send("action=save&publicKey=" + encodeURIComponent(pubKey) + "&algorithm=" + algo);
}

//
async function reportKeyLoss() {
    if (!confirm('Chắc chắn báo mất khóa? Đơn hàng cũ vẫn được giữ nguyên để đối chiếu.')) return;

    try {
        let res = await fetch('user-key?action=revoke', { method: 'POST' });
        let text = await res.text();

        if (text === 'OK') {
            alert('Đã thu hồi khóa.');
            location.reload();
        } else {
            alert('Lỗi: ' + text);
        }
    } catch (e) {
        alert('Lỗi kết nối server.');
    }
}

let btnReport = document.getElementById('btnReportKey');
if (btnReport) btnReport.onclick = reportKeyLoss;