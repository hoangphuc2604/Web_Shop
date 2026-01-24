package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import vn.edu.hcmuaf.fit.Web_Shop.Model.UserInfo;
import java.sql.*;

public class UserInfoDao {

    public UserInfo findByUserId(int userId) {
        String sql = "SELECT * FROM user_info WHERE user_id=?";
        try (Connection conn = UserDao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserInfo info = new UserInfo();
                info.setUserId(userId);
                info.setGender(rs.getString("gender"));
                info.setBirthday(rs.getDate("birthday"));
                info.setPhone(rs.getString("phone"));
                info.setAddress(rs.getString("address"));
                return info;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(int userId) {
        String sql = "SELECT 1 FROM user_info WHERE user_id=?";
        try (Connection conn = UserDao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(UserInfo info) {
        String sql = """
            INSERT INTO user_info(user_id, gender, birthday, phone, address)
            VALUES (?, ?, ?, ?, ?)
        """;
        try (Connection conn = UserDao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, info.getUserId());
            ps.setString(2, info.getGender());
            ps.setDate(3, info.getBirthday());
            ps.setString(4, info.getPhone());
            ps.setString(5, info.getAddress());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(UserInfo info) {
        String sql = """
            UPDATE user_info
            SET gender=?, birthday=?, phone=?, address=?
            WHERE user_id=?
        """;
        try (Connection conn = UserDao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, info.getGender());
            ps.setDate(2, info.getBirthday());
            ps.setString(3, info.getPhone());
            ps.setString(4, info.getAddress());
            ps.setInt(5, info.getUserId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
