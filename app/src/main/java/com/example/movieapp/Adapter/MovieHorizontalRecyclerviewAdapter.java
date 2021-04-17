package com.example.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Activity.MovieDetailActivity;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.R;

import java.util.List;

public class MovieHorizontalRecyclerviewAdapter extends RecyclerView.Adapter<MovieHorizontalRecyclerviewAdapter.MainViewHoldel> {
    Context context;
    List<Movie> movieList;

    public MovieHorizontalRecyclerviewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MainViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHoldel(LayoutInflater.from(context).inflate(R.layout.movie_horizontal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHoldel holder, int position) {
        Glide.with(context).load(movieList.get(position).getPoster()).into(holder.moviePoster);
        holder.movieTitle.setText(movieList.get(position).getTitle());

        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truyền id sang MovieDetailActivity để restAPI
                Intent intent = null ;
                intent= new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id",movieList.get(position).getId());
                intent.putExtra("poster",movieList.get(position).getPoster());
                intent.putExtra("backdrop",movieList.get(position).getBackdrop());
                intent.putExtra("title",movieList.get(position).getTitle());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MainViewHoldel extends RecyclerView.ViewHolder{
        ImageView moviePoster;
        TextView movieTitle;
        public MainViewHoldel(@NonNull View itemView) {
            super(itemView);
            moviePoster= itemView.findViewById(R.id.moviePoster);
            movieTitle= itemView.findViewById(R.id.movieTitle);
        }
    }
}
