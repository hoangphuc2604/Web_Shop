package vn.edu.hcmuaf.fit.Web_Shop.Controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import vn.edu.hcmuaf.fit.Web_Shop.cart.CartItem;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.SHA256;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@WebServlet("/generate-hash")
public class GenerateHashController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        String fullname = request.getParameter("fullname");
//        String address = request.getParameter("address");
//        String  note = request.getParameter("note");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null || cart.getTotalQuantity() ==0){
            response.getWriter().write("Lỗi: Giỏ hàng trống");
            return;

        }
        User user = (User) session.getAttribute("user");
        int userId = (user != null) ? user.getId() : 0;
        StringBuilder dataToHash = new StringBuilder();
        dataToHash.append(userId);
        dataToHash.append((long) cart.total());
        for(CartItem item : cart.getItems()){
            dataToHash.append(item.getProduct().getId())
                    .append(item.getQuantity())
                    .append((long) item.getPrice());
        }

        System.out.println("Data to hash: " + dataToHash.toString());
        SHA256 hasher = new SHA256();
        String hashResult = hasher.checkSum(dataToHash.toString());


        response.getWriter().write(hashResult);





    }
}
