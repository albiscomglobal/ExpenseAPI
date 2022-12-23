package com.albiscomglobal.ExpenseTrackerAPI.serviceImpl;

import com.albiscomglobal.ExpenseTrackerAPI.Repository.ExpenseRepository;
import com.albiscomglobal.ExpenseTrackerAPI.domain.Expense;
import com.albiscomglobal.ExpenseTrackerAPI.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpene() {
        return expenseRepository.findAll();
    }
}
