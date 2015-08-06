package com.khoslalabs.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.DrawableContainer;
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

        musicDb=musicDbHelper.getReadableDatabase();
        Cursor cursor=musicDb.query(MusicDatabase.Tables.MUSIC,null,null,null,null,null,null);
        musicCursorAdaptor=new MusicCursor(getActivity(),cursor);
        listView.setAdapter(musicCursorAdaptor);



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /*Intent intent= new Intent(getActivity(), MusicActivity.class);
               startActivity(intent);*/
               Intent intent= new Intent(getActivity(), MusicBar.class);
              // intent.putExtra("songname",musicList.get(position).getSongname().getText());
              // intent.putExtra("artistname",musicList.get(position).getSongname().getText());
               startActivity(intent);
           }
       });

/*
        MusicApi.getApi().getMusicList(new retrofit.Callback<MusicApiResponse>() {
            @Override
            public void success(MusicApiResponse musicApiResponse, Response response) {
                musicAdapter = new MusicAdapter(getActivity(), musicApiResponse.getResults().getCollection1());
                listView.setAdapter(musicAdapter);
                Toast.makeText(getActivity(), "Number Of Entries" + musicApiResponse.getResults(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
*/
        return view;

}




    @Override @DebugLog
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
