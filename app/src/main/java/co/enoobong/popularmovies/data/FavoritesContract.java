package co.enoobong.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesContract {

    static final String CONTENT_AUTHORITY = "co.enoobong.popularmovies";
    static final String PATH_MOVIE = "movie";
    static final String PATH_TRAILER = "trailer";
    static final String PATH_REVIEW = "review";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri MOVIES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
    private static final Uri TRAILERS_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRAILER).build();
    private static final Uri REVIEWS_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEW).build();

    public static Uri buildMovieUriWithId(int id) {
        return MOVIES_CONTENT_URI.buildUpon().appendPath(Integer.toString(id))
                .build();
    }

    public static Uri buildTrailerUriWithId(int id) {
        return TRAILERS_CONTENT_URI.buildUpon().appendPath(Integer.toString(id))
                .build();
    }

    public static Uri buildReviewUriWithId(int id) {
        return REVIEWS_CONTENT_URI.buildUpon().appendPath(Integer.toString(id))
                .build();
    }


    public static final class MoviesEntry implements BaseColumns {
        public static final String COLUMN_ID = "movie_id";
        //Constants for MovieTable
        static final String MOVIE_TABLE = "movies";
        static final String COLUMN_OVERVIEW = "overview";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_RELEASE_DATE = "release_date";
        static final String COLUMN_VOTE_AVERAGE = "vote_average";
        static final String COLUMN_POSTER_PATH = "poster_path";
    }

    static final class TrailerEntry implements BaseColumns {
        static final String TRAILER_TABLE = "trailer";
        static final String COLUMN_VIDEO_URL = "video_url";
        static final String COLUMN_NAME = "name";
    }

    static final class ReviewsEntry implements BaseColumns {
        static final String REVIEWS_TABLE = "reviews";
        static final String COLUMN_AUTHOR = "author";
        static final String COLUMN_CONTENT = "content";
    }
}
