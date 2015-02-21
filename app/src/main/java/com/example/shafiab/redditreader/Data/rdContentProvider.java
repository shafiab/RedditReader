package com.example.shafiab.redditreader.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.shafiab.redditreader.Data.redditContract.redditEntry;


/**
 * Created by shafiab on 9/20/14.
 */
public class rdContentProvider extends ContentProvider {
    private static final int TITLE = 100;
    private static final int URL = 200;
    private static final int TITLE_AND_URL = 300;
    private static final int TITLE_AND_BITMAPURL = 400;
    private static final int TITLE_AND_BITMAP = 500;

    dbFunction mDbHelper;

    // URI matcher
    public static UriMatcher URI_MATCHER = buildUriMatcher();


    public static UriMatcher buildUriMatcher()
    {
        final String authority = redditContract.AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, redditEntry.TABLE_NAME + "/*/*", TITLE_AND_URL);
        matcher.addURI(authority, redditEntry.TABLE_NAME + "/*", TITLE);
        matcher.addURI(authority, redditEntry.TABLE_NAME + "/*/*", TITLE_AND_URL);
        return matcher;


    }


    @Override
    public boolean onCreate() {
        mDbHelper = new dbFunction(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor retCursor = null;
        switch (URI_MATCHER.match(uri))
        {
            case TITLE_AND_URL:
                retCursor = mDbHelper.getReadableDatabase().query(
                        redditEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                break;

        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        return redditEntry.CONTENT_TYPE;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri returnUri;

        long _id = db.insert(redditEntry.TABLE_NAME, null, contentValues);
        if ( _id > 0 )
            returnUri = redditEntry.buildRedditUri(_id);
        else
            throw new android.database.SQLException("Failed to insert row into " + uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int numRecordsRemoved = 0;

        numRecordsRemoved = db.delete(
                redditEntry.TABLE_NAME, selection, selectionArgs);
        if (numRecordsRemoved != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRecordsRemoved;
    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final int match = URI_MATCHER.match(uri);
        int rowsUpdated;

        rowsUpdated = db.update(redditEntry.TABLE_NAME, contentValues, selection,
                selectionArgs);
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
