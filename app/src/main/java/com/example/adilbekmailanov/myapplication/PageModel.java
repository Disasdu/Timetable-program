package com.example.adilbekmailanov.myapplication;

import android.support.v4.app.Fragment;

/**
 * Created by adilbekmailanov on 23.02.2018.
 */

public class PageModel {
    private Fragment fragment;
    private String title;

    PageModel(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }
}
