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
    public void createOrder(int userId, Cart cart, String orderHash, String digitalSig, int keyId) {
        String sqlOrder = "INSERT INTO Orders (user_id, order_status, subtotal, voucher_id, total_amount, order_date, order_hash, digital_sig, key_id) VALUES (?, 'Pending', ?, NULL, ?, NOW(), ?, ?, ?)";
        String sqlItem = "INSERT INTO Order_items (product_id, order_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection()) {
            conn.setAutoCommit(false);
            try {
                PreparedStatement ps = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);

                if (userId <= 0) {
                    ps.setNull(1, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(1, userId);
                }
                ps.setDouble(2, cart.total());
                ps.setDouble(3, cart.total());
                ps.setString(4, orderHash);
                ps.setString(5, digitalSig);

                if (keyId == 0) {
                    ps.setNull(6, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(6, keyId);
                }
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
                        rs.getDouble("total_amount"),
                        rs.getString("order_hash"),
                        rs.getString("digital_sig"),
                        rs.getInt("key_id")
                );
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setItems(getOrderItems(o.getId()));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

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
                        rs.getDouble("total_amount"),
                        rs.getString("order_hash"),
                        rs.getString("digital_sig"),
                        rs.getInt("key_id")
                );
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setItems(getOrderItems(orderId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

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
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM Orders ORDER BY id DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("order_status"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_hash"),
                        rs.getString("digital_sig"),
                        rs.getInt("key_id")
                );
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setItems(getOrderItems(o.getId()));

                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void updateOrderSignature(int orderId, String orderHash, String digitalSig, int keyId) {
        String sql = "UPDATE Orders SET order_hash = ?, digital_sig = ?, key_id = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, orderHash);
            ps.setString(2, digitalSig);
            ps.setInt(3, keyId);
            ps.setInt(4, orderId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
