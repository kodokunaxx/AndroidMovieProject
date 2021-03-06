package com.example.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.print.PageRange;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.movieapp.Activity.MovieDetailActivity;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.MovieDetail;
import com.example.movieapp.R;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    Context context;
    List<Movie> movieList;

    public BannerAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movieList = movies;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View )object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_movie_layout, null);
        ImageView bannerImage= view.findViewById(R.id.Banner_image);
        Glide.with(context).load(movieList.get(position).getBackdrop()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truyền id sang MovieDetailActivity để restAPI
                Intent intent = null ;
                    intent= new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("id",movieList.get(position).getId());
                    intent.putExtra("backdrop", movieList.get(position).getBackdrop());

                context.startActivity(intent);

            }
        });

        return view;
    }

}
