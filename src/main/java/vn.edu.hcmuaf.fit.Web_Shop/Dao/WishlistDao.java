package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao {
    //Kiểm tra sản phẩm đã tồn tại trong ds của người dùng bất kì chưa
    public boolean checkExist(int userId, int productId) {
        String query = "SELECT id FROM Wishlists WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //Thêm sp vào ds
    public boolean addToWishlist(int userId, int productId) {
        if (checkExist(userId, productId))
            return false; // Sp đó tồn tại thì không thêm vào ds
        String query = "INSERT INTO Wishlists(user_id, product_id) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //Xóa sp trong ds
    public boolean removeFromWishlist(int userId, int productId) {
        String query = "DELETE FROM Wishlists WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //Lấy ra ds sp yêu thích của người dùng(hiện lên trang wishlist)
    public List<Product> getWishlistByUserId(int userId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, p.sale_price, p.`desc`, i.image_url, c.category_name " +
                "FROM Wishlists w " +
                "JOIN Products p ON w.product_id = p.id " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "JOIN P_category c ON p.category_id = c.id " +
                "WHERE w.user_id = ? " +
                "ORDER BY w.id DESC";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("sale_price"),
                        rs.getString("image_url"),
                        rs.getString("desc"),
                        rs.getString("category_name"));
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //Lấy ds id sản phẩm người dùng đã tim
    public List<Integer> getWishlistProductIds(int userId) {
        List<Integer> list = new ArrayList<>();
        String query = "SELECT product_id FROM Wishlists WHERE user_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("product_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
