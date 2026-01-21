package vn.edu.hcmuaf.fit.Web_Shop.Dao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    // Lấy sp giảm giá
    public List<Product> getDiscountProducts(int limit) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.sale_price, p.price, p.desc, p.quantity, p.weight, i.image_url, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "LEFT JOIN P_category c ON p.category_id = c.id " +
                "WHERE p.sale_price < p.price " +
                "ORDER BY (p.price - p.sale_price) DESC LIMIT ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product pro = new Product(
                        rs.getInt("id"),          // INT
                        rs.getString("name"),
                        rs.getDouble("price"),      // Giá gốc
                        rs.getDouble("sale_price"), // Giá bán
                        rs.getString("image_url"),
                        rs.getString("desc"),
                        rs.getString("category_name")
                );
                list.add(pro);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Lấy sp đề xuất (dựa trên ngày tạo)
    public List<Product> getRecommendedProducts(int limit) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.sale_price, p.price, p.desc, p.quantity, p.weight, i.image_url, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "LEFT JOIN P_category c ON p.category_id = c.id " +
                "ORDER BY p.created_time DESC LIMIT ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product pro = new Product(
                        rs.getInt("id"),          // INT
                        rs.getString("name"),
                        rs.getDouble("price"),      // Giá gốc
                        rs.getDouble("sale_price"), // Giá bán
                        rs.getString("image_url"),
                        rs.getString("desc"),
                        rs.getString("category_name")
                );
                list.add(pro);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
