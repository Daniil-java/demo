package ru.spring.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Repository.Database;

import java.util.List;

@RestController
public class CategoriesController  {

    @Autowired
    Database db;

    @GetMapping("/expense-accounting/category")
    public List<Category> getCategories() {
        return db.getCategoriesNonStatic();
//        Database.connection();
//        new Object();
//        return Database.getCategories();
    }

    @PutMapping("/expense-accounting/category")
    public Category setExpense(@RequestBody Category category) {
        Database.connection();
        Database.setNewCategory(category);
        return Database.getCategory(Database.getCategoryId(category));
    }

    @DeleteMapping("/expense-accounting/category/{id}")
    public Category deleteExpense(@PathVariable int id) {
        Database.connection();
        Category category = Database.getCategory(id);
        Database.deleteCategory(id);
        return category;
    }
}
