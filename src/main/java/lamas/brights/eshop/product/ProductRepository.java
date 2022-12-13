package lamas.brights.eshop.product;

import lamas.brights.eshop.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String name); // returns all categories which name contains input name

    void deleteProductById(long id);


}
