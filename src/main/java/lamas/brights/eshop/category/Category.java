package lamas.brights.eshop.category;

import lamas.brights.eshop.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotEmpty(message = "Department name could not be empty") // validation maybe not necessary - categories will be fixed directly in database at start
    @Column(name = "catname", unique = true, nullable = true)
    private String name;

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
