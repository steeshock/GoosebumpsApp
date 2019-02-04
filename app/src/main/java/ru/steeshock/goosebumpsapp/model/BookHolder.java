package ru.steeshock.goosebumpsapp.model;

import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import ru.steeshock.goosebumpsapp.R;

public class BookHolder extends RecyclerView.ViewHolder {

    public ImageView mBookImage;
    public TextView mBookName;
    public TextView mBookDescription;
    public TextView mBookNumber;
    public LottieAnimationView mAnimationView;

    public BookHolder(@NonNull final View itemView) {
        super(itemView);

        mBookImage = itemView.findViewById(R.id.ivBookImage);
        mBookName = itemView.findViewById(R.id.tvBookName);
        mBookDescription = itemView.findViewById(R.id.tvBookDescription);
        mBookNumber = itemView.findViewById(R.id.tvBookNumber);
        mAnimationView = itemView.findViewById(R.id.lottieAnimationView);
    }

    public void bind(Book book) {
        mBookImage.setImageResource(book.getBookImageResource());
        mBookName.setText(book.getBookName());
        mBookDescription.setText(book.getBookDescription());
        mBookNumber.setText("" + book.getId());
    }

    public void startCheckAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimationView.setProgress((Float) valueAnimator.getAnimatedValue());
            }
        });

        if (mAnimationView.getProgress() == 0f) {
            animator.start();
        } else {
            mAnimationView.setProgress(0f);
        }
    }
}
