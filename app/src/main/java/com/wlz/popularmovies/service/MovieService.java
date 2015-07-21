package com.wlz.popularmovies.service;

import android.content.Context;

import com.wlz.popularmovies.BuildConfig;
import com.wlz.popularmovies.R;
import com.wlz.popularmovies.model.MovieResults;
import com.wlz.popularmovies.util.Constants;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by WaiLynnZaw on 7/21/15.
 */
public interface MovieService {
    @GET(Constants.DISCOVER_MOVIE_POPULAR)
    void getPopularMovies(@Query("page") int page, Callback<MovieResults> callback);

    @GET(Constants.DISCOVER_MOVIE_RATING)
    void getTopRatedMovies(@Query("page") int page, Callback<MovieResults> callback);


    class Implementation {
        public static MovieService get(Context context) {
            return getBuilder(context)
                    .build()
                    .create(MovieService.class);
        }
        static RestAdapter.Builder getBuilder(final Context context) {
            return new RestAdapter.Builder()
                    .setEndpoint(Constants.END_POINT)
                    .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            request.addQueryParam("api_key",context.getResources().getString(R.string.api_key));
                        }
                    });
        }
    }
}