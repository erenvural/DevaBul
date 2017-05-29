package com.patho.messenger.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.patho.messenger.R;
import com.patho.messenger.activities.DiseaseSuggestActivity;

import java.util.ArrayList;


public class TimelineFragment extends Fragment implements View.OnClickListener{

    View view;
    ArrayList<String> lastValues = new ArrayList<>();


    public TimelineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline, container, false);
        Button upButton = (Button) view.findViewById(R.id.bt_displayDiseaseSugg);
        upButton.setOnClickListener(this);
        return view;


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onClick(View v) {
        Intent diseaseSugg = new Intent(view.getContext(),DiseaseSuggestActivity.class);
        startActivity(diseaseSugg);
    }

}