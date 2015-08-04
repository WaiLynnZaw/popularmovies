package com.wlz.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlz.popularmovies.model.MovieResults;
import com.wlz.popularmovies.model.MovieTrailerResults;
import com.wlz.popularmovies.service.MovieService;
import com.wlz.popularmovies.util.Constants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by WaiLynnZaw on 7/26/15.
 */
public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener{
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
    @Bind(R.id.trailer_layout_one)
    LinearLayout trailer_layout_one;
    @Bind(R.id.trailer_layout_two)
    LinearLayout trailer_layout_two;
    @Bind(R.id.trailer_one)
    TextView txt_trailer_one;
    @Bind(R.id.trailer_two)
    TextView txt_trailer_two;
    ArrayList<MovieTrailerResults.MovieTrailer> movieTrailers;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        MovieResults.Movie movie = (MovieResults.Movie) getIntent().getSerializableExtra("movie");
        setSupportActionBar(toolbar);
        setupActionBar(getSupportActionBar());
        movieTrailers = new ArrayList<>();
        callMoiveTrailer(movie.id);
        collapsingToolbar.setTitle(movie.original_title);
        Glide.with(this).load(Constants.BACKDROP_URL+movie.backdrop_path).into(backdrop);
        Glide.with(this).load(Constants.POSTER_URL+movie.poster_path).into(poster_image);

        desc_text.setText(movie.overview);
        txt_release_date.setText(movie.release_date);
        txt_vote_average.setText(movie.vote_average);
        txt_vote_count.setText(movie.vote_count);

        trailer_layout_one.setOnClickListener(this);
        trailer_layout_two.setOnClickListener(this);
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

    public void callMoiveTrailer(String id){
        final ProgressDialog dialog = new ProgressDialog(MovieDetailActivity.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        MovieService.Implementation.get(getApplicationContext()).getMovieTrailers(id, new Callback<MovieTrailerResults>() {
            @Override
            public void success(MovieTrailerResults movieTrailerResults, Response response) {
                movieTrailers.addAll(movieTrailerResults.results);
                txt_trailer_one.setText(movieTrailers.get(0).name);
                txt_trailer_two.setText(movieTrailers.get(1).name);
                dialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent(getApplicationContext(), VideoDetailActivity.class);
        switch (view.getId()){
            case R.id.trailer_layout_one:
                it.putExtra("key",movieTrailers.get(0).key);
                break;
            case R.id.trailer_layout_two:
                it.putExtra("key",movieTrailers.get(1).key);
                break;
        }
        startActivity(it);
    }
}
