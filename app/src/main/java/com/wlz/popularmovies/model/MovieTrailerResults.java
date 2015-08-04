package com.wlz.popularmovies.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WaiLynnZaw on 8/4/15.
 */
public class MovieTrailerResults implements Serializable {
    public String id;
    public List<MovieTrailer> results;
    public class MovieTrailer implements Serializable{
        public String id;
        public String iso_639_1;
        public String key;
        public String name;
        public String site;
        public String size;
        public String type;
    }
}
