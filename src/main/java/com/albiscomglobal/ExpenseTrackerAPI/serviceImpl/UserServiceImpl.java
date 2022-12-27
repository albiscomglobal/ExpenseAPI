package com.albiscomglobal.ExpenseTrackerAPI.serviceImpl;


import com.albiscomglobal.ExpenseTrackerAPI.Repository.UserRepository;
import com.albiscomglobal.ExpenseTrackerAPI.domain.User;
import com.albiscomglobal.ExpenseTrackerAPI.domain.UserModel;
import com.albiscomglobal.ExpenseTrackerAPI.exceptions.ItemAlreadyExistException;
import com.albiscomglobal.ExpenseTrackerAPI.exceptions.ResourceNotFoundException;
import com.albiscomglobal.ExpenseTrackerAPI.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistException("User is already register with email "  + user.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User readUSer(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id " + id));
    }

    @Override
    public User update(UserModel user, Long id)  {
        User existingUser = readUSer(id);
        existingUser.setName(user.getName() != null?  user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null?  user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null?  bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null?  user.getAge() : existingUser.getAge());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User existingUser = readUSer(id);
        userRepository.delete(existingUser);

    }

    @Override
    public User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

       return  userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not Found for the email " + email));

    }
}
