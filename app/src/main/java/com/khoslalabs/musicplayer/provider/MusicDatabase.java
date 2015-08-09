package com.khoslalabs.musicplayer.provider;

import android.content.ContentValues;
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
        String MUSIC_FILENAME= "file_name";
    }

    final String CREATE_TABLE_MUSIC="create table "+Tables.MUSIC+"("
            + BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +TableMusic.MUSIC_NAME+" TEXT NOT NULL,"
            +TableMusic.MUSIC_AUTHOR+" TEXT NOT NULL,"
            +TableMusic.MUSIC_IMAGE_URL+" TEXT NOT NULL,"
            +TableMusic.MUSIC_FILENAME+" TEXT NOT NULL);";

    @DebugLog
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_MUSIC);
        ContentValues contentValues=new ContentValues();
        contentValues.put(TableMusic.MUSIC_NAME, "Selfie Le Re");
        contentValues.put(TableMusic.MUSIC_AUTHOR,"Pritam");
        contentValues.put(TableMusic.MUSIC_IMAGE_URL,"" +
                "http://dailymodernized.com/wp-content/uploads/2015/07/35.jpg");
        contentValues.put(TableMusic.MUSIC_FILENAME, "selfie");
        db.insert(Tables.MUSIC,null, contentValues);

        ContentValues contentValues1=new ContentValues();
        contentValues1.put(TableMusic.MUSIC_NAME, "Gallan Goodiyaan");
        contentValues1.put(TableMusic.MUSIC_AUTHOR,"Shankar Ehsaan Loy");
        contentValues1.put(TableMusic.MUSIC_IMAGE_URL,"" +
                "http://4.bp.blogspot.com/-63npFhusD2c/VUJfxrhvy-I/AAAAAAAAJ1w/XoF5TJLfUqM/s1600/gallangoodiyan-ddd.jpg");
        contentValues1.put(TableMusic.MUSIC_FILENAME, "gallan");
        db.insert(Tables.MUSIC,null, contentValues1);

        contentValues1.put(TableMusic.MUSIC_NAME, "Bhar do jholi");
        contentValues1.put(TableMusic.MUSIC_AUTHOR,"Adnan Sami");
        contentValues1.put(TableMusic.MUSIC_IMAGE_URL,"" +
                "http://bajrangibhaijaanmovie.in/wp-content/uploads/2015/06/bhar-do-jholi-meri-song-lyrics-mp3-video-song-free-bajrangi-bhaijaan-songs-image-1.jpg");
        contentValues1.put(TableMusic.MUSIC_FILENAME, "jholi");
        db.insert(Tables.MUSIC,null, contentValues1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
