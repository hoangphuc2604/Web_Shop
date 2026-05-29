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

function taiPriKey() {
    var keyPri = document.getElementById("txtPrivateKey").value;
    if (keyPri == "" || keyPri == null){
        alert("Chưa có Private Key, không thể tải xuống!");
        return;
    }
    var blobPriKey = new Blob([keyPri], {type : "text/plain"});
    var linkDownKey = document.createElement("a");
    linkDownKey.href = window.URL.createObjectURL(blobPriKey);
    linkDownKey.download = "private_key.pri";
    linkDownKey.click();
}
//
function openModel() {
    document.getElementById('keyModel').style.display = 'block';
}
function closeModel() {
    document.getElementById('keyModel').style.display = 'none';
}
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