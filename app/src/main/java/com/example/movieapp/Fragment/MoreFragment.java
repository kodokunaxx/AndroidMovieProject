package com.example.movieapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.movieapp.Activity.HistorySearchActivity;
import com.example.movieapp.Activity.HistoryViewActivity;
import com.example.movieapp.Activity.SplashScreenActivity;
import com.example.movieapp.Activity.UserActivity;
import com.example.movieapp.R;

public class MoreFragment extends Fragment {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    SharedPreferences sharedpreferences;
    String name, email;
    TextView nameUsertv, gmailUsertv, txtHistoryView, txtHistorySearch, txtLogOut;
    Button btnEditUsertv;
    View view;
    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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
        view = inflater.inflate(R.layout.fragment_more, container, false);
        sharedpreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        nameUsertv = view.findViewById(R.id.nameUsertv);
        gmailUsertv = view.findViewById(R.id.gmailUsertv);
        txtHistoryView = view.findViewById(R.id.txtHistoryView);
        txtHistorySearch = view.findViewById(R.id.txtHistorySearch);
        txtLogOut = view.findViewById(R.id.txtLogOut);
        btnEditUsertv = view.findViewById(R.id.btnEditUsertv);

        name = sharedpreferences.getString(NAME, "Đỗ Mạnh Hà");
        email = sharedpreferences.getString(EMAIL, "hapro123456@gmail.com");

        nameUsertv.setText(name);
        gmailUsertv.setText(email);
        setRltHistoryViewOnClick();
        setRltHistorySearchOnClick();
        setEditUserOnClick();
        setRltLogOutOnClick();

        return view;
    }

    public void setEditUserOnClick(){
        btnEditUsertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setRltHistorySearchOnClick(){
        txtHistorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), HistorySearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setRltHistoryViewOnClick(){
        txtHistoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHistoryView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(view.getContext(), HistoryViewActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void setRltLogOutOnClick(){
        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(view.getContext(), SplashScreenActivity.class);
                startActivity(intent);
            }
        });
    }

}