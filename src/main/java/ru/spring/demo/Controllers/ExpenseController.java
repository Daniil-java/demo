package ru.spring.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;
import ru.spring.demo.Repository.ExpenseRepository;
import ru.spring.demo.Services.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

//    @GetMapping("/expense-accounting/expense")
//    public List<Expense> getExpenses(@RequestParam(value = "sortBy", required = false) String sortBy,
//                                     @RequestParam(value = "order", required = false) String order) {
//        Database.connection();
//        //Проверка корректности переданных параметров
//        if (sortBy.equals(null) || order.equals(null)) {
//            return Database.getExpenses();
//        } else if (sortBy.equalsIgnoreCase("date") || sortBy.equalsIgnoreCase("money")) {
//            if (order.equalsIgnoreCase("ASC") || order.equalsIgnoreCase("DESC")) {
//                return Database.getExpenses(sortBy, order);
//            }
//        }
//        return Database.getExpenses();     //Случай, отсутсвия параметров или неверных параметров
//    }

    @GetMapping("/filter")
    public List<Expense> getFilterExpenses(@RequestBody Filter filter) {
        return expenseService.getFilterExpenses(filter);
    }

    @GetMapping
    public List<Expense> getFilterExpenses(@RequestParam(value = "category", required = false) String category,
                                           @RequestParam(value = "sumFrom", required = false) String sumFrom,
                                           @RequestParam(value = "sumTo", required = false) String sumTo,
                                           @RequestParam(value = "dateFrom", required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String dateFrom,
                                           @RequestParam(value = "dateTo", required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String dateTo,
                                           @RequestParam(value = "order", required = false) String order) {

        return expenseService.getFilterExpenses(category, sumFrom, sumTo, dateFrom, dateTo, order);
    }

    @GetMapping("/{id}")
    public Expense getExpense(@PathVariable int id) {
        return expenseService.getExpense(id);
    }

    @PutMapping
    public Expense setExpense(@RequestBody Expense expense) {
        return expenseService.setExpense(expense);
    }

//    @PutMapping
//    public List<Expense> setExpense(@RequestBody Expense expense) {
//        return expenseService.setExpense(expense);
//    }

    @DeleteMapping("/{id}")
    public Expense deleteExpense(@PathVariable int id) {
        return expenseService.deleteExpense(id);
    }

    @PostMapping("/{id}")
    public Expense editExpense(@PathVariable int id, @RequestBody Expense expense) {
        return expenseService.editExpense(id, expense);
    }
}
