package lamas.brights.eshop.orders;

import lamas.brights.eshop.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Product, Long> {
}
