package ru.spring.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;
import ru.spring.demo.Repository.ExpenseRepository;
import ru.spring.demo.Services.ExpenseService;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ExpenseServiceTest {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ExpenseRepository expenseRepository;

    Expense expense = new Expense( "test", 0.0d, 13, "date");

    @Test
    public void testGetExpense() {
        Assertions.assertTrue(expenseService.getExpense(1).getName().equalsIgnoreCase("Burger"));
    }

    @Test
    public void testSetExpense() {
        Expense testExpense = expenseRepository.setNewExpense(expense);
        Assertions.assertTrue(testExpense != null);
        System.out.println(testExpense.getId());
        expenseService.deleteExpense(testExpense.getId());
    }

    @Test
    public void testDeleteExpense() {
        Expense testExpense = expenseRepository.setNewExpense(expense);
        Assertions.assertTrue(expenseRepository.deleteExpense(testExpense.getId()));
    }

    @Test
    public void testEditExpense() {
        Expense testExpense = expenseRepository.setNewExpense(expense);
        Expense editExpense = expenseService.editExpense(testExpense.getId(), new Expense("testEDIT", 0.0d, 13, "date"));
        Assertions.assertTrue(expenseService.getExpense(testExpense.getId()).getName().equalsIgnoreCase("testEDIT"));
    }

    @Test
    public void testGetFilterExpenses() {
        Assertions.assertTrue(expenseRepository.getExpenses(new Filter("FOOD", 0.0d, 1000d, LocalDateTime.MIN.toString(), LocalDateTime.MAX.toString(), "ASC")) != null);
    }
}
