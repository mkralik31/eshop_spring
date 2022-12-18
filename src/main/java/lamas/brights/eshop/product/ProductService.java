package lamas.brights.eshop.product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductByName(String name);
    Product getProductById(long id);

    Product saveProduct(Product product);

    void deleteProductById(long id);

    void deleteAllProducts();

    List<Product> getProductsByCategoryId(long categoryId);
}
