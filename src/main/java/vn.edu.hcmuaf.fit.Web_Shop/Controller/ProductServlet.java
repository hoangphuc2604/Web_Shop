package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Model.ProductVariant;
import vn.edu.hcmuaf.fit.Web_Shop.Service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if(idParam != null){
            try {
                int id = Integer.parseInt(idParam);
                ProductService service = new ProductService();
                Product proDetail = service.getDetailProduct(id);
                request.setAttribute("proDetail", proDetail);
                ProductDao dao = new ProductDao();
                List<ProductVariant> variants = dao.getVariantsByProductId(id);
                request.setAttribute("variants", variants);
                request.getRequestDispatcher("product.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}