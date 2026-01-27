package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.User;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/web_shop?useUnicode=true&characterEncoding=UTF-8",
                "root",
                "123456"
        );
    }

    //  REGISTER
    public void insert(User user) {
        String sql = """
            INSERT INTO users(email, username, password, `lock`, role)
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

    //  LOGIN
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
                    u.setLocked(rs.getInt("lock"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //  CHANGE PASSWORD
    public boolean updatePassword(int userId, String newHashedPassword) {
        String sql = "UPDATE users SET password=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newHashedPassword);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setLocked(rs.getInt("lock"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateUser(User user) {
        String sql = "UPDATE users SET username=?, email=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "SELECT id, email, username, password, `lock`, role FROM users";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setLocked(rs.getInt("lock"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
