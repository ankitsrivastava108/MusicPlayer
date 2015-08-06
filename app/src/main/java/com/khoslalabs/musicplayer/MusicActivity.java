package com.khoslalabs.musicplayer;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.khoslalabs.musicplayer.events.Duration;
import com.khoslalabs.musicplayer.events.Playevent;
import com.khoslalabs.musicplayer.events.SeekbarEvent;
import com.khoslalabs.musicplayer.services.MusicService;

import java.util.logging.Handler;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;


public class MusicActivity extends ActionBarActivity {

    public static int MESSAGE_WAKE_UP_AND_SEEK = 10;


    private ImageButton playbutton;
    private ImageButton pausebutton;
    //private MediaPlayer mediaPlayer;
    private ImageButton playfast;
    private ImageButton playback;
    private SeekBar seekBar;
    private  int currentpos;
    private  int duration;
    String TAG;

    MusicHandler musicHandler = new MusicHandler();

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MusicService.mediaPlayer!=null)
        {
            seekBar.setMax(MusicService.mediaPlayer.getDuration());
            musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
        }
        EventBus.getDefault().register(this);
        pausebutton.setVisibility(View.INVISIBLE);
        Log.d(TAG, "On start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "On stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "On restart");
    }

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        playbutton = (ImageButton) findViewById(R.id.activity_main_play);
        pausebutton = (ImageButton) findViewById(R.id.activity_main_pause);
        //mediaPlayer= MediaPlayer.create(this,R.raw.a);
        playfast = (ImageButton) findViewById(R.id.activity_main_fastforward);
        playback = (ImageButton) findViewById(R.id.activity_main_rewind);
        seekBar = (SeekBar) findViewById(R.id.activity_main_seekbar);
        //mediaPlayer.start();


        //Intent i= getIntent();
        //String songname= i.getStringExtra("songname");
        //String artistname= i.getStringExtra("artistname");
/*
        TextView songtext= (TextView) findViewById(R.id.main_songName);
        songtext.setText(songname);
        TextView artisttext= (TextView) findViewById(R.id.main_artistName);
        artisttext.setText(artistname);
*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    MusicService.mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


        /*Intent intent = new Intent(getApplicationContext(), MusicService.class);
        intent.putExtra(MusicService.KEY_METHOD, "method_play");
        startService(intent);
        playbutton.setVisibility(View.INVISIBLE);
        pausebutton.setVisibility(View.VISIBLE);
*/

        playfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_ff");
                getApplicationContext().startService(intent);
                //mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });

        playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_rw");
                getApplicationContext().startService(intent);
                //mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
            }
        });

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_play");
                startService(intent);
                playbutton.setVisibility(View.INVISIBLE);
                pausebutton.setVisibility(View.VISIBLE);
/*
                Toast.makeText(MusicActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();*/

            }
        });

        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MusicActivity.this, MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_pause");
                startService(intent);
                playbutton.setVisibility(View.VISIBLE);
                pausebutton.setVisibility(View.INVISIBLE);
               /* Toast.makeText(MusicActivity.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();*/
                //musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






/*
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MusicActivity.this, "Song is Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

    @DebugLog
    public void onEvent(SeekbarEvent event){
        currentpos= event.pos;
        musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);

    }

    public void onEvent(Duration event){
        duration= event.duration;
        seekBar.setMax(duration);
        musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);

    }

    public  void  onEvent(Playevent event)
    {
        playbutton.setVisibility(View.INVISIBLE);
        pausebutton.setVisibility(View.VISIBLE);
    }

    class MusicHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {

                if (MusicService.mediaPlayer != null) {

                    seekBar.setProgress(MusicService.getPosition());
                    sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                }

            }

            super.handleMessage(msg);
        }
    }


      /*  class MusicHandler extends android.os.Handler {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {
                    if (MusicService.mediaPlayer != null) {
                        if (MusicService.mediaPlayer.isPlaying()) {
                            seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition());
                            sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                        }
                    }
                }
                super.handleMessage(msg);
            }
        }*/


}
