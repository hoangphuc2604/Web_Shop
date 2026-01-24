package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserDao;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserInfoDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserInfo;

import java.security.MessageDigest;

public class UserService {

    private UserDao userDao = new UserDao();
    private UserInfoDao userInfoDao = new UserInfoDao();

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

    //  REGISTER
    public void register(String email, String username, String password) {
        String hashedPassword = hashPassword(password);
        User user = new User(email, username, hashedPassword);
        userDao.insert(user);
    }

    // LOGIN
    public User login(String login, String password) {
        User user = userDao.findByUsernameOrEmail(login);
        if (user == null) return null;

        if (hashPassword(password).equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    //  CHANGE PASSWORD
    public boolean changePassword(int userId, String oldPassword, String newPassword, String confirmPassword) {

        if (newPassword == null || !newPassword.equals(confirmPassword)) {
            return false;
        }

        User user = userDao.findById(userId);
        if (user == null) return false;

        if (!hashPassword(oldPassword).equals(user.getPassword())) {
            return false;
        }

        return userDao.updatePassword(userId, hashPassword(newPassword));
    }

    // PROFILE
    public UserInfo getUserInfo(int userId) {
        return userInfoDao.findByUserId(userId);
    }

    public void saveUserInfo(UserInfo info) {
        UserInfo exist = userInfoDao.findByUserId(info.getUserId());

        if (exist == null) {
            userInfoDao.insert(info);
        } else {
            userInfoDao.update(info);
        }
    }
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
