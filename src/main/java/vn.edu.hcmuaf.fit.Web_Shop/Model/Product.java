package vn.edu.hcmuaf.fit.Web_Shop.Model;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private int id;              // SỬA: Đổi String thành int
    private String name;
    private double price;        // Giá gốc (cột price trong DB)
    private double salePrice;    // Giá bán (cột sale_price trong DB)
    private String image;        // Lấy từ bảng Product_Images
    private String description;
    private String categoryName; // Lấy từ bảng P_category (dùng làm Thương hiệu/Loại)

    public Product(int id, String name, double price, double salePrice, String image, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getSalePrice() { return salePrice; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public String getCategoryName() { return categoryName; }

    // Định dạng tiền
    public String getFormattedPrice() {
        return formatMoney(this.salePrice);
    }

    public String getFormattedOriginalPrice() {
        return formatMoney(this.price);
    }

    private String formatMoney(double amount) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(amount);
    }

    // Tính giảm giá voucher
    public int getDiscountPercent() {
        if (price == 0) return 0;
        return (int) ((price - salePrice) / price * 100);
    }
}
