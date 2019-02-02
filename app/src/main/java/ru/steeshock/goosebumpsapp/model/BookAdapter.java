package ru.steeshock.goosebumpsapp.model;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.steeshock.goosebumpsapp.R;

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

        holder.mBookNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Удалить?")
                        .setMessage("Книга #" + mBook.get(position).getId()+  " будет безвозвратно удалена. Вы уверены?")
                        .setCancelable(false)
                        .setNegativeButton("ДА",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mBook.remove(position);
                                        notifyDataSetChanged();
                                    }
                                })
                        .setPositiveButton("НЕТ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBook.size();
    }

    public void createBooksStub(){
        for (int i=1;i<=35;i++)
            mBook.add(new Book(i, "Goosebumps", "TestContent"));

        notifyDataSetChanged();
    }
}
