package com.khoslalabs.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.khoslalabs.musicplayer.models.Music;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class FirstFragment extends Fragment {
    private ListView listView;
    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;

    @Override @DebugLog
    public void onResume() {
        super.onResume();
    }

    @Override @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("SongName0", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://www.funkidslive.com/wp-content/uploads/2013/09/Android-Apps-to-Download-Free-Music-Song-MP3-Downloader.jpg"));
    }

    @Nullable
    @Override @DebugLog
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first);
        musicAdapter = new MusicAdapter(getActivity(), musicList);
        listView.setAdapter(musicAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent= new Intent(getActivity(), MusicActivity.class);
               startActivity(intent);
           }
       });

        return view;

}




    @Override @DebugLog
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
