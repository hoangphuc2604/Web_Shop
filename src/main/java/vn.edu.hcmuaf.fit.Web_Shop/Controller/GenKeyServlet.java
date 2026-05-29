package vn.edu.hcmuaf.fit.Web_Shop.Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

@WebServlet("/generate-key")
public class GenKeyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try{
            String algo = request.getParameter("algorithm");
            KeyPairGenerator keyGen;
            if ("RSA".equals(algo)){
                keyGen = KeyPairGenerator.getInstance("RSA");
                keyGen.initialize(2048);
            }
            else {
                keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                keyGen.initialize(1024, random);
            }
            KeyPair keyPair = keyGen.generateKeyPair();
            String pubKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String priKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
            response.getWriter().write(pubKey + "-" + priKey);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi: " + e.getMessage());
        }
    }
}
