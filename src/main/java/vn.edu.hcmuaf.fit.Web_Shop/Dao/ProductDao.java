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
                "i.image_url, c.category_name, p.category_id " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "LEFT JOIN P_category c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (Connection con = new DBConnect().getConnection();
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
                product.setCategoryId(rs.getInt("category_id"));
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
    //Đếm tổng sp trong 1 category
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
    //Lấy tất cả sp admin
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, p.sale_price, p.`desc`, " +
                "i.image_url, c.category_name " +
                "FROM Products p " +
                "LEFT JOIN Product_Images i ON p.id = i.product_id AND i.is_main = 1 " +
                "LEFT JOIN P_category c ON p.category_id = c.id " +
                "ORDER BY p.id DESC";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
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

    //Thêm sp mới
    public void insertProduct(String name, double price, double salePrice, String desc, int categoryId, String image) {
        String query = "INSERT INTO Products (name, price, sale_price, `desc`, category_id, created_time) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setDouble(3, salePrice);
            ps.setString(4, desc);
            ps.setInt(5, categoryId);
            ps.executeUpdate();

            //Lấy ID vừa tạo để thêm ảnh
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                String imgQuery = "INSERT INTO Product_Images (product_id, image_url, is_main, sort_order) VALUES (?, ?, 1, 1)";
                PreparedStatement psImg = conn.prepareStatement(imgQuery);
                psImg.setInt(1, newId);
                psImg.setString(2, image);
                psImg.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Sửa sp
    public void updateProduct(int id, String name, double price, double salePrice, String desc, int categoryId, String image) {
        String query = "UPDATE Products SET name=?, price=?, sale_price=?, `desc`=?, category_id=?, updated_time=NOW() WHERE id=?";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setDouble(3, salePrice);
            ps.setString(4, desc);
            ps.setInt(5, categoryId);
            ps.setInt(6, id);
            ps.executeUpdate();

            //Cập nhật ảnh chính
            if (image != null && !image.isEmpty()) {
                String imgQuery = "UPDATE Product_Images SET image_url=? WHERE product_id=? AND is_main=1";
                PreparedStatement psImg = conn.prepareStatement(imgQuery);
                psImg.setString(1, image);
                psImg.setInt(2, id);
                psImg.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Xóa sp
    public void deleteProduct(int id) {
        String query = "DELETE FROM Products WHERE id=?";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Check sp đã tồn tại chưa
    public boolean checkProductExist(String name) {
        String query = "SELECT id FROM Products WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
