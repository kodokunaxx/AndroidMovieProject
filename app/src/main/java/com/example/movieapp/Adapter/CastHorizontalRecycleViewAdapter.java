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
import com.example.movieapp.Activity.CastDetailActivity;
import com.example.movieapp.Activity.MovieDetailActivity;
import com.example.movieapp.Model.Cast;
import com.example.movieapp.R;

import java.util.List;

public class CastHorizontalRecycleViewAdapter extends RecyclerView.Adapter<CastHorizontalRecycleViewAdapter.MainViewHoldel>{
    Context context;
    List<Cast> castList;

    public CastHorizontalRecycleViewAdapter(Context context, List<Cast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public MainViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CastHorizontalRecycleViewAdapter.MainViewHoldel(LayoutInflater.from(context).inflate(R.layout.movie_cast_itime,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHoldel holder, int position) {
        Glide.with(context).load(castList.get(position).getProfieImg()).into(holder.castProfile);
        holder.castName.setText(castList.get(position).getName());

        holder.castProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truyền id sang MovieDetailActivity để restAPI
                Intent intent = null ;
                intent= new Intent(context, CastDetailActivity.class);
                intent.putExtra("id",castList.get(position).getId());
                intent.putExtra("profileImg",castList.get(position).getProfieImg());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class MainViewHoldel extends RecyclerView.ViewHolder {
        ImageView castProfile;
        TextView castName;
        public MainViewHoldel(@NonNull View itemView) {
            super(itemView);
            castProfile= itemView.findViewById(R.id.castProfile);
            castName= itemView.findViewById(R.id.castName);
        }
    }
}
