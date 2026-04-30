package com.movieDesk.MovieDesk.services;

import com.movieDesk.MovieDesk.entity.Category;
import com.movieDesk.MovieDesk.entity.Movie;
import com.movieDesk.MovieDesk.entity.Streaming;
import com.movieDesk.MovieDesk.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServices {

    private final MovieRepository movieRepository;
    private final CategoryServices categoryServices;
    private final StreamingServices streamingServices;


    public Movie create(Movie movie){
        movie.setCategories(this.listCategories(movie.getCategories()));
        movie.setStreamings(this.listStreaming(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findByCategoriesId(List.of(Category.builder().id(categoryId).build()));
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }



    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long id, Movie movie){
        Optional<Movie> byId = movieRepository.findById(id);
        if (byId.isPresent()){

            List<Category> categories = this.listCategories(movie.getCategories());
            List<Streaming> streamings = this.listStreaming(movie.getStreamings());

            Movie movieToUpdate = byId.get();
            movieToUpdate.setTitle(movie.getTitle());
            movieToUpdate.setDescription(movie.getDescription());
            movieToUpdate.setReleaseDate(movie.getReleaseDate());
            movieToUpdate.setRating(movie.getRating());

            movieToUpdate.getCategories().clear();
            movieToUpdate.getCategories().addAll(categories);

            movieToUpdate.getStreamings().clear();
            movieToUpdate.getStreamings().addAll(streamings);

            movieRepository.save(movieToUpdate);
            return Optional.of(movieToUpdate);
        }
        return Optional.empty();
    }




    private List<Category> listCategories(List<Category> categories){
        List<Category> categoryList = new ArrayList<>();
        categories.forEach(category -> {
            categoryServices.getCategoryById(category.getId()).ifPresent(categoryList::add);
                });
        return categoryList;
    }

    private List<Streaming> listStreaming (List<Streaming> streaming){
        List<Streaming> streamingList = new ArrayList<>();
        streaming.forEach(s -> {
            streamingServices.getById(s.getId()).ifPresent(streamingList::add);
        });
        return streamingList;
    }




}
