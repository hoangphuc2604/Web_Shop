package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserInfoDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserInfo;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.VerSigOrder;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getTotalQuantity() == 0) {
            response.sendRedirect("Cart.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("preEmail", user.getEmail());
            request.setAttribute("preName", user.getUsername());
            UserInfoDao userInfoDao = new UserInfoDao();
            UserInfo info = userInfoDao.findByUserId(user.getId());
            if (info != null) {
                request.setAttribute("prePhone", info.getPhone());
                request.setAttribute("preAddress", info.getAddress());
            }
            // tìm active key của user để lấy id của khóa mà làm
            UserKey activeKey = UserKeyDao.getActiveKeyByUserId(user.getId());
            if (activeKey != null) {
                request.setAttribute("activeKeyId", activeKey.getId());
            }
        }

        request.getRequestDispatcher("Payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        // Kiểm tra giỏ hàng
        if (cart == null || cart.getTotalQuantity() == 0) {
            response.sendRedirect("Cart.jsp");
            return;
        }

        // Xác định User ID
        int userId = 0; // Mặc định khách vãng lai
        if (user != null) {
            userId = user.getId();
        }

        String orderHash = request.getParameter("orderHash");
        String digitalSig = request.getParameter("digitalSig");
        String keyIdStr = request.getParameter("keyId");
        if (orderHash == null || orderHash.isEmpty() ||
                digitalSig == null || digitalSig.isEmpty() ||
                keyIdStr == null || keyIdStr.isEmpty()) {

            request.setAttribute("error", "LỖI: Không tìm thấy dữ liệu chữ ký số hợp lệ để tiến hành lưu đơn!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }

        int keyId = 0;
        try {
            keyId = Integer.parseInt(keyIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Mã khóa không hợp lệ!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }

        UserKey activeKey = UserKeyDao.getKeyById(keyId);
        if (activeKey == null) {
            request.setAttribute("error", "LỖI: Không tìm thấy khóa trên hệ thống!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
        if("REVOKED".equals(activeKey.getStatus())|| !activeKey.isActive()){
            request.setAttribute("error", "LỖI: Khóa đã bị thu hồi hoặc không còn hoạt động!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
        boolean isReallyValid = false;
        try {
            isReallyValid = VerSigOrder.verifyBase64(orderHash, digitalSig, activeKey.getPublicKey(), activeKey.getAlgorithm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isReallyValid) {
            request.setAttribute("error", "PHÁT HIỆN GIAN LẬN: Chữ ký không hợp lệ, đơn hàng đã bị hủy!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
        OrderDao dao = new OrderDao();
        try {

          dao.createOrder(userId, cart, orderHash, digitalSig, keyId);
            int newOrderId = 0;
            List<Order> orders = dao.getOrdersByUser(userId);

            if (orders != null && !orders.isEmpty()) {

                newOrderId = orders.get(0).getId();
            }

            session.removeAttribute("cart");

            if (newOrderId > 0) {
                request.setAttribute("orderId", newOrderId);
            }

            request.getRequestDispatcher("NotifyPayment.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra trong quá trình xử lý đơn hàng!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        }
    }

}
