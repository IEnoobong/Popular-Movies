package co.enoobong.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static co.enoobong.popularmovies.data.FavoritesContract.CONTENT_AUTHORITY;
import static co.enoobong.popularmovies.data.FavoritesContract.MOVIES_CONTENT_URI;
import static co.enoobong.popularmovies.data.FavoritesContract.MoviesEntry;
import static co.enoobong.popularmovies.data.FavoritesContract.PATH_MOVIE;
import static co.enoobong.popularmovies.data.FavoritesContract.PATH_REVIEW;
import static co.enoobong.popularmovies.data.FavoritesContract.PATH_TRAILER;
import static co.enoobong.popularmovies.data.FavoritesContract.ReviewsEntry;
import static co.enoobong.popularmovies.data.FavoritesContract.TrailerEntry;


public class FavoritesProvider extends ContentProvider {
    public static final int CODE_MOVIE = 100;
    public static final int CODE_MOVIE_WITH_ID = 101;
    public static final int CODE_TRAILER = 200;
    public static final int CODE_TRAILER_WITH_ID = 201;
    public static final int CODE_REVIEW = 300;
    public static final int CODE_REVIEW_WITH_ID = 301;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private FavoritesDbHelper mOpenHelper;

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(CONTENT_AUTHORITY, PATH_MOVIE, CODE_MOVIE);
        matcher.addURI(CONTENT_AUTHORITY, PATH_MOVIE + "/#", CODE_MOVIE_WITH_ID);
        matcher.addURI(CONTENT_AUTHORITY, PATH_TRAILER, CODE_TRAILER);
        matcher.addURI(CONTENT_AUTHORITY, PATH_TRAILER + "/#", CODE_TRAILER_WITH_ID);
        matcher.addURI(CONTENT_AUTHORITY, PATH_REVIEW, CODE_REVIEW);
        matcher.addURI(CONTENT_AUTHORITY, PATH_REVIEW + "/#", CODE_REVIEW_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new FavoritesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIE:
                cursor = db.query(MoviesEntry.MOVIE_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);
                break;
            case CODE_MOVIE_WITH_ID:
                cursor = db.query(MoviesEntry.MOVIE_TABLE,
                        projection,
                        selection,
                        null,
                        null,
                        null,
                        null);
                break;
            case CODE_TRAILER:

                break;
            case CODE_TRAILER_WITH_ID:

                break;
            case CODE_REVIEW:

                break;
            case CODE_REVIEW_WITH_ID:
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri = null;
        long id;

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIE_WITH_ID:
                id = db.insert(MoviesEntry.MOVIE_TABLE, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MOVIES_CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            case CODE_TRAILER_WITH_ID:
                id = db.insert(TrailerEntry.TRAILER_TABLE, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MOVIES_CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            case CODE_REVIEW_WITH_ID:
                id = db.insert(ReviewsEntry.REVIEWS_TABLE, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MOVIES_CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numberOfDeletedFavorites;
        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIE_WITH_ID:
                numberOfDeletedFavorites = db.delete(MoviesEntry.MOVIE_TABLE,
                        selection,
                        selectionArgs);
                break;
            case CODE_TRAILER_WITH_ID:
                numberOfDeletedFavorites = db.delete(TrailerEntry.TRAILER_TABLE,
                        selection,
                        selectionArgs);
                break;
            case CODE_REVIEW_WITH_ID:
                numberOfDeletedFavorites = db.delete(ReviewsEntry.REVIEWS_TABLE,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }

        if (numberOfDeletedFavorites != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numberOfDeletedFavorites;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
