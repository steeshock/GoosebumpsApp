package ru.steeshock.goosebumpsapp.ui;

import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import ru.steeshock.goosebumpsapp.R;
import ru.steeshock.goosebumpsapp.utils.UserSettings;

import static ru.steeshock.goosebumpsapp.utils.UserSettings.FAVORITE_BOOKS_KEY;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KEKE";
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    public static UserSettings mUserSettings;
    private Set<String> favorites = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mUserSettings = new UserSettings(this);
        Log.d(TAG, "onCreate: " + mUserSettings.mSharedPreferences.getInt(FAVORITE_BOOKS_KEY, -1));
        //Log.d(TAG, "onCreate: " + mUserSettings.mSharedPreferences.getStringSet(FAVORITE_BOOKS_KEY, favorites));

    }
}
