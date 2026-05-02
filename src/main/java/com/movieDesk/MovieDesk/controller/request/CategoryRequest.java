package com.movieDesk.MovieDesk.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Nome de categoria obrigatorio") String name) {
}
