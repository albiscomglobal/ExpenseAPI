package com.albiscomglobal.ExpenseTrackerAPI.service;

import com.albiscomglobal.ExpenseTrackerAPI.domain.User;
import com.albiscomglobal.ExpenseTrackerAPI.domain.UserModel;

public interface UserService {

    User createUser (UserModel user);

    User readUSer ( );


    User update (UserModel user);

    void delete();

    User getLoggedInUser();
}
