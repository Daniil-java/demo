package ru.spring.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Repository.CategoriesRepository;
import ru.spring.demo.Services.CategoriesService;

import java.util.Locale;

@SpringBootTest
public class CategoriesServiceTest {
    @Autowired
    CategoriesService categoriesService;

    @Autowired  //Чтобы проверить произошло ли удаление, ввиду алгоритмических особенностей процесса
    CategoriesRepository categoriesRepository;

    @Test
    public void testGetCategories() {
        Assertions.assertTrue(categoriesService.getCategories().size() > 0);
    }

    @Test
    public void testGetCategory() {
        Assertions.assertTrue(categoriesService.getCategory(13).getCategory().equalsIgnoreCase("CLOTHES"));
    }

    @Test
    public void testSetCategory() {
        Category category = categoriesService.setCategory(new Category("testCategory"));
        Assertions.assertTrue(categoriesService.getCategory(category.getId()).getCategory().equalsIgnoreCase("testCategory"));
        categoriesService.deleteCategory(category.getId());
    }

    @Test
    public void testDeleteCategory() {
        categoriesService.setCategory(new Category("testDeleteCategory"));
        Assertions.assertTrue(categoriesRepository.deleteCategory(categoriesRepository.getCategoryId(new Category("testDeleteCategory"))));
    }

    @Test
    public void testEditCategory() {
        Category category = categoriesService.setCategory(new Category("testEditCategory"));    //Создание категории
        categoriesService.editCategory(category.getId(), new Category("testEdit")); //Редактирование категории
        //Проверка того, что название категории изменилось на то, которое было передано в методе
        Assertions.assertTrue(category.getCategory().equalsIgnoreCase(categoriesService.getCategory(category.getId()).getCategory()));
        categoriesService.deleteCategory(categoriesRepository.getCategoryId(new Category("testEdit"))); //Удаление тестовой категории
    }
}
