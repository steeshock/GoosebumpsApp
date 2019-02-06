package ru.steeshock.goosebumpsapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.steeshock.goosebumpsapp.R;

import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksPaths;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.BOOK_ID;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.SAVED_PAGE;

public class ReaderActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {

    private static final String TAG = "KEKE";
    private PDFView mPDFView;
    private Integer mPageNumber = 1;
    private ImageView mImageView;
    private LinearLayout mMainContent;

    private int mId;
    private static boolean nightMode = true;
    private static boolean isReaderClicked = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        if(savedInstanceState!=null)
            mPageNumber = savedInstanceState.getInt(SAVED_PAGE);

        mPDFView = findViewById(R.id.pdfView);
        mImageView = findViewById(R.id.ivNightMode);
        mMainContent = findViewById(R.id.main_content);

        mId = getIntent().getIntExtra(BOOK_ID, 0);
        readBookFromAsset(booksPaths[mId-1]);

        setDayOrNightModeForIcon();
        hideSystemUI();

        mPDFView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSystemUI();
                hideNightModeIcon();
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode();
            }
        });
    }

    private void hideNightModeIcon() {
        if (isReaderClicked){
            isReaderClicked = false;
            mImageView.setVisibility(View.INVISIBLE);
        } else {
            isReaderClicked = true;
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void changeMode(){

        /*if (counter >= booksPaths.length)
            counter = 0;

        String newPath = booksPaths[counter];
        counter++;
        readBookFromAsset(newPath);*/

        nightMode = !nightMode;
        setDayOrNightModeForIcon();
        setDayOrNightBackgroundForMainContent();
        mPDFView.setNightMode(nightMode);
        mPDFView.loadPages();

    }

    private void setDayOrNightBackgroundForMainContent() {
        if(nightMode){
            mMainContent.setBackgroundColor(Color.BLACK);
            mPDFView.setBackgroundColor(Color.BLACK);
        }else {
            mMainContent.setBackgroundColor(Color.WHITE);
            mPDFView.setBackgroundColor(Color.WHITE);
        }

    }

    private void setDayOrNightModeForIcon() {
        if(nightMode)
            mImageView.setImageResource(R.drawable.night_on);
        else mImageView.setImageResource(R.drawable.night_off);
    }

    private void readBookFromAsset(String path){

        mPDFView.fromAsset(path)
                .defaultPage(mPageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                //.spacing(10) // in dp
                .onPageError(this)
                .pageFitPolicy(FitPolicy.WIDTH)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .nightMode(nightMode)
                .load();

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        mPageNumber = page;
        //setTitle(String.format("%s %s / %s", getTitle(), page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = mPDFView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());
    }

    @Override
    public void onPageError(int page, Throwable t){
        Log.e(TAG, "Cannot load page " + page);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_PAGE, mPageNumber);
    }
}
