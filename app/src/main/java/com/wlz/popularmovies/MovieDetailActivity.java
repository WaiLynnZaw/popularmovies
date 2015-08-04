package com.wlz.popularmovies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlz.popularmovies.model.MovieResults;
import com.wlz.popularmovies.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by WaiLynnZaw on 7/26/15.
 */
public class MovieDetailActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.desc_text)
    TextView desc_text;
    @Bind(R.id.poster_image)
    ImageView poster_image;
    @Bind(R.id.txt_release_date)
    TextView txt_release_date;
    @Bind(R.id.txt_vote_average)
    TextView txt_vote_average;
    @Bind(R.id.txt_vote_count)
    TextView txt_vote_count;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        MovieResults.Movie movie = (MovieResults.Movie) getIntent().getSerializableExtra("movie");
        setSupportActionBar(toolbar);
        setupActionBar(getSupportActionBar());

        collapsingToolbar.setTitle(movie.original_title);
        Glide.with(this).load(Constants.BACKDROP_URL+movie.backdrop_path).into(backdrop);
        Glide.with(this).load(Constants.POSTER_URL+movie.poster_path).into(poster_image);

        desc_text.setText(movie.overview);
        txt_release_date.setText(movie.release_date);
        txt_vote_average.setText(movie.vote_average);
        txt_vote_count.setText(movie.vote_count);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void setupActionBar(ActionBar actionBar) {
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }
}
