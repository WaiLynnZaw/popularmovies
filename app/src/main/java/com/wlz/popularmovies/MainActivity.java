package com.wlz.popularmovies;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wlz.popularmovies.model.MovieResults;
import com.wlz.popularmovies.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {
    List<MovieResults.Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        getMovie(1);
    }

    public void getMovie(int page){
        MovieService.Implementation.get(this).getPopularMovies(page, new Callback<MovieResults>() {
            @Override
            public void success(MovieResults movieResults, Response response) {
                movieList.addAll(movieResults.results);
                for(int i =0;i<movieList.size();i++){
                    Log.e("Movie = ",movieList.get(i).original_title);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error",error.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
