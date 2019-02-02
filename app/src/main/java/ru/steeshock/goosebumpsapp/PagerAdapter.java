package ru.steeshock.goosebumpsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = BooksFragment.newInstance(position);
                break;
            case 1:
                fragment = BooksFragment.newInstance(position);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}