package ru.spring.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Repository.CategoriesRepository;
import ru.spring.demo.Services.CategoriesService;

import java.util.List;

@RestController
public class CategoriesController  {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/expense-accounting/category")
    public List<Category> getCategories() {
        return categoriesService.getCategories();
    }

    @PutMapping("/expense-accounting/category")
    public Category setExpense(@RequestBody Category category) {
        return categoriesService.setExpense(category);
    }

    @DeleteMapping("/expense-accounting/category/{id}")
    public Category deleteExpense(@PathVariable int id) {
        return categoriesService.deleteExpense(id);
    }

    @PostMapping("/expense-accounting/category/{id}")
    public Category editExpense(@PathVariable int id, @RequestBody Category category) {
        return categoriesService.editExpense(id, category);
    }
}
