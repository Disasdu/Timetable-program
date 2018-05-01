package com.example.adilbekmailanov.myapplication.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.adilbekmailanov.myapplication.PageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adilbekmailanov on 23.02.2018.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {

    private final List<PageModel> fragmentList = new ArrayList<>();

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addNewPage(PageModel newPage) {
        fragmentList.add(newPage);
    }
}
