package com.movieDesk.MovieDesk.controller;


import com.movieDesk.MovieDesk.controller.request.UserRequest;
import com.movieDesk.MovieDesk.controller.response.UserResponse;
import com.movieDesk.MovieDesk.entity.User;
import com.movieDesk.MovieDesk.mapper.UserMapper;
import com.movieDesk.MovieDesk.repository.UserRepository;
import com.movieDesk.MovieDesk.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieDesk/auth")
public class AuthController {

    private final UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User save = userServices.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(save));
    }


}
