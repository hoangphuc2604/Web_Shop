package vn.edu.hcmuaf.fit.Web_Shop.Dao;

public class TestDB {
    public static void main(String[] args) {
        if (DBConnect.getConnection() != null) {
            System.out.println("Kết nối DB thành công!");
        } else {
            System.out.println("Kết nối DB thất bại!");
        }
    }
}