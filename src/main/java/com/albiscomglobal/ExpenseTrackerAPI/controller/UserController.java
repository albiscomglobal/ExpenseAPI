package com.albiscomglobal.ExpenseTrackerAPI.controller;

 import com.albiscomglobal.ExpenseTrackerAPI.domain.User;
 import com.albiscomglobal.ExpenseTrackerAPI.domain.UserModel;
 import com.albiscomglobal.ExpenseTrackerAPI.exceptions.ResourceNotFoundException;
 import com.albiscomglobal.ExpenseTrackerAPI.service.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<User> readUser(){
return new ResponseEntity<User>(userService.readUSer(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> update(@RequestBody UserModel user){
        return new ResponseEntity<User>( userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> delete () throws ResourceNotFoundException{
        userService.delete();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
