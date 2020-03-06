package com.popseven.livecricketscore.Adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragList;

    public ScreenSlidePagerAdapter(ArrayList<Fragment> fragList, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.fragList = fragList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
