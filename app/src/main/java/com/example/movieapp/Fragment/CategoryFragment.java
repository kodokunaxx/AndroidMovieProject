package com.example.movieapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapp.Activity.SearchActivity;
import com.example.movieapp.Adapter.MovieHorizontalRecyclerviewAdapter;
import com.example.movieapp.Json.Json;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    TabLayout tabLayout1, tabLayout2, tabLayout3;
    RecyclerView recyclerViewCategory1, recyclerViewCategory2, recyclerViewCategory3;
    MovieHorizontalRecyclerviewAdapter movieHorizontalRecyclerviewAdapter;
    ImageView searchImgCategory;
    Json json = new Json();
    View view;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        view = inflater.inflate(R.layout.fragment_category, container, false);
        tabLayout1 = view.findViewById(R.id.tabLayout1);
        tabLayout2 = view.findViewById(R.id.tabLayout2);
        tabLayout3 = view.findViewById(R.id.tabLayout3);
        recyclerViewCategory1 = view.findViewById(R.id.recyclerViewCategory1);
        recyclerViewCategory2 = view.findViewById(R.id.recyclerViewCategory2);
        recyclerViewCategory3 = view.findViewById(R.id.recyclerViewCategory3);
        searchImgCategory = view.findViewById(R.id.searchImgCategory);

        viewMovieCategory(109418);
        viewMovieCategory(19995);
        viewMovieCategory(635302);
        setTabLayout1();
        setTabLayout2();
        setTabLayout3();
        searchImgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void setTabLayout1(){
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewMovieCategory(109418);
                        break;
                    case 1:
                        viewMovieCategory(259693);
                        break;
                    case 2:
                        viewMovieCategory(290859);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setTabLayout2(){
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewMovieCategory(19995);
                        break;
                    case 1:
                        viewMovieCategory(297708);
                        break;
                    case 2:
                        viewMovieCategory(22971);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setTabLayout3(){
        tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewMovieCategory(635302);
                        break;
                    case 1:
                        viewMovieCategory(8587);
                        break;
                    case 2:
                        viewMovieCategory(12536);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void viewMovieCategory(int idMovie){
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+ idMovie +"/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Movie> movieList = new ArrayList<>();
                            movieList = json.JsonMovie(response);

                            if(idMovie == 109418 || idMovie == 259693 || idMovie == 290859){
                                setMovieHorizontalRecyclerviewAdapter1(movieList);
                            }else if(idMovie == 19995 || idMovie == 297708 || idMovie == 22971){
                                setMovieHorizontalRecyclerviewAdapter2(movieList);
                            }else setMovieHorizontalRecyclerviewAdapter3(movieList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);

    }

    public void setMovieHorizontalRecyclerviewAdapter1(List<Movie> movieList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewCategory1.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(),movieList);
        recyclerViewCategory1.setAdapter(movieHorizontalRecyclerviewAdapter);
    }
    public void setMovieHorizontalRecyclerviewAdapter2(List<Movie> movieList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewCategory2.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(),movieList);
        recyclerViewCategory2.setAdapter(movieHorizontalRecyclerviewAdapter);
    }
    public void setMovieHorizontalRecyclerviewAdapter3(List<Movie> movieList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewCategory3.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(),movieList);
        recyclerViewCategory3.setAdapter(movieHorizontalRecyclerviewAdapter);
    }
}