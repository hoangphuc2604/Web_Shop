package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.WishlistDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import java.util.List;

public class WishlistService {
    WishlistDao wishlistDao = new WishlistDao();
    //Xét sp nếu tồn tại thì có thể xoá(false), chưa tồn tại thì có thể thêm(true)
    public boolean toggleWishlist(int userId, int productId) {
        if (wishlistDao.checkExist(userId, productId)) {
            wishlistDao.removeFromWishlist(userId, productId);
            return false;
        } else {
            wishlistDao.addToWishlist(userId, productId);
            return true;
        }
    }
    //Lấy ds sp yêu thích từ người dùng
    public List<Product> getWishlist(int userId) {
        return wishlistDao.getWishlistByUserId(userId);
    }
    //Lấy ds id sản phẩm người dùng đã tim
    public List<Integer> getWishlistIds(int userId) {
        return wishlistDao.getWishlistProductIds(userId);
    }
}
