package com.example.karimdaher.crypto.services;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class CurrencyFragmentAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mfragmentTitleList = new ArrayList<>();

    public CurrencyFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    private void addFragment(Fragment frag, String title){
        mfragmentList.add(frag);
        mfragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}
