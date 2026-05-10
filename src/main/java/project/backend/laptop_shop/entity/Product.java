package project.backend.laptop_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "products") // This tells Hibernate to use the table name "products" in the database
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty(message = "Product name cannot be blank")
    private String name;
    private String image;

    @NotNull
    @DecimalMin(message = "Price must be greater than 0", value = "0", inclusive = false)
    private double price;

    @NotNull
    @NotEmpty(message = "Detail description cannot be blank")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;

    @NotNull
    @NotEmpty(message = "Short description cannot be blank")
    private String shortDesc;

    @NotNull
    @Min(message = "Quantity must be greater than 0", value = 1)
    private long quantity;

    private long sold;
    private String brand;
    private String purpose;

    // @OneToMany(mappedBy = "product") // This tells Hibernate to use the table
    // name "order_detail" in the database
    // private List<OrderDetail> orderDetails;

    public Product() {
        super();
    }

    public Product(long id, String name, String image, double price, String detailDesc, String shortDesc, long quantity,
            long sold, String brand, String purpose) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.detailDesc = detailDesc;
        this.shortDesc = shortDesc;
        this.quantity = quantity;
        this.sold = sold;
        this.brand = brand;
        this.purpose = purpose;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", detailDesc="
                + detailDesc + ", shortDesc=" + shortDesc + ", quantity=" + quantity + ", sold=" + sold + ", brand="
                + brand + ", purpose=" + purpose + "]";
    }
}
