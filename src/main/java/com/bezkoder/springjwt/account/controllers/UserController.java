package com.bezkoder.springjwt.account.controllers;

import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.dto.response.UserResponseDto;
import com.bezkoder.springjwt.account.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(path = "/addUser")
    @RolesAllowed("ADMIN")
    public String addUser(@RequestBody UserDto userDto) throws SQLException {
        return userDetailsService.addUser(userDto);
    }

    @PutMapping("update/{id}")
    @RolesAllowed("ADMIN")
    public String updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
       try {
           return userDetailsService.updateUser(userDto, id);
       }catch (Exception e){
           throw new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND));
       }

    }
    @DeleteMapping("/delete/{id}")
    @RolesAllowed("ADMIN")
    public String deleteUser( @PathVariable("id") Long id){
        userDetailsService.deleteUser(id);
        return "User is deleted!";
    }
    @GetMapping(path = "/getUserByEmail")
    public UserResponseDto getUserByEmail(@RequestParam String email ){
        return userDetailsService.getUserByEmail(email);
    }

    @PostMapping("/forgot-password")
    @RolesAllowed({"ADMIN","USER"})
    public String forgotPassword(@RequestParam String email) {

        String response = userDetailsService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    @RolesAllowed("ADMIN")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password) {


        return userDetailsService.resetPassword(email, password);
    }

}

