package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.OrderItem;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import vn.edu.hcmuaf.fit.Web_Shop.cart.CartItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    //Tạo đơn hàng
    public void createOrder(int userId, Cart cart) {
        String sqlOrder = "INSERT INTO Orders (user_id, order_status, subtotal, voucher_id, total_amount, order_date) VALUES (?, 'Pending', ?, NULL, ?, NOW())";
        String sqlItem = "INSERT INTO Order_items (product_id, order_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection()) {
            conn.setAutoCommit(false);
            try {
                PreparedStatement ps = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setDouble(2, cart.total());
                ps.setDouble(3, cart.total());
                ps.executeUpdate();
                int orderId = 0;
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
                PreparedStatement psItem = conn.prepareStatement(sqlItem);
                for (CartItem item : cart.getItems()) {
                    psItem.setInt(1, item.getProduct().getId());
                    psItem.setInt(2, orderId);
                    psItem.setInt(3, item.getQuantity());
                    psItem.setDouble(4, item.getPrice());
                    psItem.addBatch();
                }
                psItem.executeBatch();

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Lấy ds đơn hàng
    public List<Order> getOrdersByUser(int userId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE user_id = ? ORDER BY id DESC";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("order_status"),
                        rs.getDouble("total_amount"));
                o.setItems(getOrderItems(o.getId()));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //Lấy ra chi tiết đơn hàng
    public Order getOrderById(int orderId) {
        Order o = null;
        String sql = "SELECT * FROM Orders WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                o = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("order_status"),
                        rs.getDouble("total_amount"));
                o.setItems(getOrderItems(orderId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
    //Lấy items trong đơn
    public List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT oi.*, p.name, pi.image_url " +
                "FROM Order_items oi " +
                "JOIN Products p ON oi.product_id = p.id " +
                "LEFT JOIN Product_Images pi ON p.id = pi.product_id AND pi.is_main = 1 " +
                "WHERE oi.order_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));

                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image_url"));

                item.setProduct(p);
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
