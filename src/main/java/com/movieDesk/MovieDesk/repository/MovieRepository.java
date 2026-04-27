package com.movieDesk.MovieDesk.repository;

import com.movieDesk.MovieDesk.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
