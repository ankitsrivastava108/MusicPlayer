package com.khoslalabs.musicplayer;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.khoslalabs.musicplayer.services.MusicService;

/**
 * Created by ankitsrivastava on 05/08/15.
 */
public class MusicBar extends Activity{
    private ImageButton imageplay;
    private ImageButton imagepause;
    private Button detailsbutton;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.musicbar);
        imageplay= (ImageButton) findViewById(R.id.activity_dialog_play);
        imagepause= (ImageButton) findViewById(R.id.activity_dialog_pause);
        detailsbutton= (Button) findViewById(R.id.activity_dialog_details);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -100;
        params.height = 70;
        params.width = 500;
        params.y = 750;

        this.getWindow().setAttributes(params);

        Intent i= getIntent();
        final String songname= i.getStringExtra("songname");
        final String artistname= i.getStringExtra("artistname");
        final String imageurl= i.getStringExtra("imageurl");

        imageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_play");
                getApplicationContext().startService(intent);
                imageplay.setVisibility(View.INVISIBLE);
                imagepause.setVisibility(View.VISIBLE);
            }
        });


        /*
        imageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_play");
                getApplicationContext().startService(intent);
                //mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });*/

        imagepause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_pause");
                getApplicationContext().startService(intent);
                imagepause.setVisibility(View.INVISIBLE);
                imageplay.setVisibility(View.VISIBLE);
                //mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });

        detailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                intent.putExtra("songname",songname);
                intent.putExtra("artistname",artistname);
                intent.putExtra("imagename", imageurl);
                startActivity(intent);
                //mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });

    }
}