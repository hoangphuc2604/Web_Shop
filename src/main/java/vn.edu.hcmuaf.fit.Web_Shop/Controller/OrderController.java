package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.OrderItem;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
import vn.edu.hcmuaf.fit.Web_Shop.Service.OrderVerifierService;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.SHA256;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/order-history", "/order-detail"})
public class OrderController extends HttpServlet {

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

        if (path.equals("/order-history")) {
            String action = request.getParameter("action");

            if ("get-new-hash".equals(action)) {
                int orderId = Integer.parseInt(request.getParameter("id"));
                Order o = dao.getOrderById(orderId);
                String newHash = generateOrderHash(o);
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().write(newHash);
                return;
            }

            List<Order> listOrders = dao.getOrdersByUser(user.getId());

            for (Order o : listOrders) {
                OrderVerifierService.verifyOrderIntegrity(o);
            }

            UserKey activeKey = UserKeyDao.getActiveKeyByUserId(user.getId());
            request.setAttribute("hasActiveKey", activeKey != null);

            request.setAttribute("listOrders", listOrders);
            request.getRequestDispatcher("ProductStatus.jsp").forward(request, response);
        }
        else if (path.equals("/order-detail")) {
            try {
                int orderId = Integer.parseInt(request.getParameter("id"));
                Order order = dao.getOrderById(orderId);

                OrderVerifierService.verifyOrderIntegrity(order);

                request.setAttribute("order", order);
                request.getRequestDispatcher("StatusDetails.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("order-history");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }
        if ("update-sig".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String newHash = request.getParameter("orderHash");
            String newSig = request.getParameter("digitalSig");

            UserKey activeKey = UserKeyDao.getActiveKeyByUserId(user.getId());

            if (activeKey != null) {
                OrderDao dao = new OrderDao();
                dao.updateOrderSignature(orderId, newHash, newSig, activeKey.getId());
            }

            response.sendRedirect("order-history");
        }
    }

    private String generateOrderHash(Order order) {
        StringBuilder dataBuilder = new StringBuilder();

        dataBuilder.append(order.getUserId());
        dataBuilder.append((long) order.getTotalAmount());

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                dataBuilder.append(item.getProduct().getId())
                        .append(item.getQuantity())
                        .append((long) item.getUnitPrice());
            }
        }

        SHA256 sha256 = new SHA256();
        return sha256.checkSum(dataBuilder.toString());
    }
}