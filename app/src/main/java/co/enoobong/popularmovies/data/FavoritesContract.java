package co.enoobong.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesContract {

    public static final String CONTENT_AUTHORITY = "co.enoobong.popularmovies";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_TRAILER = "trailer";
    public static final String PATH_REVIEW = "review";

    public static final Uri MOVIES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
    public static final Uri TRAILERS_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRAILER).build();
    public static final Uri REVIEWS_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEW).build();

    public static final Uri buildMovieUriWithId(int id) {
        return MOVIES_CONTENT_URI.buildUpon().appendPath(Integer.toString(id))
                .build();
    }

    public static final Uri buildTrailerUriWithId(int id) {
        return TRAILERS_CONTENT_URI.buildUpon().appendPath(Integer.toString(id))
                .build();
    }

    public static final Uri buildReviewUriWithId(int id) {
        return REVIEWS_CONTENT_URI.buildUpon().appendPath(Integer.toString(id))
                .build();
    }


    public static final class MoviesEntry implements BaseColumns {
        //Constants for MovieTable
        public static final String MOVIE_TABLE = "movies";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_ID = "movie_id";
    }

    public static final class TrailerEntry implements BaseColumns {
        public static final String TRAILER_TABLE = "trailer";
        public static final String COLUMN_VIDEO_URL = "video_url";
        public static final String COLUMN_NAME = "name";
    }

    public static final class ReviewsEntry implements BaseColumns {
        public static final String REVIEWS_TABLE = "reviews";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_CONTENT = "content";
    }
}
