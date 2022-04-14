package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {

        String response = userDetailsService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {


        return userDetailsService.resetPassword(token, password);
    }


    @PostMapping(path = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) {
        return userDetailsService.addUser(user);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userDetailsService.updateUser(user, id);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser( @PathVariable("id") Long id){
        userDetailsService.deleteUser(id);
        return "User deleted!";
    }
 @GetMapping(path = "/getUserBy/{id}")
    public User get(@PathVariable Long id ){
        return userDetailsService.getUserById(id);
 }


}

