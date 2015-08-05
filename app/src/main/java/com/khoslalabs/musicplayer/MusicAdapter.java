package com.khoslalabs.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.khoslalabs.musicplayer.models.Music;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;


/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class MusicAdapter extends BaseAdapter  {
        String TAG= "efredg";
        WeakReference<Context> contextWeakReference;
        List<Music> musicList;

        public MusicAdapter(Context context, List<Music> musicList) {
            this.contextWeakReference = new WeakReference<Context>(context);
            this.musicList = musicList;
        }

        @Override
        public int getCount() {
            return musicList.size();
        }

        @Override
        public Music getItem(int position) {
            return musicList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



    private class ViewHolder {
            TextView albumTextView;
            TextView artistTextView;
            TextView songTextView;
            ImageView imageView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            ViewHolder viewHolder = null;

            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(contextWeakReference.get());
                view = layoutInflater.inflate(R.layout.item_music, parent, false);

                viewHolder = new ViewHolder();

                viewHolder.albumTextView = (TextView) view.findViewById(R.id.item_music_album);
                viewHolder.artistTextView = (TextView) view.findViewById(R.id.item_music_artist);
                viewHolder.songTextView = (TextView) view.findViewById(R.id.item_music_song);
                viewHolder.imageView= (ImageView) view.findViewById(R.id.music_image);
                view.setTag(viewHolder);
            }

            if (viewHolder == null) {
                viewHolder = (ViewHolder) view.getTag();
            }

            Music music = getItem(position);
            Picasso.with(contextWeakReference.get()).load(music.getUrl()).error(R.drawable.ic_launcher).into(viewHolder.imageView);
            Log.d(TAG, music.getUrl());
            viewHolder.albumTextView.setText(music.getAlbumName());
            viewHolder.artistTextView.setText(music.getArtistName());
            viewHolder.songTextView.setText(music.getSongName());

            return view;
        }



    }

