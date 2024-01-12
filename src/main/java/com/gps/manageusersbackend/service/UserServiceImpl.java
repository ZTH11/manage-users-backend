package com.gps.manageusersbackend.service;

import com.gps.manageusersbackend.dtos.UserDto;
import com.gps.manageusersbackend.entities.User;
import com.gps.manageusersbackend.exceptions.UserNotFoundException;
import com.gps.manageusersbackend.mappers.UserMapperImpl;
import com.gps.manageusersbackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapperImpl dtoMapper;

    @Override
    public List<UserDto> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> dtoMapper.fromUser(user))
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDto userDto) {
        log.info("Saving new Customer");
        User user = dtoMapper.fromUserDto(userDto);
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return dtoMapper.fromUser(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("Updating User");
        User user = dtoMapper.fromUserDto(userDto);
        User savedUser = userRepository.save(user);
        return dtoMapper.fromUser(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> searchUsers(String keyword) {
        List<User> users = userRepository.searchUser(keyword);
        return users.stream().map(user -> dtoMapper.fromUser(user)).collect(Collectors.toList());
    }


}
