package com.wlz.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.wlz.popularmovies.adapter.MovieAdapter;
import com.wlz.popularmovies.adapter.PaginateAdapter;
import com.wlz.popularmovies.model.MovieResults;
import com.wlz.popularmovies.service.MovieService;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {
    ArrayList<MovieResults.Movie> movieList;
    @Bind(R.id.movie_recycler_view)
    RecyclerView moview_recycler_view;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.spinner_nav)
    Spinner spinner;
    MovieAdapter movieAdapter;
    GridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpToolbar(getSupportActionBar());
        init();
        setUpListener();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        movieList.clear();
                        movieAdapter.notifyDataSetChanged();
                        getPopularMovie(1);
                        moview_recycler_view.addOnScrollListener(new PaginateAdapter(layoutManager,1) {
                            @Override
                            public void onLoadMore(int current_page) {
                                getPopularMovie(current_page);
                            }
                        });

                        break;
                    case 1:
                        movieList.clear();
                        movieAdapter.notifyDataSetChanged();
                        getRatingMovie(1);
                        moview_recycler_view.addOnScrollListener(new PaginateAdapter(layoutManager,1) {
                            @Override
                            public void onLoadMore(int current_page) {
                                getRatingMovie(current_page);
                            }
                        });

                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    private void init(){
        movieList = new ArrayList<>();
        moview_recycler_view.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        moview_recycler_view.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(movieList);
        moview_recycler_view.setAdapter(movieAdapter);
    }
    private void setUpListener(){
        movieAdapter.setOnItemClickListener(new MovieAdapter.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(MainActivity.this, MovieDetailActivity.class);
                it.putExtra("movie", movieList.get(position));
                startActivity(it);
            }
        });
    }
    public void getPopularMovie(int page){
        progressBar.setVisibility(View.VISIBLE);
        MovieService.Implementation.get(this).getPopularMovies(page,movieCallBack);
    }
    public void getRatingMovie(int page){
        progressBar.setVisibility(View.VISIBLE);
        MovieService.Implementation.get(this).getTopRatedMovies(page,movieCallBack);
    }

    Callback<MovieResults> movieCallBack = new Callback<MovieResults>() {
        @Override
        public void success(MovieResults movieResults, Response response) {
            progressBar.setVisibility(View.GONE);
            movieList.addAll(movieResults.results);
            movieAdapter.notifyDataSetChanged();
        }
        @Override
        public void failure(RetrofitError error) {
            progressBar.setVisibility(View.GONE);
        }
    };
    private void setUpToolbar(ActionBar actionBar){
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setIcon(R.mipmap.ic_launcher);
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
