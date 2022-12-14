package lamas.brights.eshop.product;

import com.sun.istack.NotNull;
import lamas.brights.eshop.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Arrays;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name should not be empty") // validation maybe not necessary - products will be fixed directly in database at start
    @NotNull
    @Column(name = "name")
    private String name;

//    @NotNull(message = "Price should not be empty") // validation maybe not necessary - products will be fixed directly in database at start
    @NotNull
    @Column(name = "price")
    private double price;

//    @NotEmpty(message = "Discount could not be empty") // validation maybe not necessary - products will be fixed directly in database at start
//    @Column(name = "discount")
//    private int discount; // discount will be in integer numbers, for user shown in percentage | will be added in Drop2

    @NotNull
    @Column(name = "count")
    private int count; // count of product in stock

    @Column(name = "description")
    @Lob
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Column(name = "image_path")
    private String imagePath ;

    public Product() {
    }

    public Product(String name, double price, int count, String description, Category category, String imagePath) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.description = description;
        this.category = category;
        this.imagePath = imagePath;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
