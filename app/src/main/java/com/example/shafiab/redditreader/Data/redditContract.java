package com.example.shafiab.redditreader.Data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by shafiab on 9/20/14.
 */
public class redditContract {
    public static final String AUTHORITY = "com.example.shafiab.testproject";
    public static final Uri BASE_CONTENT_URI =
            Uri.parse("content://"+AUTHORITY);

    public static final String MAIN_TABLE = "main";


    public static final class redditEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, MAIN_TABLE);
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + AUTHORITY + "/" + MAIN_TABLE;
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + AUTHORITY + "/" + MAIN_TABLE;

        public static final String TABLE_NAME = "redditDb";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BITMAP_URL = "bmURL";
        public static final String COLUMN_BITMAP = "bMap";
        public static Uri buildRedditUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
