package com.bezkoder.springjwt.account.controllers;

import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
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
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password) {


        return userDetailsService.resetPassword(email, password);
    }


    @PostMapping(path = "/create")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(@RequestBody UserDto userDto) throws SQLException {
        return userDetailsService.addUser(userDto);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
       try {
           return userDetailsService.updateUser(userDto, id);
       }catch (Exception e){
           throw new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND));
       }

    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser( @PathVariable("id") Long id){
        userDetailsService.deleteUser(id);
        return "User deleted!";
    }
 @GetMapping(path = "/getUserByEmail")
    public UserDto getUserByEmail(@RequestParam String email ){
        return userDetailsService.getUserByEmail(email);
 }


}

