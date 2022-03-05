package com.example.covidtracking.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracking.Adapters.RecyclerViewAdapterData;
import com.example.covidtracking.Controllers.CovUpdatesController;
import com.example.covidtracking.ModelClasses.StatewiseDataModel;
import com.example.covidtracking.R;

public class CovUpdatesFragment extends Fragment {
    TextView tv_active_case, tv_recovered_case, tv_deceased_case, tv_confirmed_case;
    RecyclerView rcyclerview_covid_data;
    RecyclerViewAdapterData adapterData;
    CovUpdatesController controller;
    Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covupdates, container, false);

        tv_active_case = view.findViewById(R.id.tv_active_case);
        tv_recovered_case = view.findViewById(R.id.tv_recovered_case);
        tv_deceased_case = view.findViewById(R.id.tv_deceased_case);
        tv_confirmed_case = view.findViewById(R.id.tv_confirmed_case);
        rcyclerview_covid_data = view.findViewById(R.id.rcyclerview_covid_data);
        mActivity = getActivity();
        controller = new CovUpdatesController(CovUpdatesFragment.this, mActivity);
        controller.callAPI();
        return view;
    }

    public void getResponse(StatewiseDataModel model) {
        if (model != null) {
            adapterData = new RecyclerViewAdapterData(model.getData().getRegional(), mActivity);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
            rcyclerview_covid_data.setLayoutManager(layoutManager);
            rcyclerview_covid_data.setAdapter(adapterData);
        }
    }
}