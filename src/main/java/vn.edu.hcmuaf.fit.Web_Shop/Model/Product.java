package vn.edu.hcmuaf.fit.Web_Shop.Model;

public class Product {
    private int id;
    private String name;
    private String brand;
    private double price;
    private String image;
    private String description;
    private String weight;

    // constructor
    public Product(int id, String name, String brand, double price,
                   String image, String description, String weight) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.image = image;
        this.description = description;
        this.weight = weight;
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public String getWeight() { return weight; }
}
