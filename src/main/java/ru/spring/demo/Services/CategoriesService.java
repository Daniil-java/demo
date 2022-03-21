package ru.spring.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Repository.CategoriesRepository;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Category> getCategories() {
        return categoriesRepository.getCategories();
    }

    public Category setExpense(Category category) {
        categoriesRepository.setNewCategory(category);
        return categoriesRepository.getCategory(categoriesRepository.getCategoryId(category));
    }

    public Category deleteExpense(int id) {
        Category category = categoriesRepository.getCategory(id);
        categoriesRepository.deleteCategory(id);
        return category;
    }

    public Category editExpense(int id, Category category) {
        categoriesRepository.editCategory(id, category);
        return categoriesRepository.getCategory(id);
    }
}
