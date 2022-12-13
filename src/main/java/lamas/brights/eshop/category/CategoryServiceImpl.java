package lamas.brights.eshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;

        if(optional.isPresent()){
            category = optional.get();
        }else{
            throw new RuntimeException("Category not found for id: " + id);
        }

        return category;
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return categoryRepository.findByNameContaining(name);
    }

}
