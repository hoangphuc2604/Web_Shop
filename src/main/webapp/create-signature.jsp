<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo Chữ Ký Điện Tử</title>
    <link rel="stylesheet" href="css/create-signature.css">
</head>
<body>
<div id ="signature-container" class="create-sig-wrapper">
   <h3>Tạo chữ ký điện tử</h3>
    <p >Vui lòng nhập Hash của đơn hàng bạn vào đây</p>
    <div class="input-group hash-group">
        <textarea id="hashInput" class="hash-textarea" rows="4" placeholder="Dán mã Hash vào đây..."></textarea>
    </div>
    <p >Vui lòng nhập hoặc tải file private key vào đây</p>
    <div class="input-group PrivateKey-group">
        <textarea id="privateKeyInput" class="private-textarea" rows="4" placeholder="Dán Private Key vào đây..."></textarea>

            <button type="button" id="btnUploadPrivateKey" class="btn-upload">Tải Private Key</button>
           <input type="file" id="filePrivateKey" class="file-input" accept=".txt, .pem, .key">
    </div>

        <div class="result-group">
            <h4 class="title-result">Chữ ký tạo thành</h4>
            <textarea id="signatureResult" class="result-textarea" rows="4" readonly placeholder="Chữ ký sẽ hiển thị ở đây..."></textarea>
            <button type="button" id="btnSign" class="btn-sign">Bắt đầu ký dữ liệu</button>

        </div>





</div>

</body>
</html>
