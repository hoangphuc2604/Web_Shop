package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductControl", value = "/admin-product")
public class AdminProductControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductDao dao = new ProductDao();

        if ("add".equals(action)) {
            request.getRequestDispatcher("admin-product-form.jsp").forward(request, response);

        } else if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = dao.getProductByID(id);
                request.setAttribute("product", product);
                request.getRequestDispatcher("admin-product-form.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<h3>LỖI XẢY RA: " + e.getMessage() + "</h3>");
                e.printStackTrace(response.getWriter());
            }

        } else {
            List<Product> list = dao.getAllProduct();
            request.setAttribute("listP", list);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        ProductDao dao = new ProductDao();

        if ("delete".equals(action)) {
            //Xóa
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteProduct(id);
        } else if ("save".equals(action)) {
            //Thêm hoặc sửa
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");

            double price = 0, salePrice = 0;
            try {
                price = Double.parseDouble(request.getParameter("price"));
                salePrice = Double.parseDouble(request.getParameter("sale_price"));
            } catch (NumberFormatException e) { }

            String image = request.getParameter("image");
            String desc = request.getParameter("desc");
            int categoryId = Integer.parseInt(request.getParameter("category_id"));

            if (idStr == null || idStr.isEmpty()) {
                dao.insertProduct(name, price, salePrice, desc, categoryId, image);
            } else {
                dao.updateProduct(Integer.parseInt(idStr), name, price, salePrice, desc, categoryId, image);
            }
        }
        response.sendRedirect("admin-product");
    }
}