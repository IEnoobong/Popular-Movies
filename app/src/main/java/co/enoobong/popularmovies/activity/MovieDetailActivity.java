/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.enoobong.popularmovies.R;
import co.enoobong.popularmovies.model.Movies;

import static co.enoobong.popularmovies.adapter.MoviesAdapter.MOVIE;


public class MovieDetailActivity extends AppCompatActivity  {

    private TextView mMovieTitle, mMovieRating, mMovieReleaseDate, mMovieOverview;
    private ImageView mMovieBackdrop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mMovieRating = (TextView) findViewById(R.id.tv_rating);
        mMovieReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mMovieOverview = (TextView) findViewById(R.id.tv_overview);
        mMovieBackdrop = (ImageView) findViewById(R.id.im_movie_backdrop);

        Movies selectedMovie = getIntent().getParcelableExtra(MOVIE);

        mMovieTitle.setText(selectedMovie.getTitle());
        mMovieRating.setText(getString(R.string.vote_average, selectedMovie.getVoteAverage()));
        mMovieReleaseDate.setText(selectedMovie.getReleaseDate());
        mMovieOverview.setText(selectedMovie.getOverview());
        Glide.with(this)
                .load(selectedMovie.getBackdropUrl())
                .into(mMovieBackdrop);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
