/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.enoobong.popularmovies.R;
import co.enoobong.popularmovies.activity.MovieDetailActivity;
import co.enoobong.popularmovies.data.Movies;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    public static final String MOVIE = "movie";
    private Context mContext;
    private List<Movies> mMoviesList;

    public MoviesAdapter(final Context context, List<Movies> moviesList) {
        mContext = context;
        mMoviesList = moviesList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.setMovieThumbnail(mContext, mMoviesList.get(position).getPosterUrl());
    }


    @Override
    public int getItemCount() {
        if (mMoviesList == null) {
            return 0;
        }
        return mMoviesList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mMovieThumbnail;

        public MovieViewHolder(final View itemView) {
            super(itemView);
            mMovieThumbnail = (ImageView) itemView.findViewById(R.id.iv_movie);
            itemView.setOnClickListener(this);
        }


        /**
         * convenience method to set movie thumbnail in ImageView
         *
         * @param context  Context of the activity where the view is displayed
         * @param imageUrl String URL of the image to be displayed
         */
        void setMovieThumbnail(final Context context, final String imageUrl) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(mMovieThumbnail);
        }

        /**
         * Called whenever a user clicks a view
         *
         * @param v The view that was clicked
         */
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(MOVIE, mMoviesList.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}