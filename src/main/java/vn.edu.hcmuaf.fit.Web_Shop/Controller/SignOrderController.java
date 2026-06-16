package vn.edu.hcmuaf.fit.Web_Shop.Controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.VerSigOrder;

import java.io.IOException;

@WebServlet(name = "SignOrderController", value = "/SignOrderController")

public class SignOrderController extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        try {
            String orderIdStr = request.getParameter("orderId");
            String orderHash = request.getParameter("orderHash");
            String digitalSig = request.getParameter("digitalSig");

            if (orderIdStr == null || orderIdStr.trim().isEmpty() ||
                    orderHash == null || orderHash.trim().isEmpty() ||
                    digitalSig == null || digitalSig.trim().isEmpty()) {

                session.setAttribute("error", "LỖI: Dữ liệu gửi lên không đầy đủ hoặc bị rỗng!");
                response.sendRedirect("order-history");
                return;
            }
            int orderId = Integer.parseInt(orderIdStr.trim());

            UserKey activeKey = UserKeyDao.getActiveKeyByUserId(user.getId());

            if (activeKey == null || "REVOKED".equals(activeKey.getStatus()) || !activeKey.isActive()) {
                System.out.println("=> THẤT BẠI: Không tìm thấy khóa hoạt động cho user " + user.getId());
                session.setAttribute("error", "LỖI: Bạn chưa có khóa hoạt động hoặc khóa đã bị thu hồi!");
                response.sendRedirect("order-history");
                return;
            }

            boolean isReallyValid = VerSigOrder.verifyBase64(orderHash, digitalSig, activeKey.getPublicKey(), activeKey.getAlgorithm());

            if (!isReallyValid) {
                System.out.println("=> THẤT BẠI: Xác thực chữ ký sai (Thuật toán hoặc chữ ký không khớp)");
                session.setAttribute("error", "LỖI: Chữ ký số không hợp lệ hoặc dữ liệu đã bị sửa đổi. Xác thực thất bại!");
                response.sendRedirect("order-history");
                return;
            }
            System.out.println("=> THÀNH CÔNG: Chữ ký hợp lệ, tiến hành lưu Database!");
            OrderDao dao = new OrderDao();
            dao.updateOrderSignature(orderId, orderHash, digitalSig, activeKey.getId());

            session.setAttribute("success", "Xác thực đơn hàng #" + orderId + " thành công!");
            response.sendRedirect("order-history");


        } catch (Exception e) {
            System.out.println("=> LỖI HỆ THỐNG TRONG QUÁ TRÌNH XÁC THỰC:");
            e.printStackTrace();
            session.setAttribute("error", "LỖI HỆ THỐNG: Quá trình xác thực bị lỗi!");
            response.sendRedirect("order-history");

        }
    }
}