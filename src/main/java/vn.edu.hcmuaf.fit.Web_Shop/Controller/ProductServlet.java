package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product(
                1,
                "Thức Ăn Hạt Cho Mèo Con Royal Canin Kitten 36",
                "Royal Canin",
                132000,
                "assets/img/product1.webp",
                "Royal Canin Kitten 36 hỗ trợ tiêu hóa và miễn dịch cho mèo con.",
                "400g"
        );

        request.setAttribute("product", product);

        request.getRequestDispatcher("/WEB-INF/views/product.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}