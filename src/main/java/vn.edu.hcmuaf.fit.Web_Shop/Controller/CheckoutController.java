package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        if (cart == null || cart.getTotalQuantity() == 0) {
            response.sendRedirect("index.jsp");
            return;
        }
        String orderHash = request.getParameter("orderHash");
        String digitalSig = request.getParameter("digitalSig");
        String keyIdStr = request.getParameter("keyId");
        if (orderHash == null || orderHash.isEmpty() ||
                digitalSig == null || digitalSig.isEmpty() ||
                keyIdStr == null || keyIdStr.isEmpty()) {
            response.sendRedirect("Payment.jsp?error=MissingSignature");
            return;
        }

        int keyId = Integer.parseInt(keyIdStr);
        UserKey key = UserKeyDao.getKeyById(keyId);

        if (key == null) {
            response.sendRedirect("Payment.jsp?error=InvalidKey");
            return;
        }
        if (!key.isActive() && key.getRevokedAt() != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (now.after(key.getRevokedAt())) {
                response.sendRedirect("Payment.jsp?error=KeyRevoked");
                return;
            }
        }

        OrderDao dao = new OrderDao();
        dao.createOrder(user.getId(), cart, orderHash, digitalSig, keyId);

        session.removeAttribute("cart");
        response.sendRedirect("order-history");
    }
}