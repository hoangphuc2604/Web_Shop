package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.User;

public class UserService {

    UserDao userDao = new UserDao();

    public void register(String email, String username, String password) {
        User user = new User(email, username, password);
        userDao.insert(user);
    }
}

