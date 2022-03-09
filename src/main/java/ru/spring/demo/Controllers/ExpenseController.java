package ru.spring.demo.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;
import ru.spring.demo.Repository.AuthService;

import java.util.List;

@RestController
public class ExpenseController {

    @GetMapping("/expense-accounting/expense")
    public List<Expense> getExpenses(@RequestParam(value = "sortObj", required = false) String sortObj,
                                     @RequestParam(value = "type", required = false) String type) {
        AuthService.connection();
        //Проверка корректности переданных параметров
        if (sortObj.equals(null) || type.equals(null)) {
            return AuthService.getExpenses();
        } else if (sortObj.equalsIgnoreCase("date") || sortObj.equalsIgnoreCase("money")) {
            if (type.equalsIgnoreCase("ASC") || type.equalsIgnoreCase("DESC")) {
                return AuthService.getExpenses(sortObj, type);
            }
        }
        return AuthService.getExpenses();     //Случай, отсутсвия параметров или неверных параметров
    }

    @GetMapping("/expense-accounting/expense/filter")
    public List<Expense> getFilterExpenses(@RequestBody Filter filter) {
        AuthService.connection();
        return AuthService.getExpenses(filter);
    }


    @PutMapping("/expense-accounting/expense")
    public List<Expense> setExpense(@RequestBody Expense expense) {
        AuthService.connection();
        AuthService.setNewExpense(expense);
        return AuthService.getExpenses();
    }

    @DeleteMapping("/expense-accounting/expense/{id}")
    public Expense deleteExpense(@PathVariable int id) {
        Expense expense = AuthService.getExpense(id);
        AuthService.deleteExpense(id);
        return expense;
    }

    @PostMapping("/expense-accounting/expense/{id}")
    public Expense editExpense(@PathVariable int id, @RequestBody Expense expense) {
        AuthService.editExpense(id, expense);
        return AuthService.getExpense(id);
    }
}
