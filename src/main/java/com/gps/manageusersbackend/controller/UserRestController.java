package com.gps.manageusersbackend.controller;

import com.gps.manageusersbackend.dtos.UserDto;
import com.gps.manageusersbackend.exceptions.UserNotFoundException;
import com.gps.manageusersbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserRestController {

    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> users() {
        return userService.listUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto userById(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @GetMapping("/users/search")
    public List<UserDto> searchUsers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return userService.searchUsers("%" + keyword + "%");
    }

    @PostMapping("/users")
    public void saveCustomer(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }

    @PutMapping("/users/{id}")
    public UserDto updateCustomer(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
