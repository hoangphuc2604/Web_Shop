package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserInfoDao;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.SHA256;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserInfo;

import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import vn.edu.hcmuaf.fit.Web_Shop.cart.CartItem;


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


            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        }
    }
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("user");

            if (cart == null || cart.getTotalQuantity() == 0) {
                response.sendRedirect("Cart.jsp");
                return;
            }

            int userId = 0; // Mặc định khách vãng lai
            if (user != null) {
                userId = user.getId();
            }


            OrderDao dao = new OrderDao();
        try {
            StringBuilder dataToHash = new StringBuilder();
            dataToHash.append(userId);
            dataToHash.append((long) cart.total());
            for (CartItem item : cart.getItems()) {
                dataToHash.append(item.getProduct().getId())
                        .append(item.getQuantity())
                        .append((long) item.getPrice());
            }
            SHA256 hasher = new SHA256();
            String originalOrderHash = hasher.checkSum(dataToHash.toString());
            dao.createOrder(userId, cart, originalOrderHash, null, 0);

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
            request.setAttribute("error", "Có lỗi xảy ra trong quá trình lưu đơn hàng!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        }
    }
}