package com.example.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Activity.MovieDetailActivity;
import com.example.movieapp.Activity.SearchActivity;
import com.example.movieapp.Model.HistorySearch;
import com.example.movieapp.R;

import java.util.List;

public class ListViewSearchAdapter extends BaseAdapter {

    Context context;
    List<HistorySearch> historySearchList;

    public ListViewSearchAdapter(Context context, List<HistorySearch> historySearchList) {
        this.context = context;
        this.historySearchList = historySearchList;
    }

    @Override
    public int getCount() {
        return historySearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return historySearchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
        TextView txtHistoryItem = convertView.findViewById(R.id.txtHistoryItem);
        txtHistoryItem.setText(historySearchList.get(position).getKeyword());

        txtHistoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null ;
                intent= new Intent(context, SearchActivity.class);

                context.startActivity(intent);

            }
        });
        return convertView;
    }
}
