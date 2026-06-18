package vn.edu.hcmuaf.fit.Web_Shop.Model;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Order {
    private int id;
    private int userId;
    private String status;
    private double totalAmount;
    private List<OrderItem> items;
    private String orderHash;
    private String digitalSig;
    private int keyId;
    private boolean isFake;
    private boolean timeViolated; // Bổ sung cờ báo lỗi thời gian
    private Timestamp orderDate;

    public Order() {}

    public Order(int id, int userId, String status, double totalAmount, String orderHash, String digitalSig, int keyId) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderHash = orderHash;
        this.digitalSig = digitalSig;
        this.keyId = keyId;
    }

    public boolean isTimeViolated() { return timeViolated; }
    public void setTimeViolated(boolean timeViolated) { this.timeViolated = timeViolated; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
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

    public String getOrderHash() { return orderHash; }
    public void setOrderHash(String orderHash) { this.orderHash = orderHash; }
    public String getDigitalSig() { return digitalSig; }
    public void setDigitalSig(String digitalSig) { this.digitalSig = digitalSig; }
    public int getKeyId() { return keyId; }
    public void setKeyId(int keyId) { this.keyId = keyId; }
    public boolean isFake() { return isFake; }
    public void setFake(boolean fake) { isFake = fake; }

    public String getStatusVN() {
        if ("Pending".equalsIgnoreCase(status)) return "Đang xử lý";
        if ("Packing".equalsIgnoreCase(status)) return "Đang đóng gói";
        if ("Shipping".equalsIgnoreCase(status)) return "Đang vận chuyển";
        if ("Delivered".equalsIgnoreCase(status)) return "Đã giao";
        if ("Cancelled".equalsIgnoreCase(status)) return "Đã hủy";
        return status;
    }
}