package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Service.ProductHomeService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductHomeServlet", urlPatterns = {"/index", "/home"})
public class ProductHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductHomeService ps = new ProductHomeService();
        List<Product> listDiscount = ps.getHomeDiscountProducts();
        List<Product> listRecommend = ps.getHomeRecommendedProducts();
        request.setAttribute("listDiscount", listDiscount);
        request.setAttribute("listRecommend", listRecommend);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
