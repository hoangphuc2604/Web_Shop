package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;

import java.io.IOException;
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
        request.getRequestDispatcher("Payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        //Kiểm tra giỏ hàng
        if (cart == null || cart.getTotalQuantity() == 0) {
            response.sendRedirect("Cart.jsp");
            return;
        }

        //Xác định User ID
        int userId = 0; // Mặc định khách vãng lai
        if (user != null) {
            userId = user.getId();
        } else {

        }
        OrderDao dao = new OrderDao();

        try {
            dao.createOrder(userId, cart);

            int newOrderId = 0;
            List<Order> orders = dao.getOrdersByUser(userId);

            if (orders != null && !orders.isEmpty()) {
                // Lấy đơn hàng đầu tiên (mới nhất)
                newOrderId = orders.get(0).getId();
            }

            session.removeAttribute("cart"); // Xóa giỏ hàng

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