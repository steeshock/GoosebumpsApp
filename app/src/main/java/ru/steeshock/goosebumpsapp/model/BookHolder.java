package ru.steeshock.goosebumpsapp.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.steeshock.goosebumpsapp.R;

public class BookHolder extends RecyclerView.ViewHolder {

    public ImageView mBookImage;
    public TextView mBookName;
    public TextView mBookDescription;

    public BookHolder(@NonNull View itemView) {
        super(itemView);

        mBookImage = itemView.findViewById(R.id.ivBookImage);
        mBookName = itemView.findViewById(R.id.tvBookName);
        mBookDescription = itemView.findViewById(R.id.tvBookDescription);
    }

    public void bind(Book book) {
        mBookImage.setImageResource(book.getBookImageResource());
        mBookName.setText(book.getBookName());
        mBookDescription.setText(book.getBookDescription());
    }
}
