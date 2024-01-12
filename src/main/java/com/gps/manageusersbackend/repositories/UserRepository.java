package com.gps.manageusersbackend.repositories;

import com.gps.manageusersbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.firstName like :keyword or u.lastName like :keyword")
    List<User> searchUser(@Param("keyword") String keyword);
}
