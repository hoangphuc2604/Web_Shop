package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Model.ProductVariant;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddToCartController", value = "/add-to-cart")
public class AddToCartController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy tham số từ URL
        String idParam = request.getParameter("id");
        String qtyParam = request.getParameter("quantity");
        String variantParam = request.getParameter("variantId");

        int id = Integer.parseInt(idParam);
        int quantity = (qtyParam != null) ? Integer.parseInt(qtyParam) : 1;
        int variantId = (variantParam != null && !variantParam.isEmpty()) ? Integer.parseInt(variantParam) : 0;

        // 2. Lấy thông tin sản phẩm từ DB
        ProductDao dao = new ProductDao();
        Product product = dao.getProductByID(id);

        if (product != null) {
            if (variantId > 0) {
                ProductVariant variant = dao.getVariantById(variantId);
                if (variant != null) {
                    product.setPrice(variant.getPrice()); // Lấy giá của biến thể
                    product.setId(Integer.parseInt(id + "" + variantId));
                }
            }
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addItem(product, quantity);
        }
        response.sendRedirect("product?id=" + id);
    }
}
