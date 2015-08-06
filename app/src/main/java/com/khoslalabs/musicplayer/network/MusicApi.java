package com.khoslalabs.musicplayer.network;

import com.khoslalabs.musicplayer.models.MusicApiResponse;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by ankitsrivastava on 06/08/15.
 */
public class MusicApi {
    public  static  String URL= "https://www.kimonolabs.com/api";
    private static MusicInterface musicInterface;
    public  static  MusicInterface getApi(){
        if(musicInterface==null)
        {

            RestAdapter restAdapter= new RestAdapter.Builder()
                        .setEndpoint(URL)
                    .build();
            musicInterface= restAdapter.create(MusicInterface.class);
        }
        return  musicInterface;
    }


    public  interface MusicInterface{
        @GET("/45ww1px8?apikey=cJgQ9GQhvg987UM2nVFJ0sZkpfimLaLL")
        MusicApiResponse getMusicList();

        @GET("/45ww1px8?apikey=cJgQ9GQhvg987UM2nVFJ0sZkpfimLaLL")
        void getMusicList(retrofit.Callback<MusicApiResponse> musicApiResponseCallback);

    }
}
