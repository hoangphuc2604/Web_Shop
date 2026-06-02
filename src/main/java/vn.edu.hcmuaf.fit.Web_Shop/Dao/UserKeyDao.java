package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;

import java.sql.*;

public class UserKeyDao {

    public static boolean revokeKey(int userId) {
        String query = "UPDATE User_keys SET status = 'REVOKED', revoked_at = NOW() WHERE user_id = ? AND status = 'ACTIVE'";
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
    public static UserKey getKeyById(int id) {
        String sql = "SELECT * FROM User_keys WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserKey key = new UserKey();
                key.setId(rs.getInt("id"));
                key.setUserId(rs.getInt("user_id"));
                key.setPublicKey(rs.getString("public_key"));
                key.setAlgorithm(rs.getString("algorithm"));
                key.setStatus(rs.getString("status"));
                key.setCreatedAt(rs.getTimestamp("created_at"));
                key.setRevokedAt(rs.getTimestamp("revoked_at"));
                return key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
