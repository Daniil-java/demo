package ru.spring.demo.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;
import ru.spring.demo.Repository.Database;

import java.util.List;

@RestController
public class ExpenseController {

    @GetMapping("/expense-accounting/expense")
    public List<Expense> getExpenses(@RequestParam(value = "sortBy", required = false) String sortBy,
                                     @RequestParam(value = "order", required = false) String order) {
        Database.connection();
        //Проверка корректности переданных параметров
        if (sortBy.equals(null) || order.equals(null)) {
            return Database.getExpenses();
        } else if (sortBy.equalsIgnoreCase("date") || sortBy.equalsIgnoreCase("money")) {
            if (order.equalsIgnoreCase("ASC") || order.equalsIgnoreCase("DESC")) {
                return Database.getExpenses(sortBy, order);
            }
        }
        return Database.getExpenses();     //Случай, отсутсвия параметров или неверных параметров
    }

    @GetMapping("/expense-accounting/expense/filter")
    public List<Expense> getFilterExpenses(@RequestBody Filter filter) {
        Database.connection();
        return Database.getExpenses(filter);
    }


    @PutMapping("/expense-accounting/expense")
    public List<Expense> setExpense(@RequestBody Expense expense) {
        Database.connection();
        Database.setNewExpense(expense);
        return Database.getExpenses();
    }

    @DeleteMapping("/expense-accounting/expense/{id}")
    public Expense deleteExpense(@PathVariable int id) {
        Expense expense = Database.getExpense(id);
        Database.deleteExpense(id);
        return expense;
    }

    @PostMapping("/expense-accounting/expense/{id}")
    public Expense editExpense(@PathVariable int id, @RequestBody Expense expense) {
        Database.editExpense(id, expense);
        return Database.getExpense(id);
    }
}
