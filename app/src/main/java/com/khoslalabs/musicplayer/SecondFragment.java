package com.khoslalabs.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.khoslalabs.musicplayer.models.Music;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class SecondFragment extends Fragment {

    private GridView gridView;
    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;

    @Override @DebugLog
    public void onResume() {
        super.onResume();
    }

    @Override @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("SongName0", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName0", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName1", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
        musicList.add(new Music("SongName2", "Album", "Artist", "http://i.telegraph.co.uk/multimedia/archive/02973/schweinsteiger_2973771b.jpg"));
    }

    @Nullable
    @Override @DebugLog
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        gridView = (GridView) view.findViewById(R.id.gridview);
        musicAdapter = new MusicAdapter(getActivity(), musicList);
        gridView.setAdapter(musicAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity(), MusicBar.class);
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
