package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.OrderDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;

@WebServlet("/admin-order")
public class AdminOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDao dao = new OrderDao();
        List<Order> listOrders = dao.getAllOrders();

        request.setAttribute("listOrders", listOrders);
        request.setAttribute("activeTab", "don-hang");

        request.getRequestDispatcher("admin.jsp")
                .forward(request, response);
    }
}
