package com.movieDesk.MovieDesk.mapper;

import com.movieDesk.MovieDesk.controller.request.UserRequest;
import com.movieDesk.MovieDesk.controller.response.UserResponse;
import com.movieDesk.MovieDesk.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {


    public static User toUser(UserRequest request){
       return User.builder()
               .name(request.name())
               .email(request.email())
               .password(request.password())
               .build();
    }

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }





}
