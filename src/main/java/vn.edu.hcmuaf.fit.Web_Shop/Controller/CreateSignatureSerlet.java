package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.GenSigOrder;

import java.io.IOException;

@WebServlet("/create-signature")
public class CreateSignatureSerlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String hashInput = request.getParameter("hashInput");
        String privateKeyInput = request.getParameter("privateKeyInput");
        String algo = request.getParameter("algo");

        // BỘ LỌC BẢO MẬT: Dọn sạch rác tiêu đề PEM và ký tự xuống dòng ẩn của Private Key
        if (privateKeyInput != null) {
            privateKeyInput = privateKeyInput.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                    .replace("-----END RSA PRIVATE KEY-----", "")
                    .replaceAll("\\s+", ""); // Xóa toàn bộ dấu cách, tab, xuống dòng (\n, \r)
        }

        try {
            String result = GenSigOrder.signData(hashInput, privateKeyInput, algo);
            response.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi: Cấu trúc Khóa riêng tư hoặc định dạng dữ liệu không hợp lệ!");
        }
    }
}