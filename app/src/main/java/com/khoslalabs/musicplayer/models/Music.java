package com.khoslalabs.musicplayer.models;

/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class Music {
    private String songName;
    private String albumName;
    private String artistName;
    private String url;

    public Music(String songName, String albumName, String artistName, String url) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.url = url;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
