package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Service.ProductService;
import vn.edu.hcmuaf.fit.Web_Shop.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    UserService userService = new UserService();
    ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.login(login, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect("admin/index.jsp");
            } else {
                List<Product> listDiscount = productService.getTopDiscountProducts(8);
                List<Product> listRecommend = productService.getTopRecommendProducts(8);
                session.setAttribute("listDiscount", listDiscount);
                session.setAttribute("listRecommend", listRecommend);
                response.sendRedirect("index.jsp");
            }
        } else {
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("DangNhap.jsp")
                    .forward(request, response);
        }
    }
}
