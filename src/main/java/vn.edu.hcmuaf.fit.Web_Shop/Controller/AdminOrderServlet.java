package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.VerSigOrder;

@WebServlet("/admin-order")
public class AdminOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDao dao = new OrderDao();
        List<Order> listOrders = dao.getAllOrders();

        // Kiểm tra toàn vẹn dữ liệu từng đơn hàng
        for (Order o : listOrders) {
            try {
                // Kiểm tra 2 trường mới: digitalSig và orderHash
                if (o.getDigitalSig() != null && !o.getDigitalSig().isEmpty() && o.getOrderHash() != null) {

                    // 1. Kéo Public Key từ DB
                    UserKey key = UserKeyDao.getKeyById(o.getKeyId());

                    if (key != null && key.getPublicKey() != null) {
                        // 2. Xác thực bằng DSA (truyền orderHash từ DB thay cho rawData)
                        boolean isValid = VerSigOrder.verifyBase64(o.getOrderHash(), o.getDigitalSig(), key.getPublicKey());

                        // 3. Nếu chữ ký không hợp lệ, gán cờ fake
                        o.setFake(!isValid);
                    } else {
                        // Mất khóa hoặc không tìm thấy khóa -> Đáng ngờ
                        o.setFake(true);
                    }
                } else {
                    // Thiếu hash hoặc chữ ký điện tử
                    o.setFake(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                o.setFake(true); // Lỗi giải mã Base64/Key -> Đơn bị can thiệp định dạng
            }
        }

        request.setAttribute("listOrders", listOrders);
        request.setAttribute("activeTab", "don-hang");

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}