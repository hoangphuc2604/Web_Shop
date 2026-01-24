package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/order-history", "/order-detail"})
public class OrderController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        OrderDao dao = new OrderDao();
        //Xem lịch sử đơn hàng
        if (path.equals("/order-history")) {
            List<Order> listOrders = dao.getOrdersByUser(user.getId());
            request.setAttribute("listOrders", listOrders);
            request.getRequestDispatcher("ProductStatus.jsp").forward(request, response);
        }

        //Xem chi tiết đơn hàng
        else if (path.equals("/order-detail")) {
            try {
                int orderId = Integer.parseInt(request.getParameter("id"));
                Order order = dao.getOrderById(orderId);
                request.setAttribute("order", order);
                request.getRequestDispatcher("StatusDetails.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("order-history");
            }
        }
    }
}
