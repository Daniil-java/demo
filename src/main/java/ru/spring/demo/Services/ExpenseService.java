package ru.spring.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;
import ru.spring.demo.Repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public List<Expense> getFilterExpenses(Filter filter) {
        return expenseRepository.getExpenses(filter);
    }

    public List<Expense> getFilterExpenses(String category, String sumFrom, String sumTo, String dateFrom, String dateTo, String order) {
        Double from = 0d;
        Double to = Double.MAX_VALUE;

        if (sumFrom != null) {
            from = Double.valueOf(sumFrom);
        }

        if (sumTo != null) {
            to = Double.valueOf(sumTo);
        }

        return expenseRepository.getExpenses(new Filter(category, from, to, dateFrom, dateTo, order));
    }

    public Expense getExpense(int id) {
        return expenseRepository.getExpense(id);
    }

    public List<Expense> setExpense(Expense expense) {
        expenseRepository.setNewExpense(expense);
        return expenseRepository.getExpenses();
    }

    public Expense deleteExpense(int id) {
        Expense expense = expenseRepository.getExpense(id);
        expenseRepository.deleteExpense(id);
        return expense;
    }

    public Expense editExpense(int id, Expense expense) {
        expenseRepository.editExpense(id, expense);
        return expenseRepository.getExpense(id);
    }
}
