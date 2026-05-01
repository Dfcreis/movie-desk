package com.movieDesk.MovieDesk.services;

import com.movieDesk.MovieDesk.entity.User;
import com.movieDesk.MovieDesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }


}
