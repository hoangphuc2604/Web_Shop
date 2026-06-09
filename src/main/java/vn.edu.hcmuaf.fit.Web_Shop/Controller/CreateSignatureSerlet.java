package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create-signature")
public class CreateSignatureSerlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hashInput = request.getParameter("hashInput");
        String pivateKeyInput = request.getParameter("pivateKeyInput");
        String algo = request.getParameter("algo");


    }
}
