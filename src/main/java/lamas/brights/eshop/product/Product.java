package lamas.brights.eshop.product;

import lamas.brights.eshop.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name should not be empty") // validation maybe not necessary - products will be fixed directly in database at start
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
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", count=" + count + ", description='" + description + '\'' + ", category=" + category + '}';
    }
}
