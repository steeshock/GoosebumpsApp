package ru.steeshock.goosebumpsapp.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.steeshock.goosebumpsapp.R;
import ru.steeshock.goosebumpsapp.model.BookAdapter;

import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class BooksFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    public static BookAdapter mBookAdapter = new BookAdapter();//todo Избавиться от static поля
    private LinearLayoutManager mLayoutLinearManager;

    public BooksFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BooksFragment newInstance(int sectionNumber) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        Log.d(TAG, "newInstance: " + sectionNumber);
        mBookAdapter.createBooksStub();
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = rootView.findViewById(R.id.recycler);
        mLayoutLinearManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutLinearManager);
        mRecyclerView.setAdapter(mBookAdapter);

        return rootView;
    }
}