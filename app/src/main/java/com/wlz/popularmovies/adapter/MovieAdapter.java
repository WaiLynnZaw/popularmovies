package com.wlz.popularmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wlz.popularmovies.R;
import com.wlz.popularmovies.model.MovieResults;
import com.wlz.popularmovies.util.Constants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by WaiLynnZaw on 7/21/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ClickListener mItemClickListener;
    ArrayList<MovieResults.Movie> movieList;
    public MovieAdapter(ArrayList<MovieResults.Movie> movieList){
        this.movieList = movieList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ((ViewHolder) holder).movie_title.setText(movieList.get(position).original_title);
            Glide.with(((ViewHolder) holder).movie_poster.getContext())
                    .load(Constants.POSTER_URL+movieList.get(position).poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ViewHolder)holder).movie_poster);
        }
    }
    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public void setOnItemClickListener(final ClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public interface ClickListener {
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.movie_poster)
        ImageView movie_poster;
        @Bind(R.id.movie_title)
        TextView movie_title;
        @Bind(R.id.rootLayout)
        LinearLayout touch_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            touch_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
