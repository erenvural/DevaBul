package com.patho.messenger.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.patho.messenger.R;
import com.patho.messenger.activities.DiseaseActivity;
import com.patho.messenger.model.Disease;

import java.util.ArrayList;

public class ForumFragment extends ListFragment implements AdapterView.OnItemClickListener {

    String[] diseaseType_spinner_items = new String[Disease.diseaseList.size()+1];
    ArrayList<String> sampleDiseaseNames = new ArrayList<>();
    String[] mDiseaseTypes=null;


    public ForumFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDiseaseTypes = getResources().getStringArray(R.array.diseasefilter);
        diseaseType_spinner_items[0]="Tüm Hastalık Türleri";
        for (int i=0;i< Disease.diseaseList.size();i++){
            diseaseType_spinner_items[i+1]=Disease.diseaseList.get(i).getDiseaseType().toString();
            sampleDiseaseNames.add(Disease.diseaseList.get(i).getDiseaseName().toString().concat(", "+
                    Disease.diseaseList.get(i).getDiseaseType().toString()));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArrayAdapter diseaseAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, sampleDiseaseNames);
        setListAdapter(diseaseAdapter);
        getListView().setOnItemClickListener(this);

        Spinner s = (Spinner) getView().findViewById(R.id.spinner_disease_in_DA);
        ArrayAdapter typeAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, mDiseaseTypes);
        s.setAdapter(typeAdapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                diseaseAdapter.clear();
                ArrayList<String> filterDiseaseNames = new ArrayList<>();
                for (int i=0;i< Disease.diseaseList.size();i++){
                    filterDiseaseNames.add(Disease.diseaseList.get(i).getDiseaseName().toString().concat(", "+
                            Disease.diseaseList.get(i).getDiseaseType().toString()));
                }
                if (parentView.getItemAtPosition(position).toString()=="Tüm Hastalık Türleri"){
                    for (int i=0;i<Disease.diseaseList.size();i++){
                            diseaseAdapter.add(filterDiseaseNames.get(i).toString());
                    }
                }
                else{
                    String filter_keyword=parentView.getItemAtPosition(position).toString();
                    for (int i=0;i<Disease.diseaseList.size();i++){
                        if (filterDiseaseNames.get(i).toString().contains(filter_keyword)){
                            diseaseAdapter.add(filterDiseaseNames.get(i).toString());
                        }
                    }
                diseaseAdapter.notifyDataSetChanged();
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Intent diseaseActivity = new Intent(view.getContext(),DiseaseActivity.class);
        String chosenDiseaseName = (String) parent.getItemAtPosition(position);
        diseaseActivity.putExtra("diseaseName",chosenDiseaseName);
        startActivity(diseaseActivity);
    }
}