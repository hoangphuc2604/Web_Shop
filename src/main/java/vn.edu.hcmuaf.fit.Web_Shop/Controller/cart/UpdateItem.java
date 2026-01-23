package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;

import java.io.IOException;

@WebServlet(name = "UpdateItem", value = "/update-item")
public class UpdateItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pIdParam = request.getParameter("productId");
        String qtyParam = request.getParameter("quantity");

        if (pIdParam != null && qtyParam != null) {
            try {
                int productId = Integer.parseInt(pIdParam);
                int quantity = Integer.parseInt(qtyParam);

                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("cart");

                if (cart != null) {
                    // Gọi hàm updateItem của Cart
                    cart.updateItem(productId, quantity);
                    session.setAttribute("cart", cart);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Quay về trang giỏ hàng
        response.sendRedirect("Cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}