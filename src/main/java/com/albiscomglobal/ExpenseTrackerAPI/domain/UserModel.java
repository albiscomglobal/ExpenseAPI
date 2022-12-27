package com.albiscomglobal.ExpenseTrackerAPI.domain;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message="Please enter name")
    private String name;

    @NotBlank(message="Please enter email")
    @Email(message="Please enter valid email")
    private String email;

    @NotBlank(message="Please enter password")
    @Size(min=5, message="Password should be atleast 5 characters")
    private String password;

    private Long age = 0L;
}
