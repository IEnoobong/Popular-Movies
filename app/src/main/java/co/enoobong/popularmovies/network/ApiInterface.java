/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<JsonObject> getPopularMovies(@Query(value = "api_key") String API_KEY, @Query("page") int page);

    @GET("movie/top_rated")
    Call<JsonObject> getTopRatedMovies(@Query(value = "api_key") String API_KEY, @Query("page") int page);

}
