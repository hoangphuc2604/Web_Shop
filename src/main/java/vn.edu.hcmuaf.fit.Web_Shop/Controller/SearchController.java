package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String txtSearch = request.getParameter("txt");
            ProductDao dao = new ProductDao();
            List<Product> list = dao.searchByName(txtSearch);
            request.setAttribute("listSearch", list);
            request.setAttribute("txtS", txtSearch);
            request.setAttribute("resultCount", list.size()); // Số kết quả tìm thấy

            // 4. Chuyển trang
            request.getRequestDispatcher("Search-result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
