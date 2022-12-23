package com.albiscomglobal.ExpenseTrackerAPI.Repository;

import com.albiscomglobal.ExpenseTrackerAPI.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
