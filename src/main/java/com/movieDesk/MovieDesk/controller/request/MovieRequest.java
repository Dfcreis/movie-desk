package com.movieDesk.MovieDesk.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest( @NotEmpty(message = "titulo do filme e Obrigatorio") String title,
                           String description,
                           LocalDate releaseDate,
                           Double rating,
                           List<Long> categories,
                           List<Long> streaming
){}
