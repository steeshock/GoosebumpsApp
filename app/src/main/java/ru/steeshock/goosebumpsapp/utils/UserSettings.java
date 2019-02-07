package ru.steeshock.goosebumpsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSettings {

    public SharedPreferences mSharedPreferences;

    private static final String PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME";

    public static String FAVORITE_BOOKS_SET_KEY = "FAVORITE_BOOKS_SET_KEY";
    //public static String SAVED_PAGES_BOOK_KEY = "SAVED_PAGES_BOOK_KEY";
    public static String BOOK_ID = "BOOK_ID";
    public static String SAVED_PAGE = "SAVED_PAGE";
    public static String NIGHT_MODE = "NIGHT_MODE";
    public static String READER_CLICKED = "READER_CLICKED";

    public UserSettings(Context context){
        mSharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }
}
