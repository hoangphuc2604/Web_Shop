package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;

@WebServlet("/ReportKey")
public class ReportKeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        if (UserKeyDao.revokeKey(user.getId())) {
            response.sendRedirect("Thongtintaikhoan.jsp?success=KeyRevoked");
        } else {
            response.sendRedirect("Thongtintaikhoan.jsp?error=RevokeFailed");
        }
    }
}