package com.khoslalabs.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.DrawableContainer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.khoslalabs.musicplayer.models.Collection1;
import com.khoslalabs.musicplayer.models.Music;
import com.khoslalabs.musicplayer.models.MusicApiResponse;
import com.khoslalabs.musicplayer.models.Songname;
import com.khoslalabs.musicplayer.network.MusicApi;
import com.khoslalabs.musicplayer.provider.MusicDatabase;
import com.khoslalabs.musicplayer.services.MusicService;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import hugo.weaving.DebugLog;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class FirstFragment extends Fragment {
    private ListView listView;
    private List<Collection1> musicList = new ArrayList<Collection1>();
    private MusicAdapter musicAdapter;
    MusicCursor musicCursorAdaptor;
    MusicDatabase musicDbHelper;
    SQLiteDatabase musicDb;
    MusicApiResponse m;

    @Override @DebugLog
    public void onResume() {
        super.onResume();
    }

    @Override @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override @DebugLog
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first);
        musicDbHelper=new MusicDatabase(getActivity());

        //For Database
        musicDb=musicDbHelper.getReadableDatabase();
        final Cursor cursor=musicDb.query(MusicDatabase.Tables.MUSIC,null,null,null,null,null,null);
        musicCursorAdaptor=new MusicCursor(getActivity(),cursor);
        listView.setAdapter(musicCursorAdaptor);


       //new MusicAsynctask().execute();
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Intent intent= new Intent(getActivity(), MusicActivity.class);
               Intent intent= new Intent(getActivity(), MusicBar.class);

               //For API
               //intent.putExtra("songlist",m);
               //intent.putExtra("position",position);

               //For Database
               if(MusicService.mediaPlayer!=null)
               {
                   MusicService.mediaPlayer.stop();
                   MusicService.mediaPlayer= null;
               }
               cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_NAME);
               intent.putExtra("songname","Song Name: " + cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_NAME)));
               intent.putExtra("artistname", "Artist Name: " + cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_AUTHOR)));
               intent.putExtra("imageurl", cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_IMAGE_URL)));
               intent.putExtra("filename", cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_FILENAME)));
               startActivity(intent);
           }
       });

//For API
  /*      MusicApi.getApi().getMusicList(new retrofit.Callback<MusicApiResponse>() {
            @Override
            public void success(MusicApiResponse musicApiResponse, Response response) {
                musicAdapter = new MusicAdapter(getActivity(), musicApiResponse.getResults().getCollection1());
                listView.setAdapter(musicAdapter);
                m= musicApiResponse;
                Toast.makeText(getActivity(), "Number Of Entries" + musicApiResponse.getResults(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });*/

        return view;

}

/*
class  MusicAsynctask extends AsyncTask<String, Integer, MusicApiResponse>{

    @Override
    protected MusicApiResponse doInBackground(String... params) {
        return  MusicApi.getApi().getMusicList();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(MusicApiResponse musicApiResponse) {
        super.onPostExecute(musicApiResponse);
        musicAdapter= new MusicAdapter(getActivity(), musicApiResponse.getResults().getCollection1());
        listView.setAdapter(musicAdapter);
        Toast.makeText(getActivity(), "Number Of Entries" + musicApiResponse.getResults(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}

    @Override @DebugLog
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }*/
}
