/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Movies implements Parcelable{
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_title")
    private String title;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;

    private String backdropUrl = "https://image.tmdb.org/t/p/w500";
    private String posterUrl = "https://image.tmdb.org/t/p/w185";

    public Movies(){    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    private String getPosterPath() {
        return posterPath;
    }

    public String getPosterUrl() {
        return posterUrl + getPosterPath();
    }

    private String getBackdropPath() {
        return backdropPath;
    }

    public String getBackdropUrl() {
        return backdropUrl + getBackdropPath();
    }

    protected Movies(Parcel in) {
        overview = in.readString();
        releaseDate = in.readString();
        title = in.readString();
        voteAverage = in.readDouble();
        posterPath = in.readString();
        posterUrl = in.readString();
        backdropPath = in.readString();
        backdropUrl = in.readString();

    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(title);
        dest.writeDouble(voteAverage);
        dest.writeString(posterPath);
        dest.writeString(posterUrl);
        dest.writeString(backdropPath);
        dest.writeString(backdropUrl);
    }
}
