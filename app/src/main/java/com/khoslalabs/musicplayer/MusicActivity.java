package com.khoslalabs.musicplayer;

import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.logging.Handler;


public class MusicActivity extends ActionBarActivity {

    private Button playbutton;
    private Button pausebutton;
    private MediaPlayer mediaPlayer;
    private Button playfast;
    private Button playback;
    private SeekBar seekBar;


    MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        playbutton= (Button) findViewById(R.id.activity_main_play);
        pausebutton= (Button) findViewById(R.id.activity_main_pause);
        mediaPlayer= MediaPlayer.create(this,R.raw.a);
        playfast= (Button) findViewById(R.id.activity_main_fastforward);
        playback= (Button) findViewById(R.id.activity_main_rewind);
        seekBar = (SeekBar) findViewById(R.id.activity_main_seekbar);

        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });

        playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
            }
        });

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MusicActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
            }
        });

        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MusicActivity.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Toast.makeText(MusicActivity.this, "Song is Complete", Toast.LENGTH_SHORT).show();
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

    public static int MESSAGE_WAKE_UP_AND_SEEK=10;

    class MusicHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {

                if (mediaPlayer != null) {
                    if(mediaPlayer.isPlaying()) {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                    }
                }
            }

            super.handleMessage(msg);
        }
    }


}
