package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;

import java.security.MessageDigest;

public class UserService {

    UserDao userDao = new UserDao();

    //  HASH PASSWORD
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // REGISTER
    public void register(String email, String username, String password) {
        String hashedPassword = hashPassword(password);
        User user = new User(email, username, hashedPassword);
        userDao.insert(user);
    }

    // LOGIN
    public User login(String login, String password) {
        User user = userDao.findByUsernameOrEmail(login);
        if (user == null) return null;

        String hashedInputPassword = hashPassword(password);

        if (hashedInputPassword.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}
