package lamas.brights.eshop.product;

import lamas.brights.eshop.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;

        if(optional.isPresent()){
            product = optional.get();
        }else{
            throw new RuntimeException("Product not found for id: " + id);
        }

        return product;
    }

    @Override
    public Product saveProduct(Product product) {
        Product _product = productRepository.save(product);

        return _product;
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteProductById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public List<Product> getProductsByCategoryId(long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
