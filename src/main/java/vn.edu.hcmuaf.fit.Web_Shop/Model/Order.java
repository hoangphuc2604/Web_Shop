package vn.edu.hcmuaf.fit.Web_Shop.Model;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Order {
    private int id;
    private int userId;
    private String status;
    private double totalAmount;
    private List<OrderItem> items;

    public Order() {

    }

    public Order(int id, int userId, String status, double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public String getFormattedTotal() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(totalAmount);
    }

    public String getStatusVN() {
        if ("Pending".equalsIgnoreCase(status)) return "Đang xử lý";
        if ("Packing".equalsIgnoreCase(status)) return "Đang đóng gói";
        if ("Shipping".equalsIgnoreCase(status)) return "Đang vận chuyển";
        if ("Delivered".equalsIgnoreCase(status)) return "Đã giao";
        if ("Cancelled".equalsIgnoreCase(status)) return "Đã hủy";
        return status;
    }
}
