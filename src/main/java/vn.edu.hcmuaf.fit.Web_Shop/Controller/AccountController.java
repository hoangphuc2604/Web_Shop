package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserInfo;
import vn.edu.hcmuaf.fit.Web_Shop.Service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    UserService userService = new UserService();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("DangNhap.jsp");
                return;
            }

            User user = (User) session.getAttribute("user");
            UserInfo info = userService.getUserInfo(user.getId());

            if (info == null) { info = new UserInfo(); }

            request.setAttribute("userInfo", info);
            request.getRequestDispatcher("Thongtintaikhoan.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        // UPDATE USER
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        user.setUsername(username);
        user.setEmail(email);
        userService.updateUser(user);
        session.setAttribute("user", user);

        //  UPDATE USER INFO
        UserInfo info = new UserInfo();
        info.setUserId(user.getId());
        info.setGender(request.getParameter("gender"));
        info.setPhone(request.getParameter("phone"));
        info.setAddress(request.getParameter("address"));

        String birthday = request.getParameter("birthday");
        if (birthday != null && !birthday.isEmpty()) {
            info.setBirthday(java.sql.Date.valueOf(birthday));
        }

        userService.saveUserInfo(info);

        request.setAttribute("userInfo", info);
        request.setAttribute("success", "Cập nhật hồ sơ thành công");

        request.getRequestDispatcher("Thongtintaikhoan.jsp")
                .forward(request, response);
    }
}
