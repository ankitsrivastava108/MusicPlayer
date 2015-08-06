package com.khoslalabs.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khoslalabs.musicplayer.provider.MusicDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by ankitsrivastava on 06/08/15.
 */
public class MusicCursor extends CursorAdapter {

    public MusicCursor(Context context, Cursor c) {
        super(context, c, false);
    }

    public class ViewHolder {
        TextView albumTextView;
        TextView artistTextView;
        TextView songTextView;
        ImageView imageView;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_music, parent, false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.albumTextView = (TextView) view.findViewById(R.id.item_music_album);
        viewHolder.artistTextView = (TextView) view.findViewById(R.id.item_music_artist);
        viewHolder.songTextView = (TextView) view.findViewById(R.id.item_music_song);
        viewHolder.imageView= (ImageView) view.findViewById(R.id.music_image);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder= (ViewHolder) view.getTag();
        String imageUrl= cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_IMAGE_URL));
        String songname= cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_NAME));
        String artistname= cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_AUTHOR));

        viewHolder.albumTextView.setText(artistname);
        Picasso
                .with(context)
                .load(imageUrl)
                .error(R.drawable.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.songTextView.setText(songname);
    }
}
