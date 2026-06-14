package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-key")
public class UserKeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            return;
        }

        String action = request.getParameter("action");
        if ("revoke".equals(action)) {
            int keyId = Integer.parseInt(request.getParameter("keyId"));
            UserKeyDao keyDao = new UserKeyDao();
            boolean success = UserKeyDao.revokeKey(keyId,user.getId());
            response.getWriter().write(success ? "OK" : "FAIL");
        }
        else if ("save".equals(action)){
            String pubKey = request.getParameter("publicKey");
            String algo = request.getParameter("algorithm");
            boolean isSaved = UserKeyDao.insertPubKey(user.getId(), pubKey, algo);
            if (isSaved){
                response.getWriter().write("Đã lưu khoá public cho thuật toán " + algo + " thành công!");
            } else {
                response.getWriter().write("Lưu khoá public thất bại!");
            }
        }
    }
}
