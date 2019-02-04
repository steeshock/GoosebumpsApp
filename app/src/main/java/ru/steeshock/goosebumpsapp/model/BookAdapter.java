package ru.steeshock.goosebumpsapp.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.steeshock.goosebumpsapp.R;
import ru.steeshock.goosebumpsapp.ui.BooksFragment;
import ru.steeshock.goosebumpsapp.ui.FavoriteBooksFragment;

import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksDescription;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.booksNames;
import static ru.steeshock.goosebumpsapp.model.BooksInfo.imagesCollection;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    private List<Book> mBook = new ArrayList<>();

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

        holder.mFavoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!FavoriteBooksFragment.mBookAdapter.mBook.contains(book)){
                    book.setBookIsFavorite(true);
                    holder.mFavoriteIcon.setImageResource(R.drawable.like);
                    FavoriteBooksFragment.mBookAdapter.addFavoriteBook(book);
                }
                else {
                    book.setBookIsFavorite(false);
                    holder.mFavoriteIcon.setImageResource(R.drawable.dislike_w);
                    FavoriteBooksFragment.mBookAdapter.removeFavoriteBook(book);
                    BooksFragment.mBookAdapter.notifyDataSetChanged(); //Обновляем данные в первом фрагменте после удаления из второго
                }
            }
        });

        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return mBook.size();
    }

    public void createBooksStub(){
        for (int i=1;i<=11;i++){

            // Создание коротких описаний для каждой книги
            int lastDotIndex = booksDescription[i-1].indexOf(".", 100);
            int lastTrimIndex = booksDescription[i-1].indexOf(" ", 100);

            if (lastDotIndex < 150)
                mBook.add(new Book(i, imagesCollection[i-1], booksNames[i-1], booksDescription[i-1].substring(0, lastDotIndex).concat("...")));
            else mBook.add(new Book(i, imagesCollection[i-1], booksNames[i-1], booksDescription[i-1].substring(0, lastTrimIndex).concat("...")));
        }

        notifyDataSetChanged();
    }

    public void addFavoriteBook(Book book){
        for (Book b:mBook
             ) {
            if(book.getId()==b.getId())
                return;
        }
        mBook.add(book);

        notifyDataSetChanged();
    }

    public void removeFavoriteBook(Book book){
        mBook.remove(book);
        notifyDataSetChanged();
    }

    public void clearList(){
        mBook.clear();
        notifyDataSetChanged();
    }
}
