package ru.steeshock.goosebumpsapp.model;

import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import ru.steeshock.goosebumpsapp.R;

import static android.content.ContentValues.TAG;

public class BookHolder extends RecyclerView.ViewHolder {

    public ImageView mBookImage;
    public TextView mBookName;
    public TextView mBookDescription;
    public TextView mBookNumber;
    public ImageView mFavoriteIcon;

    public BookHolder(@NonNull final View itemView) {
        super(itemView);

        mBookImage = itemView.findViewById(R.id.ivBookImage);
        mBookName = itemView.findViewById(R.id.tvBookName);
        mBookDescription = itemView.findViewById(R.id.tvBookDescription);
        mBookNumber = itemView.findViewById(R.id.tvBookNumber);
        mFavoriteIcon = itemView.findViewById(R.id.ivFavorite);
    }

    public void bind(Book book) {
        mBookImage.setImageResource(book.getBookImageResource());
        mBookName.setText(book.getBookName());
        mBookDescription.setText(book.getBookDescription());
        mBookNumber.setText("" + book.getId());

        if(book.isBookIsFavorite()){
            mFavoriteIcon.setImageResource(R.drawable.like);
            Log.d(TAG, "bind: " + book.getBookDescription());
        } else mFavoriteIcon.setImageResource(R.drawable.dislike_r);
    }
}
