package vn.edu.hcmuaf.fit.Web_Shop.cart;

import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int quantity;
    private double price;
    private Product product;

    public CartItem(int quantity, double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }
    public CartItem(){
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public void upQuantity(int quantity){
        this.quantity += quantity;
    }
}
