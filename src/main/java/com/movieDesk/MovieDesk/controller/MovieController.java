package com.movieDesk.MovieDesk.controller;

import com.movieDesk.MovieDesk.services.MovieServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieDesk/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServices movieServices;



}
