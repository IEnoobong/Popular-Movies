package co.enoobong.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_MOVIE_TABLE = "";
    private static final String CREATE_TRAILERS_TABLE = "";
    private static final String CREATE_REVIEWS_TABLE = "";

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
