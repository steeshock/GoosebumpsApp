package ru.steeshock.goosebumpsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.steeshock.goosebumpsapp.model.BookAdapter;

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
    private BookAdapter mBookAdapter;
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
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mBookAdapter = new BookAdapter();
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mLayoutLinearManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutLinearManager);
        mRecyclerView.setAdapter(mBookAdapter);

        return rootView;
    }
}