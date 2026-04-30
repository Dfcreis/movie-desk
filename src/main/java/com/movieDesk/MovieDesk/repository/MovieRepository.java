package com.movieDesk.MovieDesk.repository;

import com.movieDesk.MovieDesk.entity.Category;
import com.movieDesk.MovieDesk.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategoriesId(List<Category> categoryId);
}
