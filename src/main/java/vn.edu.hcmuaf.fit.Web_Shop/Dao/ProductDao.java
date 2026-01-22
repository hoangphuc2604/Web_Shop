package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDao {
    //Lấy sp theo id
    public Product getProductByID(int id) {
        Product product = null;
        String query = "SELECT p.id, p.name, p.price, p.sale_price, p.`desc`, " +
                "i.image_url, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "JOIN P_category c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("sale_price"),
                        rs.getString("image_url"),
                        rs.getString("desc"),
                        rs.getString("category_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
