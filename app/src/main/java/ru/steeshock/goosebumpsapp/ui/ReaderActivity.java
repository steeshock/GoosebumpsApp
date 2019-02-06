package ru.steeshock.goosebumpsapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Integer mPageNumber = 0;
    private int mId;
    private Button mButton;
    private int counter = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        if(savedInstanceState!=null)
            mPageNumber = savedInstanceState.getInt(SAVED_PAGE);

        mPDFView = findViewById(R.id.pdfView);
        mButton = findViewById(R.id.chBtn);

        mId = getIntent().getIntExtra(BOOK_ID, 0);
        readBookFromAsset(booksPaths[mId]);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePath();
            }
        });


    }

    private void changePath(){

        if (counter >= booksPaths.length)
            counter = 0;

        String newPath = booksPaths[counter];
        counter++;
        readBookFromAsset(newPath);

    }

    private void readBookFromAsset(String path){

        mPDFView.fromAsset(path)
                .defaultPage(mPageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .pageFitPolicy(FitPolicy.BOTH)
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
