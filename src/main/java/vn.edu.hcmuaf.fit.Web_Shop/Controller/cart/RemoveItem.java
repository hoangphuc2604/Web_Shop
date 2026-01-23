package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;

import java.io.IOException;

@WebServlet(name = "removeItem", value = "/remove-item")
public class RemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Cart cart   =  (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
            return;
        }
        cart.removeItem(productId);
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect("my-cart");
    }
}