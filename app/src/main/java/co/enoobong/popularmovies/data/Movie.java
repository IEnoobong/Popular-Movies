/*
 * Copyright (c) 2017. Ibanga Enoobong Ime (Entrepreneur, Developer Marathoner)
 *
 */

package co.enoobong.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
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
    @SerializedName("id")
    private Integer movieId;
    private String posterUrl = "https://image.tmdb.org/t/p/w185";

    public Movie() {
    }

    protected Movie(Parcel in) {
        overview = in.readString();
        releaseDate = in.readString();
        title = in.readString();
        voteAverage = in.readDouble();
        posterPath = in.readString();
        posterUrl = in.readString();
        movieId = in.readInt();

    }

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

    public Integer getMovieId() {
        return movieId;
    }


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
        dest.writeInt(movieId);
    }
}
