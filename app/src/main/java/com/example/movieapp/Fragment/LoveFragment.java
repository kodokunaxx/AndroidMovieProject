package com.example.movieapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.movieapp.Activity.SearchActivity;
import com.example.movieapp.Adapter.SearchGridViewAdapter;
import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.User;
import com.example.movieapp.R;

import java.util.List;

public class LoveFragment extends Fragment {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_ID = "user_id";
    SharedPreferences sharedpreferences;
    View view;
    User user = new User();
    GridView gridViewLove;
    SearchGridViewAdapter searchGridViewAdapter;
    DBMangager dbMangager;
    ImageView searchImgLove;

    public LoveFragment() {
        // Required empty public constructor
    }

    public static LoveFragment newInstance(String param1, String param2) {
        LoveFragment fragment = new LoveFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_love, container, false);
        gridViewLove = view.findViewById(R.id.gridViewLove);
        searchImgLove = view.findViewById(R.id.searchImgLove);
        sharedpreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        dbMangager = new DBMangager(getActivity());
        setMovieListGridView();

        searchImgLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void setMovieListGridView(){
        int idUser = sharedpreferences.getInt(USER_ID, 0);
        List<Movie> movieList = dbMangager.getLoveMovie(idUser);
        searchGridViewAdapter = new SearchGridViewAdapter(view.getContext(), movieList);
        gridViewLove.setAdapter(searchGridViewAdapter);
    }


}