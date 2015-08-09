package com.khoslalabs.musicplayer;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.khoslalabs.musicplayer.events.Pauseevent;
import com.khoslalabs.musicplayer.events.Playevent;
import com.khoslalabs.musicplayer.models.MusicApiResponse;
import com.khoslalabs.musicplayer.services.MusicService;

import de.greenrobot.event.EventBus;

/**
 * Created by ankitsrivastava on 05/08/15.
 */
public class MusicBar extends Activity{
    private ImageButton imageplay;
    private ImageButton imagepause;
    private Button detailsbutton;
    MusicApiResponse musicApiResponse;
    int pos;


    @Override
    protected void onStart() {
       // EventBus.getDefault().register(this);
        if(MusicService.mediaPlayer!=null && MusicService.mediaPlayer.isPlaying())
        {
            imageplay.setVisibility(View.INVISIBLE);
            imagepause.setVisibility(View.VISIBLE);
        }
        else if(MusicService.mediaPlayer!=null)
        {
            imageplay.setVisibility(View.VISIBLE);
            imagepause.setVisibility(View.INVISIBLE);
        }
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

        imagepause.setVisibility(View.INVISIBLE);

        Intent i= getIntent();
        //For Database
        final String songname= i.getStringExtra("songname");
        final String artistname= i.getStringExtra("artistname");
        final String imageurl= i.getStringExtra("imageurl");
        final String filename= i.getStringExtra("filename");


        //For API
        //musicApiResponse= (MusicApiResponse) i.getSerializableExtra("songlist");
        //pos= i.getIntExtra("position",1);




        imageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_play");
                intent.putExtra("filename", filename);
                getApplicationContext().startService(intent);
                imageplay.setVisibility(View.INVISIBLE);
                imagepause.setVisibility(View.VISIBLE);
            }
        });

        imagepause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD, "method_pause");
                intent.putExtra("filename", filename);
                getApplicationContext().startService(intent);
                imagepause.setVisibility(View.INVISIBLE);
                imageplay.setVisibility(View.VISIBLE);

            }
        });

        detailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                //For Database
                intent.putExtra("songname",songname);
                intent.putExtra("artistname",artistname);
                intent.putExtra("imagename", imageurl);
                intent.putExtra("filename", filename);

                //For API
                //intent.putExtra("songlist", musicApiResponse);
                //intent.putExtra("position", pos);
                startActivity(intent);

            }
        });

    }
/*
    public  void  onEvent(Playevent event)
    {
        Log.d("njg", "play is pressed in musicbar activity");
        // playbutton.setVisibility(View.INVISIBLE);
        //pausebutton.setVisibility(View.VISIBLE);
    }

    public  void  onEvent(Pauseevent event)
    {
        Log.d("njg", "pause is pressed in musicbar activity");
        // playbutton.setVisibility(View.INVISIBLE);
        //pausebutton.setVisibility(View.VISIBLE);
    }
*/
}