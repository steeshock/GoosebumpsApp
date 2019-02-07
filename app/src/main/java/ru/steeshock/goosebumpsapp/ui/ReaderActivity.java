package ru.steeshock.goosebumpsapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import ru.steeshock.goosebumpsapp.R;

import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksPaths;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.BOOK_ID;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.NIGHT_MODE;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.READER_CLICKED;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.SAVED_PAGE;

public class ReaderActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener, OnPageScrollListener {

    private static final String TAG = "KEKE";
    private PDFView mPDFView;
    private Integer mPageNumber = 1;
    private ImageView mImageView;
    private ConstraintLayout mMainContent;
    private DefaultScrollHandle handle;

    private int mId;
    private boolean isNightMode = true;
    private boolean isReaderClicked = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        mId = getIntent().getIntExtra(BOOK_ID, 0);

        if(savedInstanceState!=null){
            isNightMode = savedInstanceState.getBoolean(NIGHT_MODE);
            mPageNumber = savedInstanceState.getInt(SAVED_PAGE);
        }


        mPDFView = findViewById(R.id.pdfView);
        mImageView = findViewById(R.id.ivNightMode);
        mMainContent = findViewById(R.id.main_content);
        mImageView.bringToFront();

        handle = new DefaultScrollHandle(this);
        readBookFromAsset(booksPaths[mId-1]);

        setDayOrNightModeForIcon();
        setDayOrNightBackgroundForMainContent();
        hideSystemUI();

        mPDFView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: " + handle.shown());

                if (handle.shown()){
                    isReaderClicked = false;
                    hideSystemUI();
                    hideNightModeIcon();
                } else {
                    isReaderClicked = true;
                    hideSystemUI();
                    hideNightModeIcon();
                }
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_PAGE, mPageNumber);
        outState.putBoolean(NIGHT_MODE, isNightMode);
    }

    private void readBookFromAsset(String path){

        mPDFView.fromAsset(path)
                .defaultPage(mPageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(handle)
                .onPageScroll(this)
                .onPageError(this)
                .pageFitPolicy(FitPolicy.WIDTH)
                .swipeHorizontal(true)
                .pageSnap(true)
                //.autoSpacing(true)
                .pageFling(true)
                .nightMode(isNightMode)
                .load();

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
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
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

        isNightMode = !isNightMode;
        setDayOrNightModeForIcon();
        setDayOrNightBackgroundForMainContent();
        mPDFView.setNightMode(isNightMode);
        mPDFView.loadPages();

    }

    private void setDayOrNightBackgroundForMainContent() {
        if(isNightMode){
            mMainContent.setBackgroundColor(Color.BLACK);
            mPDFView.setBackgroundColor(Color.BLACK);
        }else {
            mMainContent.setBackgroundColor(Color.WHITE);
            mPDFView.setBackgroundColor(Color.WHITE);
        }

    }

    private void setDayOrNightModeForIcon() {
        if(isNightMode)
            mImageView.setImageResource(R.drawable.night_on);
        else mImageView.setImageResource(R.drawable.night_off);
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
    public void onPageScrolled(int page, float positionOffset) {

        handle.hideDelayed();
        isReaderClicked = true;
        hideNightModeIcon();
    }
}
