package com.albiscomglobal.ExpenseTrackerAPI.controller;

import com.albiscomglobal.ExpenseTrackerAPI.domain.Expense;
import com.albiscomglobal.ExpenseTrackerAPI.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ExpenseController {

    // http:localhost:8097/api/v1/expense

    @Autowired
    private ExpenseService expenseService;

    /*@GetMapping("/expense")
    public Page <Expense> getAllExpense(Pageable page){
        return expenseService.getAllExpene(page);
*/

        @GetMapping("/expense")
        public List<Expense> getAllExpenses(Pageable page) {
            return expenseService.getAllExpene(page).toList();
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<Expense> getSingleExpense (@PathVariable Long id){
        return new ResponseEntity<Expense>(expenseService.getSingleExpense(id), HttpStatus.OK);
    }
    /* @ResponseStatus(value = HttpStatus.CREATED)  Optional way for doing the status code */
    @PostMapping("/expense")
    public ResponseEntity<Expense> saveSingleExpense ( @Valid @RequestBody Expense expense){
        return new ResponseEntity<Expense>(expenseService.saveSingleExpense(expense), HttpStatus.CREATED);
    }
public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateSytleExpense(id, expense);
}


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam Long id) {
        expenseService.deletesingleExpense(id);
    }

public int calculateFactorial(int number){
        return number * calculateFactorial(number -1);
}
@GetMapping("/expenses/category")
public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page){
return expenseService.readByCategory(category, page);
}

@GetMapping("/expenses/name")
public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page){
        return expenseService.readByName(keyword , page);
}


@GetMapping("/expenses/date")
public List<Expense> getAllExpensesByDate(
        @RequestParam(required = false) Date startDate,
        @RequestParam(required = false) Date endDate,
        Pageable page
){
        return expenseService.readByDate(startDate ,endDate, page);

}
}
