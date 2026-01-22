package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.User;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserDao {

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/web_shop?useUnicode=true&characterEncoding=UTF-8",
                "root",
                "123456"
        );
    }

    // ================= REGISTER =================
    public void insert(User user) {
        String sql = """
            INSERT INTO users(email, username, password, lock, role)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getLocked());
            ps.setString(5, user.getRole());
            

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= LOGIN =================
    public User findByUsernameOrEmail(String input) {
        String sql = "SELECT * FROM users WHERE username=? OR email=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, input);
            ps.setString(2, input);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setRole(rs.getString("role"));
                    u.setLocked(rs.getInt("locked"));
                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
