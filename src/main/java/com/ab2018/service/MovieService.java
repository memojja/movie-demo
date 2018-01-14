package com.ab2018.service;

import com.ab2018.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    Optional<Movie> getMovieByTheMovieDbId(int theMovieDbId);
    Optional<List<Movie>> getMovieByLimit(int limit);
}
