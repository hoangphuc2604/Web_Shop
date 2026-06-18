package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.OrderItem;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import vn.edu.hcmuaf.fit.Web_Shop.cart.CartItem;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.SHA256;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@WebServlet("/generate-hash")
public class GenerateHashController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.getWriter().write("Lỗi: Không tìm thấy mã đơn hàng để tạo Hash");
            return;
        }
        try {
            int orderId = Integer.parseInt(orderIdStr);
            OrderDao dao = new OrderDao();
            Order order = dao.getOrderById(orderId);

            if (order == null) {
                response.getWriter().write("Lỗi: Đơn hàng không tồn tại");
                return;

        }
            StringBuilder dataToHash = new StringBuilder();
            dataToHash.append(order.getUserId());
            dataToHash.append((long) order.getTotalAmount());
            for (OrderItem item : order.getItems()) {
                dataToHash.append(item.getProduct().getId())
                        .append(item.getQuantity())
                        .append((long) item.getUnitPrice());
            }
            System.out.println("Data to hash (Tu Database): " + dataToHash.toString());
            SHA256 hasher = new SHA256();
            String currentHash = hasher.checkSum(dataToHash.toString());
            String originalHash = order.getOrderHash();
            if (originalHash != null && !originalHash.trim().isEmpty() && !originalHash.equals(currentHash)) {
                System.out.println("=> CẢNH BÁO BẢO MẬT: Đơn hàng #" + orderId + " đã bị thay đổi dữ liệu trái phép trước khi ký!");
                response.getWriter().write("Lỗi: Đơn hàng này đã bị thay đổi dữ liệu trái phép trước khi ký! Không thể xác thực.");
                return;
            }
            System.out.println("Mã Hash hợp lệ : " + currentHash);
            response.getWriter().write(currentHash);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi hệ thống khi tạo mã băm!");
        }
    }
}