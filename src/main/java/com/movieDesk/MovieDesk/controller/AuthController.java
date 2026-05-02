package com.movieDesk.MovieDesk.controller;


import com.movieDesk.MovieDesk.config.TokenServices;
import com.movieDesk.MovieDesk.controller.request.LoginRequest;
import com.movieDesk.MovieDesk.controller.request.UserRequest;
import com.movieDesk.MovieDesk.controller.response.LoginResponse;
import com.movieDesk.MovieDesk.controller.response.UserResponse;
import com.movieDesk.MovieDesk.entity.User;
import com.movieDesk.MovieDesk.exeption.UserNameOrPasswordIvalidExeption;
import com.movieDesk.MovieDesk.mapper.UserMapper;
import com.movieDesk.MovieDesk.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieDesk/auth")
public class AuthController {

    private final UserServices userServices;
    private final AuthenticationManager authenticationManager;
    private final TokenServices tokenServices;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User save = userServices.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(save));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        try{
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();
            String token = tokenServices.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        }catch (BadCredentialsException e){
            throw new UserNameOrPasswordIvalidExeption("Usuario ou senha invalido");
        }
    }
}