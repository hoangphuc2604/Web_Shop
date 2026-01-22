package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Service.ProductService;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;

import java.io.IOException;

@WebServlet(name = "AddCart", value = "/add-cart")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        ProductService productService = new ProductService();
        Product product = productService.getProduct(productId);
        if(product != null){
            cart.addItem(product, quantity);
            session.setAttribute("cart", cart);
            response.sendRedirect("index.jsp");
            return;
        }
        request.setAttribute("msg", "Product not found");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}