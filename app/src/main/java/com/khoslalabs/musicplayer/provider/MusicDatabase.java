package com.khoslalabs.musicplayer.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import hugo.weaving.DebugLog;

/**
 * Created by ankitsrivastava on 06/08/15.
 */
public class MusicDatabase extends SQLiteOpenHelper{
    public static int VERSION=1;
    public static String DATABASE_NAME="musicdb";
    public MusicDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public interface Tables{
        String MUSIC="music";

    }

    public interface TableMusic{
        String MUSIC_NAME="music_name";
        String MUSIC_AUTHOR="music_author";
        String MUSIC_IMAGE_URL="image_url";
    }

    final String CREATE_TABLE_MUSIC="create table "+Tables.MUSIC+"("
            + BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +TableMusic.MUSIC_NAME+" TEXT NOT NULL,"
            +TableMusic.MUSIC_AUTHOR+" TEXT NOT NULL,"
            +TableMusic.MUSIC_IMAGE_URL+" TEXT NOT NULL);";

    @DebugLog
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_MUSIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
