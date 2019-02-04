package ru.steeshock.goosebumpsapp.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.steeshock.goosebumpsapp.ui.FavoriteBooksFragment;
import ru.steeshock.goosebumpsapp.R;

import static android.content.ContentValues.TAG;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksDescription;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksNames;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.imagesCollection;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    private final List<Book> mBook = new ArrayList<>();

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull final ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_list_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookHolder holder, final int position) {
        final Book book = mBook.get(position);
        holder.bind(book);

        holder.mAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.startCheckAnimation();

                if(!FavoriteBooksFragment.mBookAdapter.mBook.contains(book))
                    FavoriteBooksFragment.mBookAdapter.addFavoriteBook(book);
                else FavoriteBooksFragment.mBookAdapter.removeFavoriteBook(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBook.size();
    }

    public void createBooksStub(){
        for (int i=1;i<=11;i++){

            int lastDotIndex = booksDescription[i-1].indexOf(".", 100);
            int lastTrimIndex = booksDescription[i-1].indexOf(" ", 100);
            Log.d(TAG, "createBooksStub: " + lastDotIndex);
            if (lastDotIndex < 150)
                mBook.add(new Book(i, imagesCollection[i-1], booksNames[i-1], booksDescription[i-1].substring(0, lastDotIndex).concat("...")));
            else mBook.add(new Book(i, imagesCollection[i-1], booksNames[i-1], booksDescription[i-1].substring(0, lastTrimIndex).concat("...")));
        }

        notifyDataSetChanged();
    }

    public void addFavoriteBook(Book book){
        if (!mBook.contains(book))
            mBook.add(book);
        notifyDataSetChanged();
    }

    public void removeFavoriteBook(Book book){
        if (mBook.contains(book))
            mBook.remove(book);
        notifyDataSetChanged();
    }
}
