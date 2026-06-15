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

            if (orderIdStr == null || orderHash == null || digitalSig == null) {
                request.setAttribute("error", "LỖI: Dữ liệu gửi lên không đầy đủ!");
                request.getRequestDispatcher("URL-CUA-TRANG-TRANG-THAI").forward(request, response);
                return;
            }

            int orderId = Integer.parseInt(orderIdStr);

            UserKey activeKey = UserKeyDao.getActiveKeyByUserId(user.getId());

            if (activeKey == null || "REVOKED".equals(activeKey.getStatus()) || !activeKey.isActive()) {
                request.setAttribute("error", "LỖI: Bạn chưa có khóa hoạt động hoặc khóa đã bị thu hồi!");
                request.getRequestDispatcher("order-history").forward(request, response);
                return;
            }

            // 4. Xác thực chữ ký bằng thuật toán của bồ
            boolean isReallyValid = VerSigOrder.verifyBase64(orderHash, digitalSig, activeKey.getPublicKey(), activeKey.getAlgorithm());

            if (!isReallyValid) {
                request.setAttribute("error", "LỖI: Chữ ký số không hợp lệ hoặc dữ liệu đã bị sửa đổi. Xác thực thất bại!");
                request.getRequestDispatcher("order-history").forward(request, response);
                return;
            }

            OrderDao dao = new OrderDao();
            dao.updateOrderSignature(orderId, orderHash, digitalSig, activeKey.getId());

            request.setAttribute("success", "Xác thực đơn hàng #" + orderId + " thành công!");
            request.getRequestDispatcher("order-history").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "LỖI HỆ THỐNG: Quá trình xác thực bị lỗi!");
            request.getRequestDispatcher("order-history").forward(request, response);
        }
    }
}