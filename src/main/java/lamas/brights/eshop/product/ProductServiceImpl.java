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
}
