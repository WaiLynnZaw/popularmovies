package com.wlz.popularmovies.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WaiLynnZaw on 7/21/15.
 */
public class MovieResults implements Serializable {
    public int page;
    public List<Movie> results;


    public class Movie implements Serializable{
        public String id;
        public String backdrop_path;
        public String original_title;
        public String overview;
        public String release_date;
        public String poster_path;
        public String vote_average;
        public String vote_count;
    }

}
