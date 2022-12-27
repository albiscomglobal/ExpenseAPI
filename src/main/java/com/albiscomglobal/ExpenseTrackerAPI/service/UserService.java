package com.albiscomglobal.ExpenseTrackerAPI.service;

import com.albiscomglobal.ExpenseTrackerAPI.domain.User;
import com.albiscomglobal.ExpenseTrackerAPI.domain.UserModel;

public interface UserService {

    User createUser (UserModel user);

    User readUSer (Long id);


    User update (UserModel user, Long id);

    void delete(Long id);

    User getLoggedInUser();
}
