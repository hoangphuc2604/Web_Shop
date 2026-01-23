package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;

import java.io.IOException;

@WebServlet(name = "UpdateItem", value = "/update-cart")
public class UpdateItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            // Cập nhật số lượng mới cho sản phẩm
            if(quantity > 0) {
                cart.updateItem(productId, quantity);
            } else {
                cart.removeItem(productId);
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("my-cart");
    }
}