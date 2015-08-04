package com.khoslalabs.musicplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by ankitsrivastava on 04/08/15.
 */
public class ListofMusic extends FragmentActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager= (ViewPager) findViewById(R.id.activity_viewpager);
    }
}
