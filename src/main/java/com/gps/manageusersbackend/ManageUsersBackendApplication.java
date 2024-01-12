package com.gps.manageusersbackend;

import com.gps.manageusersbackend.dtos.UserDto;
import com.gps.manageusersbackend.entities.User;
import com.gps.manageusersbackend.repositories.UserRepository;
import com.gps.manageusersbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ManageUsersBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageUsersBackendApplication.class, args);
    }


    @Bean
    CommandLineRunner start(UserRepository userRepository) {
        return args -> {
            List<User> users = List.of(
                    User.builder()
                            .firstName("Mat")
                            .lastName("Dickerson")
                            .email("matt.dickeson@gmail.com")
                            .build(),
                    User.builder()
                            .firstName("Brad")
                            .lastName("Mason")
                            .email("brad.mason@gmail.com")
                            .build(),
                    User.builder()
                            .firstName("Guillaume")
                            .lastName("LE CORRE")
                            .email("guillaume@gmail.com")
                            .build(),
                    User.builder()
                            .firstName("Epiphane")
                            .lastName("HOUESSOU")
                            .email("epiphane@gmail.com")
                            .build()
            );
            userRepository.saveAll(users);
        };
    }
}
