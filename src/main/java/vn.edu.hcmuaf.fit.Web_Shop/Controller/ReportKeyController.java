package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;

import java.io.IOException;

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

        int keyId = Integer.parseInt(request.getParameter("keyId"));
        UserKeyDao keyDao = new UserKeyDao();

        if (keyDao.revokeKey(keyId, user.getId())) {
            response.sendRedirect("Thongtintaikhoan.jsp?success=KeyRevoked");
        } else {
            response.sendRedirect("Thongtintaikhoan.jsp?error=RevokeFailed");
        }
    }
}