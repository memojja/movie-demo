package com.ab2018.controller;

import com.ab2018.exception.MovieNotFoundException;
import com.ab2018.model.Movie;
import com.ab2018.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id")Integer id){
        return movieService.getMovieByTheMovieDbId(id).orElseThrow(() -> new MovieNotFoundException());
    }

    @GetMapping("/limit/{limitNumber}")
    public List<Movie> getMoviesBylimit(@PathVariable("limitNumber")Integer limitNumber){
        return movieService.getMovieByLimit(limitNumber).get();
    }
}
