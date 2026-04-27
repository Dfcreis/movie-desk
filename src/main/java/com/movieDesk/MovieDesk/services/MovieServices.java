package com.movieDesk.MovieDesk.services;

import com.movieDesk.MovieDesk.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServices {

    private final MovieRepository movieRepository;

}
