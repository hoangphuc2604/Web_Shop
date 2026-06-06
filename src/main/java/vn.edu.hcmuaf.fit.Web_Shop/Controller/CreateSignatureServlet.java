package vn.edu.hcmuaf.fit.Web_Shop.Controller; // Thay đổi package cho đúng với thư mục chứa Servlet của bạn

import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.GenSigOrder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create-signature-api")
public class CreateSignatureServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cấu hình trả về JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 1. Lấy mã Hash và Private Key từ JavaScript gửi lên
        String hashInput = request.getParameter("hashInput");
        String privateKeyInput = request.getParameter("privateKeyInput");

        PrintWriter out = response.getWriter();

        try {
            // 2. Ném vào file lõi để xử lý thuật toán DSA
            String signatureResult = GenSigOrder.signToBase64(hashInput, privateKeyInput);

            // 3. Ký thành công -> Trả JSON chứa chữ ký về cho màn hình
            out.print("{\"status\":\"success\", \"signature\":\"" + signatureResult + "\"}");

        } catch (Exception e) {
            e.printStackTrace();
            // Lỗi (do sai Private Key, mã hóa lỗi...) -> Trả JSON báo lỗi
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }

        out.flush();
    }
}