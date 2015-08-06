package com.khoslalabs.musicplayer;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.khoslalabs.musicplayer.provider.MusicDatabase;

import hugo.weaving.DebugLog;

/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class ListofMusic extends FragmentActivity {

    private ViewPager viewPager;
    private final int numberofpages= 2;
    private MusicListFragmentStatePagerAdapter musicListFragmentStatePagerAdapter;
    MusicDatabase musicDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Override @DebugLog
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager= (ViewPager) findViewById(R.id.activity_viewpager);
        musicListFragmentStatePagerAdapter= new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(musicListFragmentStatePagerAdapter);
    }
    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override @DebugLog
        public android.support.v4.app.Fragment getItem(int i) {
            switch(i){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
            }
           return null;
        }

        @Override @DebugLog
        public int getCount() {
            return numberofpages;

        }
    }


}
