package ru.spring.demo.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Repository.AuthService;

import java.util.List;

@RestController
public class CategoriesController  {

    @GetMapping("/expense-accounting/categories")
    public List<Category> getCategories() {
        AuthService.connection();
        new Object();
        return AuthService.getCategories();
    }

    @PutMapping("/expense-accounting/categories")
    public Category setExpense(@RequestBody Category category) {
        AuthService.connection();
        AuthService.setNewCategory(category);
        return AuthService.getCategory(AuthService.getCategoryId(category));
    }

    @DeleteMapping("/expense-accounting/categories/{id}")
    public Category deleteExpense(@PathVariable int id) {
        AuthService.connection();
        Category category = AuthService.getCategory(id);
        AuthService.deleteCategory(id);
        return category;
    }
}
