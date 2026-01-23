package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {

    UserService userService = new UserService();

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

        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirm = request.getParameter("confirmPassword");

        boolean success = userService.changePassword(
                user.getId(), oldPass, newPass, confirm
        );

        if (success) {
            request.setAttribute("success", "Đổi mật khẩu thành công");
        } else {
            request.setAttribute("error", "Mật khẩu hiện tại không đúng hoặc xác nhận sai");
        }

        request.getRequestDispatcher("Thongtintaikhoan.jsp")
                .forward(request, response);
    }
}
