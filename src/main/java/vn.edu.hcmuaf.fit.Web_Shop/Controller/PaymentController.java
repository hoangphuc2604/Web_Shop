package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;

import java.io.IOException;

@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //Kiểm tra giỏ hàng
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getTotalQuantity() == 0) {
            response.sendRedirect("Cart.jsp");
            return;
        }

        //Lấy dữ liệu từ Form
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        //Đổ dữ liệu vào Model Order
        Order order = new Order();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            order.setUserId(user.getId());
        } else {
            order.setUserId(0); // Khách vãng lai
        }

        order.setFullName(fullName);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setNote(note);
        order.setTotalAmount(cart.total());

        //Gọi DAO để lưu
        OrderDao dao = new OrderDao();
        int orderId = dao.createOrder(order, cart);

        if (orderId > 0) {
            //Thành công
            session.removeAttribute("cart"); // Xóa giỏ hàng
            request.setAttribute("orderId", orderId); // Gửi mã đơn hàng sang trang thông báo
            request.getRequestDispatcher("NotifyPayment.jsp").forward(request, response);
        } else {
            // THẤT BẠI
            request.setAttribute("error", "Hệ thống đang bận, vui lòng thử lại sau!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        }
    }
}