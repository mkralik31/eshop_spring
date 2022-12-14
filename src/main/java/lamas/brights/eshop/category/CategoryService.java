package lamas.brights.eshop.category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(long id);

    

    List<Category> getCategoryByName(String name);

    boolean existsCategoryById(long categoryId);
}
