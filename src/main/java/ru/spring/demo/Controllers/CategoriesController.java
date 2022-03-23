package ru.spring.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Services.CategoriesService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController  {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping
    public List<Category> getCategories() {
        return categoriesService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return categoriesService.getCategory(id);
    }

    @PutMapping
    public Category setCategory(@RequestBody Category category) {
        return categoriesService.setCategory(category);
    }

    @DeleteMapping("/{id}")
    public Category deleteCategory(@PathVariable int id) {
        return categoriesService.deleteCategory(id);
    }

    @PostMapping("/{id}")
    public Category editCategory(@PathVariable int id, @RequestBody Category category) {
        return categoriesService.editCategory(id, category);
    }
}
