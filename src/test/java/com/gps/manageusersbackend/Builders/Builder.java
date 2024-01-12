package com.gps.manageusersbackend.Builders;

import com.gps.manageusersbackend.dtos.UserDto;
import com.gps.manageusersbackend.entities.User;

public class Builder {
    public static User getUser_1(){
        return new User(1L,"Epiphane","HOUESSOU","epiphane@gmail.com");
    }

    public static User getUser_2(){
        return new User(2L,"Guillaume","LE CORRE","guillaume@gmail.com");
    }

    public static UserDto getUserDto_1(){
        UserDto userDto = new UserDto();
        userDto.setId(3L);
        userDto.setFirstName("Mat");
        userDto.setLastName("Dickerson");
        userDto.setEmail("matt.dickeson@gmail.com");
        return userDto;
    }
}
