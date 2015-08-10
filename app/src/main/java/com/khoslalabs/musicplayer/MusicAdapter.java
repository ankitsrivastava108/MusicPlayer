package com.khoslalabs.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.khoslalabs.musicplayer.models.Collection1;
import com.khoslalabs.musicplayer.models.Music;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;


/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class MusicAdapter extends BaseAdapter  {
    String TAG= "efredg";
    WeakReference<Context> contextWeakReference;
    // List<Collection1> musicList;
    ArrayList<HashMap<String, String>> songsListData;

    public MusicAdapter(Context context, ArrayList<HashMap<String, String>> songsListData) {
        this.contextWeakReference = new WeakReference<Context>(context);
        this.songsListData = songsListData;
    }

    @Override
    public int getCount() {
        return songsListData.size();
    }

    @Override
    public  HashMap<String, String> getItem(int position) {
        return songsListData.get(position);

    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    public static Bitmap decodeSampledBitmapFromResource(File file, int resId,
                                                         int reqWidth, int reqHeight) throws FileNotFoundException {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(file), null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(new FileInputStream(file), null, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
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
/*
            Music music = getItem(position);
            Picasso.with(contextWeakReference.get()).load(music.getUrl()).error(R.drawable.ic_launcher).into(viewHolder.imageView);
            Log.d(TAG, music.getUrl());
            viewHolder.albumTextView.setText(music.getAlbumName());
            viewHolder.artistTextView.setText(music.getArtistName());
            viewHolder.songText
            View.setText(music.getSongName());

  */

        //Collection1 collection1 = getItem(position);

        //viewHolder.albumTextView.setText(collection1.getArtistname().getText());




        Log.d("fv", "items are rendering");

        HashMap<String, String> map= getItem(position);
        String filename= map.get("songPath");

        MediaMetadataRetriever metaRetriver;
        byte[] art;
        metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(filename);



        try {
            art = metaRetriver.getEmbeddedPicture();
            File file= new File(filename);
            Bitmap songImage = BitmapFactory
                    .decodeByteArray(art, 0, art.length);
            if(songImage!=null)
            {
                viewHolder.imageView.setImageBitmap(songImage);
                //viewHolder.albumTextView.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
                //viewHolder.artistTextView.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
                String title= metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                if(title!=null) {
                    viewHolder.songTextView.setText(title);
                }
                else
                {
                    viewHolder.songTextView.setText(map.get("songTitle"));
                }
                //genre.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));
            }
            else{
                Picasso
                        .with(contextWeakReference.get())
                        .load(R.drawable.musicicon)
                        .error(R.drawable.ic_launcher)
                        .into(viewHolder.imageView);
                String title= metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                if(title!=null) {
                    viewHolder.songTextView.setText(title);
                }
                else
                {
                    viewHolder.songTextView.setText(map.get("songTitle"));
                }


                //viewHolder.albumTextView.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
                //viewHolder.artistTextView.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            }

        }
        catch (Exception e) {
            Picasso
                    .with(contextWeakReference.get())
                    .load(R.drawable.musicicon)
                    .error(R.drawable.ic_launcher)
                    .into(viewHolder.imageView);
            viewHolder.songTextView.setText(map.get("songTitle"));
        }



/*
            Picasso
                    .with(contextWeakReference.get())
                    .load(map.get("imageUrl"))
                    .error(R.drawable.ic_launcher)
                    .into(viewHolder.imageView);
            viewHolder.songTextView.setText(map.get("songTitle"));
*/

        return view;
    }



}

