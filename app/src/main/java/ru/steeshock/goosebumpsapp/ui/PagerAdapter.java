package ru.steeshock.goosebumpsapp.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ru.steeshock.goosebumpsapp.ui.BooksFragment;
import ru.steeshock.goosebumpsapp.ui.FavoriteBooksFragment;

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
                fragment = FavoriteBooksFragment.newInstance(position);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}