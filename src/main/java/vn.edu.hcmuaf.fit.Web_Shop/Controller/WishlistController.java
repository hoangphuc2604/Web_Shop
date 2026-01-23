package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Service.WishlistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WishlistController", urlPatterns = {"/wishlist", "/api/wishlist-toggle"})
public class WishlistController extends HttpServlet{
    WishlistService wishlistService = new WishlistService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        //Kiểm tra khi bấm vào wishlist
        if (path.equals("/wishlist")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                //Chưa đăng nhập thì phải đăng nhập để sd chức năng
                response.sendRedirect("DangNhap.jsp");
                return;
            }
            //Đăng nhập rồi thì trả về ds
            List<Product> wishlists = wishlistService.getWishlist(user.getId());
            request.setAttribute("wishlists", wishlists);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Trả về json đơn giản hoặc text
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if (user == null) {
            response.getWriter().write("not_login");
            return;
        }
        try {
            int pid = Integer.parseInt(request.getParameter("pid"));
            //Phương thức thêm hoặc xóa sp
            boolean isAdded = wishlistService.toggleWishlist(user.getId(), pid);
            List<Integer> wishlistIds = wishlistService.getWishlistIds(user.getId());
            session.setAttribute("wishlistIds", wishlistIds);
            if (isAdded) {
                response.getWriter().write("added");
            } else {
                response.getWriter().write("removed");
            }
        } catch (Exception e) {
            response.getWriter().write("error");
        }
    }
}
