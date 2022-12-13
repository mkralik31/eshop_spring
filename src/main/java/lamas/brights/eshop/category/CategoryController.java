package lamas.brights.eshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping("/products/category")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String name) {
        try {
            List<Category> category = new ArrayList<>();

            if (name == null){
                category = categoryService.getAllCategories();
            } else {
                category = categoryService.getCategoryByName(name);
            }

            if (category.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(category, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/products/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        // method to return category by id from database
        Category category = categoryService.getCategoryById(id);

        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
