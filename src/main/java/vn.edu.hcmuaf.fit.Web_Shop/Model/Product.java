package vn.edu.hcmuaf.fit.Web_Shop.Model;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private int id;
    private String name;
    private double price;
    private double salePrice;
    private String image;
    private String description;
    private String categoryName;
    private int categoryId;

    public Product() {

    }

    public Product(int id, String name, double price, double salePrice, String image, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getSalePrice() { return salePrice; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public String getCategoryName() { return categoryName; }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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
