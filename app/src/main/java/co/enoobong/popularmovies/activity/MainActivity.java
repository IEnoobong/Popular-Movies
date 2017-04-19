/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.enoobong.popularmovies.R;
import co.enoobong.popularmovies.adapter.MoviesAdapter;
import co.enoobong.popularmovies.model.Movies;
import co.enoobong.popularmovies.network.ApiClient;
import co.enoobong.popularmovies.network.ApiInterface;
import co.enoobong.popularmovies.utility.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.enoobong.popularmovies.adapter.MoviesAdapter.MOVIE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String SORT_STATE = "sort_state";
    private final static String SORT_TOP = "Top Rated";
    private final static String SORT_POPULAR = "Popular";
    private static final ApiInterface API_INTERFACE = ApiClient.getClient().create(ApiInterface.class);
    private static final Type TYPE = new TypeToken<List<Movies>>() {
    }.getType();
    private boolean isTopRated = false;
    private RecyclerView mMoviesRecyclerView;
    private ProgressBar mLoadingIndicator;
    private ArrayList<Movies> mMoviesList;
    private MoviesAdapter mMoviesAdapter;
    private CollapsingToolbarLayout toolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMoviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.progressBar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (savedInstanceState == null || !savedInstanceState.containsKey(MOVIE) || !savedInstanceState.containsKey(SORT_STATE)) {
            getMoviesBySortOrder(isTopRated);
        } else if (savedInstanceState.getParcelableArrayList(MOVIE) == null) {
            getMoviesBySortOrder(isTopRated);
        } else {
            mMoviesList = savedInstanceState.getParcelableArrayList(MOVIE);
            isTopRated = savedInstanceState.getBoolean(SORT_STATE);
            if (isTopRated) {
                toolbarLayout.setTitle(getString(R.string.title, SORT_TOP));
            } else {
                toolbarLayout.setTitle(getString(R.string.title, SORT_POPULAR));
            }
            loadData();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIE, mMoviesList);
        outState.putBoolean(SORT_STATE, isTopRated);
    }


    /**
     * helper method to load data in views
     */
    private void loadData() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mMoviesAdapter = new MoviesAdapter(MainActivity.this, mMoviesList);
        mMoviesRecyclerView.setAdapter(mMoviesAdapter);
    }


    /**
     * helper method to get popular movies
     */
    private void getPopularMovies() {
        Call<JsonObject> call = API_INTERFACE.getPopularMovies(getString(R.string.movie_db_api_key), 1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                mMoviesList = new Gson().fromJson(response.body().getAsJsonArray("results"), TYPE);
                loadData();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                t.printStackTrace();
                Utility.showToast(MainActivity.this, getString(R.string.error_toast), Toast.LENGTH_LONG);


            }
        });
    }

    /**
     * helper method to get top rated movies
     */
    private void getTopRatedMovies() {
        Call<JsonObject> call = API_INTERFACE.getTopRatedMovies(getString(R.string.movie_db_api_key), 1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                mMoviesList = new Gson().fromJson(response.body().getAsJsonArray("results"), TYPE);
                loadData();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                t.printStackTrace();
                Utility.showToast(MainActivity.this, getString(R.string.error_toast), Toast.LENGTH_LONG);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals(SORT_TOP)) {
            getMoviesBySortOrder(true);
            isTopRated = true;
            item.setTitle(SORT_POPULAR);
        } else if (item.getTitle().equals(SORT_POPULAR)) {
            getMoviesBySortOrder(false);
            isTopRated = false;
            item.setTitle(SORT_TOP);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.sort_order);
        if (isTopRated) {
            menuItem.setTitle(SORT_POPULAR);
        } else {
            menuItem.setTitle(SORT_TOP);
        }
        return true;
    }
    /**
     * method to re-query movie db API and update views based on user sort selection
     *
     * @param sortChoice selected sort choice
     */
    private void getMoviesBySortOrder(boolean sortChoice) {
        if (sortChoice) {
            toolbarLayout.setTitle(getString(R.string.title, SORT_TOP));
            if (Utility.isNetworkConnected(this)) {
                getTopRatedMovies();
            } else {
                Utility.showDialog(this, android.R.drawable.ic_dialog_alert, R.string.no_network)
                        .setPositiveButton(R.string.action_settings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(Settings.ACTION_SETTINGS));
                            }
                        })
                        .show();
            }
        } else {
            toolbarLayout.setTitle(getString(R.string.title, SORT_POPULAR));
            getPopularMovies();
        }
    }
}
