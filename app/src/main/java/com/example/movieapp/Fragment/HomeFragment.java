package com.example.movieapp.Fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
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
import com.example.movieapp.Adapter.BannerAdapter;
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

    View view;
    List<Movie> movieList;
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

        return view;
    }

    private void viewBanner() {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/791373/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi&page=1",
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
                Toast.makeText(view.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void setBannerMoviesPagerAdapter(List<Movie> bannerMoviesList) {
        bannerAdapter = new BannerAdapter(view.getContext(), bannerMoviesList);
        bannerViewPager.setAdapter(bannerAdapter);

    }

    private void setrefresh() {
        swipeRefreshLayout = view.findViewById(R.id.swipe_fefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}