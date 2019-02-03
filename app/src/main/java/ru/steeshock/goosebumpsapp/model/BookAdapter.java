package ru.steeshock.goosebumpsapp.model;

import android.arch.persistence.room.util.StringUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.steeshock.goosebumpsapp.R;

import static android.content.ContentValues.TAG;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksDescription;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksNames;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.imagesCollection;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    private final List<Book> mBook = new ArrayList<>();

    public BookAdapter() {
        this.createBooksStub();
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_list_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, final int position) {
        Book book = mBook.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return mBook.size();
    }

    public void createBooksStub(){
        for (int i=1;i<=11;i++){

            int lastTrimIndex = booksDescription[i-1].indexOf(" ", 50);
            Log.d(TAG, "createBooksStub: " + lastTrimIndex);
            mBook.add(new Book(i, imagesCollection[i-1], booksNames[i-1], booksDescription[i-1].substring(0, lastTrimIndex).concat("...")));
        }


        notifyDataSetChanged();
    }
}
