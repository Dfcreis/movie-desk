package com.movieDesk.MovieDesk.controller;

import com.movieDesk.MovieDesk.controller.request.MovieRequest;
import com.movieDesk.MovieDesk.controller.response.MovieResponse;
import com.movieDesk.MovieDesk.entity.Movie;
import com.movieDesk.MovieDesk.mapper.MovieMapper;
import com.movieDesk.MovieDesk.services.MovieServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/movieDesk/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServices movieServices;

    @PostMapping("/create")
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        Movie saveMovie = movieServices.create(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(saveMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieServices.getAllMovies();
        List<MovieResponse> response = movies.stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return movieServices.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@Valid @PathVariable Long id, @RequestBody MovieRequest request) {
        return movieServices.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/saarch")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(movieServices.findByCategory(categoryId)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        Optional<Movie> movieDelete = movieServices.findById(id);
        if (movieDelete.isPresent()) {
            movieServices.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
