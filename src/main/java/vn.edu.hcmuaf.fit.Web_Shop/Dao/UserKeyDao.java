package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import java.sql.*;

public class UserKeyDao {
    public static boolean revokeKey(int userId) {
        String query = "UPDATE user_keys SET is_active = false, revoked_at = NOW() WHERE user_id = ? AND is_active = true";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertPubKey(int userId, String pubKey, String algo){
        String query = "INSERT INTO User_keys (user_id, public_key, algorithm, status) VALUES (?, ?, ?, 'ACTIVE')";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, pubKey);
            ps.setString(3, algo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
