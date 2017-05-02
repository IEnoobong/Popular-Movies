/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.enoobong.popularmovies.R;
import co.enoobong.popularmovies.adapter.ReviewsAdapter;
import co.enoobong.popularmovies.adapter.TrailerAdapter;
import co.enoobong.popularmovies.data.Movies;
import co.enoobong.popularmovies.data.Reviews;
import co.enoobong.popularmovies.data.Trailer;
import co.enoobong.popularmovies.network.ApiClient;
import co.enoobong.popularmovies.network.ApiInterface;
import co.enoobong.popularmovies.utility.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.enoobong.popularmovies.adapter.MoviesAdapter.MOVIE;


public class MovieDetailActivity extends AppCompatActivity {

    private static final ApiInterface API_INTERFACE = ApiClient.getClient().create(ApiInterface.class);
    @BindView(R.id.tv_movie_title)
    TextView mMovieTitle;
    @BindView(R.id.tv_rating)
    TextView mMovieRating;
    @BindView(R.id.tv_release_date)
    TextView mMovieReleaseDate;
    @BindView(R.id.tv_overview)
    TextView mMovieOverview;
    @BindView(R.id.tv_reviews)
    TextView mReview;
    @BindView(R.id.tv_trailers)
    TextView mTrailer;
    @BindView(R.id.im_movie_poster)
    ImageView mMoviePoster;
    @BindView(R.id.rv_trailers)
    RecyclerView mTrailerRecycler;
    @BindView(R.id.rv_reviews)
    RecyclerView mReviewRecycler;
    private ArrayList<Trailer> mTrailersList;
    private ArrayList<Reviews> mReviewsList;
    private TrailerAdapter mTrailerAdapter;
    private ReviewsAdapter mReviewsAdapter;
    private Movies selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);

        selectedMovie = getIntent().getParcelableExtra(MOVIE);

        mMovieTitle.setText(selectedMovie.getTitle());
        mMovieRating.setText(getString(R.string.vote_average, selectedMovie.getVoteAverage()));
        mMovieReleaseDate.setText(selectedMovie.getReleaseDate());
        mMovieOverview.setText(selectedMovie.getOverview());
        Glide.with(this)
                .load(selectedMovie.getPosterUrl())
                .thumbnail(0.1f)
                .into(mMoviePoster);
        getTrailers();
        getReviews();
    }

    private void getTrailers() {
        Call<JsonObject> call = API_INTERFACE.getMovieTrailers(selectedMovie.getMovieId(), getString(R.string.movie_db_api_key));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Type trailerType = new TypeToken<List<Trailer>>() {
                }.getType();
                mTrailersList = new Gson().fromJson(response.body().getAsJsonArray("results"), trailerType);
                if (mTrailersList.isEmpty()) mTrailer.setText(getString(R.string.no_trailers));
                mTrailerAdapter = new TrailerAdapter(MovieDetailActivity.this, mTrailersList);
                mTrailerRecycler.setAdapter(mTrailerAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Utility.showToast(MovieDetailActivity.this, getString(R.string.error_toast), Toast.LENGTH_LONG);
            }
        });
    }

    private void getReviews() {
        Call<JsonObject> call = API_INTERFACE.getMovieReviews(selectedMovie.getMovieId(), getString(R.string.movie_db_api_key));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Type reviewType = new TypeToken<List<Reviews>>() {
                }.getType();
                mReviewsList = new Gson().fromJson(response.body().getAsJsonArray("results"), reviewType);
                Log.d("TAG", Arrays.toString(mReviewsList.toArray()));
                if (mReviewsList.isEmpty()) mReview.setText(getString(R.string.no_reviews));
                mReviewsAdapter = new ReviewsAdapter(MovieDetailActivity.this, mReviewsList);
                mReviewRecycler.setAdapter(mReviewsAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Utility.showToast(MovieDetailActivity.this, getString(R.string.error_toast), Toast.LENGTH_LONG);

            }
        });
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
