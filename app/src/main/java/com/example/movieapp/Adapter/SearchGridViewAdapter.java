package com.example.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Activity.MovieDetailActivity;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.R;

import java.util.List;

public class SearchGridViewAdapter extends BaseAdapter {
    Context context;
    List<Movie> movieList;

    public SearchGridViewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.movie_horizontal_item, null);
        ImageView moviePosster= convertView.findViewById(R.id.moviePoster);
        Glide.with(context).load(movieList.get(position).getBackdrop()).into(moviePosster);
        TextView movieTitle = convertView.findViewById(R.id.movieTitle);
        movieTitle.setText(movieList.get(position).getTitle());

        moviePosster.setOnClickListener(new View.OnClickListener() {
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
        return convertView;
    }
}
