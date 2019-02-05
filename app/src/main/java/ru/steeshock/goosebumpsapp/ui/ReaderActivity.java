package ru.steeshock.goosebumpsapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.steeshock.goosebumpsapp.R;

import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksDescription;
import static ru.steeshock.goosebumpsapp.utils.UserSettings.BOOK_ID;

public class ReaderActivity extends AppCompatActivity {

    private int id;
    private TextView mReader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        mReader = findViewById(R.id.tvReader);
        id = getIntent().getIntExtra(BOOK_ID, 0);

        mReader.setText(booksDescription[id-1]);
    }
}
