package com.albiscomglobal.ExpenseTrackerAPI.controller;

import com.albiscomglobal.ExpenseTrackerAPI.domain.AuthModel;
import com.albiscomglobal.ExpenseTrackerAPI.domain.JwtResponse;
import com.albiscomglobal.ExpenseTrackerAPI.domain.User;
import com.albiscomglobal.ExpenseTrackerAPI.domain.UserModel;
import com.albiscomglobal.ExpenseTrackerAPI.security.CustomUserDetailsService;
import com.albiscomglobal.ExpenseTrackerAPI.service.UserService;
import com.albiscomglobal.ExpenseTrackerAPI.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {
      authenticate(authModel.getEmail(), authModel.getPassword());


        //we need to generate the jwt token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (DisabledException e) {
            throw new Exception("User disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");

        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUSers(@Valid @RequestBody UserModel user ){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);


    }

}
