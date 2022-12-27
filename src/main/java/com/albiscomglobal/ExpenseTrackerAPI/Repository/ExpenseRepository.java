package com.albiscomglobal.ExpenseTrackerAPI.Repository;

import com.albiscomglobal.ExpenseTrackerAPI.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    //SELECT * FROM tbl_expenses WHERE category=?
 Page<Expense>findByCategory(String Category, Pageable page);

 //SELECT * FROM tbl_expenses WHERE name LIKE '%keyword%'
 Page<Expense> findByNameContaining (String keyword, Pageable page);


//SELECT * FROM tbl_Expenses WHERE date BETWEEN 'startDate' AND 'endDate'
Page<Expense> findByDateBetween (Date startDate, Date endDate, Pageable Page);


    Optional<Expense> findByUserIdAndId (Long userId, Long expenseId);
}
