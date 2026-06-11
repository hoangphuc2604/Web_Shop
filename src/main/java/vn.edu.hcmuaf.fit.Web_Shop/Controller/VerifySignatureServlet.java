package vn.edu.hcmuaf.fit.Web_Shop.Controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.VerSigOrder;
import java.io.IOException;
@WebServlet(name = "VerifySignatureServlet", value = "/verify-signature")
public class VerifySignatureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderHash = request.getParameter("orderHash");
        String digitalSig = request.getParameter("digitalSig");
        String keyIdStr = request.getParameter("keyId");

        if (orderHash == null || digitalSig == null || keyIdStr == null || keyIdStr.isEmpty()) {
            response.getWriter().write("FAILED: Thong tin khong du de xac thuc!");
            return;
        }

        try {
            int keyId = Integer.parseInt(keyIdStr);
            UserKey key = UserKeyDao.getKeyById(keyId);

            if (key == null) {
                response.getWriter().write("FAILED: Không tìm thấy khóa công khai!");
                return;
            }

            boolean isValid = VerSigOrder.verifyBase64(orderHash, digitalSig, key.getPublicKey(), key.getAlgorithm());
            if (isValid) {
                response.getWriter().write("SUCCESS");
            } else {
                response.getWriter().write("Thất bại: Chữ ký koong hợp lệ!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Thất bại: có lỗi xảy ra!");
        }
    }
    }

