package co.enoobong.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_MOVIE_TABLE = "CREATE TABLE " + FavoritesContract.MoviesEntry.MOVIE_TABLE + " (" +
            FavoritesContract.MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavoritesContract.MoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_ID + " INTEGER NOT NULL" +
            ");";
    private static final String CREATE_TRAILERS_TABLE = "CREATE TABLE " + FavoritesContract.TrailerEntry.TRAILER_TABLE + " (" +
            FavoritesContract.TrailerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavoritesContract.TrailerEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            FavoritesContract.TrailerEntry.COLUMN_VIDEO_URL + " TEXT NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_ID + " INTEGER NOT NULL" +
            ");";
    private static final String CREATE_REVIEWS_TABLE = "CREATE TABLE " + FavoritesContract.ReviewsEntry.REVIEWS_TABLE + " (" +
            FavoritesContract.ReviewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavoritesContract.ReviewsEntry.COLUMN_AUTHOR + " TEXT NOT NULL, " +
            FavoritesContract.ReviewsEntry.COLUMN_CONTENT + " TEXT NOT NULL, " +
            FavoritesContract.MoviesEntry.COLUMN_ID + " INTEGER NOT NULL" +
            ");";

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_TRAILERS_TABLE);
        db.execSQL(CREATE_REVIEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.MoviesEntry.MOVIE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.TrailerEntry.TRAILER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.ReviewsEntry.REVIEWS_TABLE);
        onCreate(db);
    }
}
