package com.khoslalabs.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.khoslalabs.musicplayer.R;
import com.khoslalabs.musicplayer.events.Duration;
import com.khoslalabs.musicplayer.events.Pauseevent;
import com.khoslalabs.musicplayer.events.Playevent;
import com.khoslalabs.musicplayer.events.SeekbarEvent;

import java.io.IOException;
import java.net.URI;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;

/**
 * Created by ankitsrivastava on 05/08/15.
 */
public class MusicService extends Service {

    public static MediaPlayer mediaPlayer= null;
    public static final String KEY_METHOD = "method";
    private static final String METHOD_PLAY = "method_play";
    private static final String METHOD_PAUSE = "method_pause";
    private static final String METHOD_FF = "method_ff";
    private static final String METHOD_RW = "method_rw";

    String TAG = "hi";

@DebugLog
    @Override
    public void onCreate() {



        super.onCreate();
    }

    @DebugLog
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(mediaPlayer==null) {
            String str = intent.getStringExtra("songPath");
            //ForDatabase
            /*switch (str){
                case "jholi" : mediaPlayer = MediaPlayer.create(this, R.raw.jholi);
                    break;
                case "gallan" : mediaPlayer = MediaPlayer.create(this, R.raw.gallan);
                    break;
                case "selfie" : mediaPlayer = MediaPlayer.create(this, R.raw.selfie);
            }*/

            if(str!="") {
                Uri u = Uri.parse(str);
                mediaPlayer= MediaPlayer.create(getApplicationContext(), u);
                Log.d("path is not null", str);


                mediaPlayer.start();
            }
            else
            {

            }
            Duration duration = new Duration();
            duration.duration = mediaPlayer.getDuration();
            EventBus.getDefault().post(duration);
        }
        String method = intent.getStringExtra(KEY_METHOD);

        if (method.equals(METHOD_PLAY)) {

            mediaPlayer.start();

            Log.d(TAG, "play in service");

        }

        if (method.equals(METHOD_PAUSE)) {

            mediaPlayer.pause();

            Log.d(TAG, "pause in service");

        }
        if (method.equals(METHOD_FF)) {

            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
            Log.d(TAG, "fast forward in service");

        }
        if (method.equals(METHOD_RW)) {

            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
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