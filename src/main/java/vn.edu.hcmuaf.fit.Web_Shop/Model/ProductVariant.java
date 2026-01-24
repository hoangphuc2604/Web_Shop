package vn.edu.hcmuaf.fit.Web_Shop.Model;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductVariant {
    private int id;
    private int productId;
    private double weight;
    private double price; // Giá bán của variant này

    public ProductVariant() {

    }

    public ProductVariant(int id, int productId, double weight, double price) {
        this.id = id;
        this.productId = productId;
        this.weight = weight;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(price);
    }

    public String getFormattedWeight() {
        if (weight < 1) return (int)(weight * 1000) + "g";
        if (weight == (long) weight) return (long) weight + "kg";
        return weight + "kg";
    }
}
