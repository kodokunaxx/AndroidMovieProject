package com.example.movieapp.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapp.Activity.SearchActivity;
import com.example.movieapp.Adapter.BannerAdapter;
import com.example.movieapp.Adapter.MovieHorizontalRecyclerviewAdapter;
import com.example.movieapp.Json.Json;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    BannerAdapter bannerAdapter;
    ViewPager bannerViewPager;
    NestedScrollView nestedScrollView;
    ImageView searchImg, notification;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerview1, recyclerview2, recyclerview3, recyclerview4;
    MovieHorizontalRecyclerviewAdapter movieHorizontalRecyclerviewAdapter;

    View view;
    Json json = new Json();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        bannerViewPager =view.findViewById(R.id.bannerViewPager);
        nestedScrollView =view.findViewById(R.id.nestedScroll);
        searchImg =view.findViewById(R.id.searchImg);
        notification =view.findViewById(R.id.notification);
        this.setrefresh();
        viewBanner();
        for (int i = 0; i<4; i++){
            viewMovieOnRecycle(i);
        }
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void viewBanner() {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/412656/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Movie> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JsonMovie(response);

                            setBannerMoviesPagerAdapter(movieDetailList);


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

    private void viewMovieOnRecycle(int temp) {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String categoryArr[] = {"now_playing", "upcoming", "top_rated", "popular"};

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+ categoryArr[temp] +"?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Movie> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JsonMovie(response);

                            switch (temp){
                                case 0:
                                    setMovieListHorizontalRecyclerviewAdapter1(movieDetailList);
                                    break;
                                case 1:
                                    setMovieListHorizontalRecyclerviewAdapter2(movieDetailList);
                                    break;
                                case 2:
                                    setMovieListHorizontalRecyclerviewAdapter3(movieDetailList);
                                    break;
                                case 3:
                                    setMovieListHorizontalRecyclerviewAdapter4(movieDetailList);
                                    break;
                            }


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

    private void setBannerMoviesPagerAdapter(List<Movie> bannerMoviesList) {
        bannerAdapter = new BannerAdapter(view.getContext(), bannerMoviesList);
        bannerViewPager.setAdapter(bannerAdapter);

    }

    private void setMovieListHorizontalRecyclerviewAdapter1(List<Movie> movieListHorizontal) {
        recyclerview1 = view.findViewById(R.id.recyclerview1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerview1.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(), movieListHorizontal);
        recyclerview1.setAdapter(movieHorizontalRecyclerviewAdapter);
    }

    private void setMovieListHorizontalRecyclerviewAdapter2(List<Movie> movieListHorizontal) {
        recyclerview2 = view.findViewById(R.id.recyclerview2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerview2.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(), movieListHorizontal);
        recyclerview2.setAdapter(movieHorizontalRecyclerviewAdapter);
    }

    private void setMovieListHorizontalRecyclerviewAdapter3(List<Movie> movieListHorizontal) {
        recyclerview3 = view.findViewById(R.id.recyclerview3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerview3.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(), movieListHorizontal);
        recyclerview3.setAdapter(movieHorizontalRecyclerviewAdapter);
    }

    private void setMovieListHorizontalRecyclerviewAdapter4(List<Movie> movieListHorizontal) {
        recyclerview4 = view.findViewById(R.id.recyclerview4);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerview4.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(view.getContext(), movieListHorizontal);
        recyclerview4.setAdapter(movieHorizontalRecyclerviewAdapter);
    }

    private void setrefresh() {
        swipeRefreshLayout = view.findViewById(R.id.swipe_fefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewBanner();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}