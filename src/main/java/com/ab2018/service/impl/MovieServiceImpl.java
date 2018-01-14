package com.ab2018.service.impl;

import com.ab2018.model.Movie;
import com.ab2018.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class MovieServiceImpl implements MovieService {

    private final RestTemplate restTemplate;

    @Value("${apiUrl}")
    private String apiUrl;

    @Value("${apiKey}")
    private String apiKey;

    @Autowired
    public MovieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Movie> getMovieByTheMovieDbId(int id) {
        String url = prepareUrl(id);
        ResponseEntity<Movie> movie = getMovieResponseFromUrl(url);
        return Optional.ofNullable(movie.getBody());
    }

    @Override
    public Optional<List<Movie>> getMovieByLimit(int limit) {
        List<Movie> movies = new ArrayList<>();
        IntStream.range(2,limit+2).parallel().forEach(
                number -> {
                    String url = prepareUrl(number);
                    Movie movie = getMovieResponseFromUrl(url).getBody();
                    movies.add(movie);
                }
        );
        return Optional.ofNullable(movies);
    }

    private String prepareUrl(int number) {
        return apiUrl + number + "?api_key=" + apiKey;
    }

    private ResponseEntity<Movie> getMovieResponseFromUrl(String url) {
        return  Optional.ofNullable(restTemplate.exchange(
                url,
                HttpMethod.GET,
                getHttpEntity(),
                Movie.class))
                .orElse(new ResponseEntity<Movie>(HttpStatus.NOT_FOUND));
    }

    private HttpEntity getHttpEntity() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity(httpHeaders);
    }

}
