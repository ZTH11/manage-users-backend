package com.gps.manageusersbackend.service;

import com.gps.manageusersbackend.dtos.UserDto;
import com.gps.manageusersbackend.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> listUsers();

    void saveUser(UserDto userDto);

    UserDto getUser(Long userId) throws UserNotFoundException;

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);

    List<UserDto> searchUsers(String keyword);
}
