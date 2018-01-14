package com.ab2018.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Movie {

    private String budget;
    private String vote_average;
    private String backdrop_path;
    private String status;
    private String runtime;
    private String adult;
    private String homepage;
    private String id;
    private String title;
    private String original_language;
    private String overview;
    private String imdb_id;
    private String release_date;
    private String original_title;
    private String vote_count;
    private String poster_path;
    private String video;
    private String tagline;
    private String revenue;
    private String popularity;

    private Gendres[] genres;
    private SpokenLanguages[] spoken_languages;
    private ProductionCountries[] production_countries;
    private ProductionCompanies[] production_companies;
    private BelongsToCollection belongs_to_collection;


}
