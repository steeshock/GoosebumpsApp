package ru.steeshock.goosebumpsapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int mId;

    @ColumnInfo(name = "book_number")
    private String mBookNumber;

    @ColumnInfo(name = "book_name")
    private String mBookName;

    @ColumnInfo(name = "book_content")
    private String mBookContent;

    public Book(int mId, String mBookName, String mBookContent) {
        this.mId = mId;
        this.mBookName = mBookName;
        this.mBookContent = mBookContent;
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

    public String getBookContent() {
        return mBookContent;
    }

    public void setBookContent(String mBookContent) {
        this.mBookContent = mBookContent;
    }
}
