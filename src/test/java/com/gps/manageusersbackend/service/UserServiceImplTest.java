package com.gps.manageusersbackend.service;

import com.gps.manageusersbackend.Builders.Builder;
import com.gps.manageusersbackend.dtos.UserDto;
import com.gps.manageusersbackend.entities.User;
import com.gps.manageusersbackend.exceptions.UserNotFoundException;
import com.gps.manageusersbackend.mappers.UserMapperImpl;
import com.gps.manageusersbackend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapperImpl dtoMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listUsers() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        userService.listUsers();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void saveUser() {
        UserDto userDto = new UserDto();
        User user = new User();

        when(dtoMapper.fromUserDto(userDto)).thenReturn(user);

        userService.saveUser(userDto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUser() throws UserNotFoundException {
        Long userId = 1L;
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(dtoMapper.fromUser(user)).thenReturn(userDto);

        UserDto result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals(userDto, result);
    }

    @Test
    void getUser_UserNotFoundException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(userId));
    }

    @Test
    void updateUser() {
        UserDto userDto = Builder.getUserDto_1();
        User user = Builder.getUser_1();

        when(dtoMapper.fromUserDto(userDto)).thenReturn(user);
        when(dtoMapper.fromUser(user)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.updateUser(userDto);

        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void searchUsers() {
        String keyword = "test";
        List<User> users = new ArrayList<>();

        when(userRepository.searchUser(keyword)).thenReturn(users);

        userService.searchUsers(keyword);

        verify(userRepository, times(1)).searchUser(keyword);
    }
}
