package com.wlz.popularmovies.util;

/**
 * Created by WaiLynnZaw on 7/21/15.
 */
public class Constants {
    public static final String END_POINT = "http://api.themoviedb.org/3";
    public static final String DISCOVER_MOVIE_POPULAR = "/discover/movie?sort_by=popularity.desc";
    public static final String DISCOVER_MOVIE_RATING = "discover/movie?sort_by=vote_average.desc&vote_count.gte=1000";
    public static final String POSTER_URL = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_URL = "http://image.tmdb.org/t/p/w342";
    public static final String MOVIE_TRAILER = "/movie/{id}/videos";
}
