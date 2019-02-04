package ru.steeshock.goosebumpsapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int mId;

    @ColumnInfo(name = "book_image")
    private int mBookImageResource;

    @ColumnInfo(name = "book_name")
    private String mBookName;

    @ColumnInfo(name = "book_description")
    private String mBookDescription;

    @ColumnInfo(name = "book_content")
    private String mBookContent;

    @ColumnInfo(name = "book_isFavorite")
    private boolean mBookIsFavorite;

    public Book(int mId, int mBookImage, String mBookName, String mBookDescription) {
        this.mId = mId;
        this.mBookImageResource = mBookImage;
        this.mBookName = mBookName;
        this.mBookDescription = mBookDescription;
    }

    public Book(int mId, int mBookImage, String mBookName, String mBookDescription, boolean mBookIsFavorite) {
        this.mId = mId;
        this.mBookImageResource = mBookImage;
        this.mBookName = mBookName;
        this.mBookDescription = mBookDescription;
        this.mBookIsFavorite = mBookIsFavorite;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getBookName() {
        return mBookName;
    }

    public void setBookName(String mBookName) {
        this.mBookName = mBookName;
    }

    public int getBookImageResource() {
        return mBookImageResource;
    }

    public void setBookImageResource(int bookImageResource) {
        mBookImageResource = bookImageResource;
    }

    public String getBookDescription() {
        return mBookDescription;
    }

    public void setBookDescription(String bookDescription) {
        mBookDescription = bookDescription;
    }

    public boolean isBookIsFavorite() {
        return mBookIsFavorite;
    }

    public void setBookIsFavorite(boolean bookIsFavorite) {
        mBookIsFavorite = bookIsFavorite;
    }
}
