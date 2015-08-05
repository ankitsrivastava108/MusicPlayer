package com.khoslalabs.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.khoslalabs.musicplayer.R;
import com.khoslalabs.musicplayer.events.Duration;
import com.khoslalabs.musicplayer.events.SeekbarEvent;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;

/**
 * Created by ankitsrivastava on 05/08/15.
 */
public class MusicService extends Service {

    public static MediaPlayer mediaPlayer;
    public static final String KEY_METHOD = "method";
    private static final String METHOD_PLAY = "method_play";
    private static final String METHOD_PAUSE = "method_pause";
    private static final String METHOD_FF = "method_ff";
    private static final String METHOD_RW = "method_rw";

    String TAG = "hi";


    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.a);
        Duration duration= new Duration();
        duration.duration= mediaPlayer.getDuration();


        EventBus.getDefault().post(duration);
        super.onCreate();
    }

    @DebugLog
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String method = intent.getStringExtra(KEY_METHOD);


        if (method.equals(METHOD_PLAY)) {

            Toast.makeText(MusicService.this, "Play is clicked", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
            int pos= mediaPlayer.getCurrentPosition();
            SeekbarEvent event= new SeekbarEvent();
            event.mssg= "pause";
            event.pos= pos;
            EventBus.getDefault().post(event);
            Log.d(TAG, "play in service");

        }


        if (method.equals(METHOD_PAUSE)) {
            Toast.makeText(MusicService.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
            mediaPlayer.pause();
            int pos= mediaPlayer.getCurrentPosition();
            SeekbarEvent event= new SeekbarEvent();
            event.mssg= "play";
            event.pos= pos;
            EventBus.getDefault().post(event);
            Log.d(TAG, "pause in service");

        }
        if (method.equals(METHOD_FF)) {
            Toast.makeText(MusicService.this, "FF is clicked", Toast.LENGTH_SHORT).show();
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
            /*int pos= mediaPlayer.getCurrentPosition();
            SeekbarEvent event= new SeekbarEvent();
            event.pos= pos;
            EventBus.getDefault().post(event);*/
            Log.d(TAG, "fast forward in service");

        }
        if (method.equals(METHOD_RW)) {
            Toast.makeText(MusicService.this, "RW is clicked", Toast.LENGTH_SHORT).show();
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
            /*int pos= mediaPlayer.getCurrentPosition();
            SeekbarEvent event= new SeekbarEvent();
            event.pos= pos;
            EventBus.getDefault().post(event);*/
            Log.d(TAG, "rewind in service");
        }



        return super.onStartCommand(intent, flags, startId);
    }

    public static int getPosition(){
        return mediaPlayer.getCurrentPosition();
    }


    @DebugLog
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
