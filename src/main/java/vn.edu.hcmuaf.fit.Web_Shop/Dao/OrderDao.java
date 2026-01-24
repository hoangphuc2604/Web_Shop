package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.cart.Cart;
import vn.edu.hcmuaf.fit.Web_Shop.cart.CartItem;

import java.sql.*;
public class OrderDao {

    public int createOrder(Order order, Cart cart) {

        String sqlOrder = "INSERT INTO Orders (user_id, full_name, email, phone, address, note, order_status, total_amount, order_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        String sqlItem = "INSERT INTO Order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        String sqlPayment = "INSERT INTO Payments (order_id, pay_method, pay_status, payment_time) VALUES (?, ?, ?, NOW())";

        Connection conn = DBConnect.getConnection();
        int orderId = 0;

        try {
            conn.setAutoCommit(false);
            PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            if (order.getUserId() == 0) psOrder.setNull(1, Types.INTEGER);
            else psOrder.setInt(1, order.getUserId());

            psOrder.setString(2, order.getFullName());
            psOrder.setString(3, order.getEmail());
            psOrder.setString(4, order.getPhone());
            psOrder.setString(5, order.getAddress());
            psOrder.setString(6, order.getNote());
            psOrder.setString(7, "Đang xử lý");
            psOrder.setDouble(8, order.getTotalAmount());
            psOrder.executeUpdate();
            ResultSet rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            PreparedStatement psItem = conn.prepareStatement(sqlItem);
            for (CartItem item : cart.getItems()) {
                psItem.setInt(1, orderId);
                psItem.setInt(2, item.getProduct().getId());
                psItem.setInt(3, item.getQuantity());
                psItem.setDouble(4, item.getPrice());
                psItem.addBatch();
            }
            psItem.executeBatch();

            PreparedStatement psPayment = conn.prepareStatement(sqlPayment);
            psPayment.setInt(1, orderId);
            psPayment.setString(2, "COD");
            psPayment.setString(3, "Chưa thanh toán");
            psPayment.executeUpdate();

            conn.commit();
            return orderId;

        } catch (Exception e) {
            e.printStackTrace();
            try {

                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {

            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException e) {}
        }
        return 0; // Thất bại
    }
}