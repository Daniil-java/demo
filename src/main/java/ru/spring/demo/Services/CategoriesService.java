package ru.spring.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Category setCategory(Category category) {
        return categoriesRepository.setNewCategory(category);
    }

    public Category deleteCategory(int id) {
        Category category = categoriesRepository.getCategory(id);
        categoriesRepository.deleteCategory(id);
        return category;
    }

    public Category editCategory(int id, Category category) {
        categoriesRepository.editCategory(id, category);
        return categoriesRepository.getCategory(id);
    }

    public Category getCategory(int id) {
       return categoriesRepository.getCategory(id);
    }
}
