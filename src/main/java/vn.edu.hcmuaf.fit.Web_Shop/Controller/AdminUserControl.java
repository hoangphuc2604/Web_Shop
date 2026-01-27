package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserControl", value = "/admin-user")
public class AdminUserControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        List<User> list = dao.getAllUsers();
        request.setAttribute("listU", list);
        request.setAttribute("activeTab", "nguoi-dung");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
