package com.wlz.popularmovies.model;

import java.util.List;

/**
 * Created by WaiLynnZaw on 7/21/15.
 */
public class MovieResults {
    public int page;
    public List<Movie> results;


    public class Movie {
        public String id;
        public String backdrop_path;
        public String original_title;
        public String overview;
        public String release_date;
        public String poster_path;
        public String vote_average;
    }

}
