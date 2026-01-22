package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CollectionServlet", value = "/collections")
public class CollectionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidParam = request.getParameter("cid");
        String sort = request.getParameter("sort");
        if (sort == null) sort = "alpha-asc"; // Mặc định là A-Z
        String pageParam = request.getParameter("page");
        int page = 1; // Mặc định trang 1
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        ProductService service = new ProductService();
        List<Product> listProduct = new ArrayList<>();
        String categoryName = "Danh mục chưa có sản phẩm";
        int totalPage = 1;
        if (cidParam != null) {
            try {
                int cid = Integer.parseInt(cidParam);
                int pageSize = 12; //1 trang 12 sp
                int count = service.countProductsByCategory(cid);
                // Tính tổng số trang
                totalPage = count / pageSize;
                if (count % pageSize != 0) {
                    totalPage++;
                }
                // Tính vị trí bắt đầu (offset)
                int index = (page - 1) * pageSize;
                // Lấy danh sách sản phẩm theo bộ lọc
                listProduct = service.getProductsByCategory(cid, sort, index);
                // Lấy mô tả & tên danh mục
                String catDesc = service.getCategoryDescription(cid);
                request.setAttribute("categoryDesc", catDesc);
                if (!listProduct.isEmpty()) {
                    categoryName = listProduct.get(0).getCategoryName();
                }
                request.setAttribute("cid", cid);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("sort", sort);
        request.setAttribute("tag", page);
        request.setAttribute("endPage", totalPage);
        request.getRequestDispatcher("collections.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
