package com.albiscomglobal.ExpenseTrackerAPI.serviceImpl;

import com.albiscomglobal.ExpenseTrackerAPI.Repository.ExpenseRepository;
import com.albiscomglobal.ExpenseTrackerAPI.domain.Expense;
import com.albiscomglobal.ExpenseTrackerAPI.exceptions.ResourceNotFoundException;
import com.albiscomglobal.ExpenseTrackerAPI.service.ExpenseService;
import com.albiscomglobal.ExpenseTrackerAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

@Autowired
    private UserService userService;

    @Override
    public Page<Expense> getAllExpene(Pageable page) {
        return expenseRepository.findAll(page);


    }

    @Override
    public Expense getSingleExpense(Long id) {
        Optional<Expense> expense = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if(expense.isPresent()){
            return expense.get();

        }
       throw new ResourceNotFoundException("Expense is not found for the id " + id);
    }

    @Override
    public void deletesingleExpense(Long id) {
    Expense expense = getSingleExpense(id);
        expenseRepository.delete(expense);
    }



    @Override
    public Expense saveSingleExpense(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateSytleExpense(Long id, Expense expense) {
       Expense existingExpense = getSingleExpense(id);
       existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return expenseRepository.findByUserIdAndCategory (userService.getLoggedInUser().getId(),category, page).toList();
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return expenseRepository.findByUserIdAndNameContaining (userService.getLoggedInUser().getId(),keyword,page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {

       if(startDate == null){
           startDate = new Date(0);
       }
       if(endDate == null){
           endDate = new Date(System.currentTimeMillis());
       }

       Page<Expense> pages = expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate, endDate, page);
        return pages.toList();
    }

    }

