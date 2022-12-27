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


    @GetMapping("/users/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
return new ResponseEntity<User>(userService.readUSer(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@RequestBody UserModel user, @PathVariable Long id){
        return new ResponseEntity<User>( userService.update(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete (@PathVariable Long id) throws ResourceNotFoundException{
        userService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
