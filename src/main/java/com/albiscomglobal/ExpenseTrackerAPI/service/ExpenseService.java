package com.albiscomglobal.ExpenseTrackerAPI.service;

import com.albiscomglobal.ExpenseTrackerAPI.domain.Expense;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    //List<Expense> getAllExpene();

    Page<Expense> getAllExpene(Pageable page);

    Expense getSingleExpense (Long id);

    void deletesingleExpense (Long id);

    Expense saveSingleExpense (Expense expense);

    Expense updateSytleExpense (Long id , Expense expense);

    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> readByName (String keyword, Pageable page);

   List<Expense> readByDate(Date startDate, Date endDate, Pageable page);

}
