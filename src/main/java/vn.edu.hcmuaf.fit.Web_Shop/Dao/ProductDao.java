package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.Model.ProductVariant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
    //Lấy sp theo id category
    public List<Product> getProductsByCategory(int cid){
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, p.sale_price, p.`desc`, " +
                "i.image_url, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "JOIN P_category c ON p.category_id = c.id " +
                "WHERE p.category_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product pro = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("sale_price"),
                        rs.getString("image_url"),
                        rs.getString("desc"),
                        rs.getString("category_name")
                );
                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //Lấy ra mô tả của collection
    public String getCategoryDescription(int cid) {
        String desc = "";
        String query = "SELECT description FROM P_category WHERE id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                desc = rs.getString("description");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }
    //Đếm tổng sp trong 1 category (mục đích để phân trang)
    public int countProductsByCategory(int cid) {
        String query = "SELECT COUNT(*) FROM Products WHERE category_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Lấy sp có sắp xếp và phân trang
    public List<Product> getProductsByCategory(int cid, String sortType, int index) {
        List<Product> list = new ArrayList<>();
        String orderBy = "ORDER BY p.name ASC"; //Mặc định là A-Z

        // Xử lý logic sắp xếp
        if (sortType != null) {
            switch (sortType) {
                case "alpha-desc": orderBy = "ORDER BY p.name DESC"; break;
                case "price-asc": orderBy = "ORDER BY p.sale_price ASC"; break;
                case "price-desc": orderBy = "ORDER BY p.sale_price DESC"; break;
                default: orderBy = "ORDER BY p.name ASC"; break;
            }
        }
        String query = "SELECT p.id, p.name, p.price, p.sale_price, p.`desc`, " +
                "i.image_url, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "JOIN P_category c ON p.category_id = c.id " +
                "WHERE p.category_id = ? " +
                orderBy + " " +
                "LIMIT 12 OFFSET ?"; //Lấy ra 12 sp ở mỗi trang

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cid);
            ps.setInt(2, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("sale_price"),
                        rs.getString("image_url"),
                        rs.getString("desc"),
                        rs.getString("category_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //Tìm kiếm sản phẩm theo tên
    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, p.sale_price, pi.image_url, p.`desc`, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN P_category c ON p.category_id = c.id " +
                "LEFT JOIN Product_Images pi ON p.id = pi.product_id AND pi.is_main = 1 " +
                "WHERE p.name LIKE ?";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + txtSearch + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getDouble("sale_price"),
                            rs.getString("image_url"),
                            rs.getString("desc"),
                            rs.getString("category_name"));
                    list.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //Lấy khối lượng sp
    public List<ProductVariant> getVariantsByProductId(int productId) {
        List<ProductVariant> list = new ArrayList<>();
        // Lấy ID, weight và price_adjustment
        String query = "SELECT id, product_id, weight, price_adjustment " +
                "FROM Product_variants WHERE product_id = ? ORDER BY weight ASC";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductVariant(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getDouble("weight"),
                        rs.getDouble("price_adjustment")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // Lấy thông tin 1 biến thể cụ thể theo ID biến thể
    public ProductVariant getVariantById(int variantId) {
        String query = "SELECT id, product_id, weight, price_adjustment FROM Product_variants WHERE id = ?";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, variantId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProductVariant(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getDouble("weight"),
                        rs.getDouble("price_adjustment")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
