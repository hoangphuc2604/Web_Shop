package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.GenSigOrder;

import java.io.IOException;

@WebServlet("/create-signature")
public class CreateSignatureSerlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hashInput = request.getParameter("hashInput");
        String privateKeyInput = request.getParameter("privateKeyInput");
        String algo = request.getParameter("algo");
        try {
            String result = GenSigOrder.signData(hashInput, privateKeyInput, algo);
            response.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi: Khóa hoặc dữ liệu không hợp lệ!");
        }


    }
}
