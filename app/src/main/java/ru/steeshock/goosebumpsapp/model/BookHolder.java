package ru.steeshock.goosebumpsapp.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.steeshock.goosebumpsapp.R;

public class BookHolder extends RecyclerView.ViewHolder {

    public TextView mBookNumber;
    public TextView mBookContent;
    public TextView mBookImage;

    public BookHolder(@NonNull View itemView) {
        super(itemView);

        mBookNumber = itemView.findViewById(R.id.tvBookId);
        mBookContent = itemView.findViewById(R.id.tvBookName);
        mBookImage = itemView.findViewById(R.id.tvBookContent);
    }

    public void bind(Book book) {
        mBookNumber.setText("" + book.getId());
        mBookContent.setText(book.getBookName());
        mBookImage.setText(book.getBookContent());
    }
}
