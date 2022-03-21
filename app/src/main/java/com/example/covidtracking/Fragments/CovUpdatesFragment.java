package com.example.covidtracking.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracking.Adapters.RecyclerViewAdapterData;
import com.example.covidtracking.Adapters.StateRecyclerViewAdapter;
import com.example.covidtracking.Controllers.CovUpdatesController;
import com.example.covidtracking.ModelClasses.CountrywiseDataModel;
import com.example.covidtracking.ModelClasses.StatewiseDataModel;
import com.example.covidtracking.R;
import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class CovUpdatesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    CountryCodePicker countryCodePicker;
    TextView mtodaytotal, mtotal, mactive, mtodayactive, mrecovered, mtodayrecovered, mdeaths, mtodaydeaths;


    Spinner spinner;
    String country;
    TextView mfilter;
    String[] types = {"Cases", "Deaths", "Recovered", "Active"};
    PieChart mpiechart;
    RecyclerView recyclerView,rcyclerview_covid_data;
    // List<CountryDataModelClass> modelClassList = new ArrayList<>();
    List<CountrywiseDataModel> modelClassList2 = new ArrayList<>();
    List<CountrywiseDataModel> countryCodeList = new ArrayList<>();
    RecyclerViewAdapterData recyclerViewAdapterData;
    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covupdates, container, false);

        initUI(view);
        recyclerViewAdapterData = new RecyclerViewAdapterData(modelClassList2,CovUpdatesFragment.this);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        callAPIRecyclerview();

        return view;
    }

    private void initUI(View view) {
        mtotal = view.findViewById(R.id.totalcase);
        mtodaytotal = view.findViewById(R.id.todaytotal);
        mactive = view.findViewById(R.id.activecase);
        mtodayactive = view.findViewById(R.id.todayactive);
        mrecovered = view.findViewById(R.id.recoveredcase);
        mtodayrecovered = view.findViewById(R.id.todayrecovered);
        mdeaths = view.findViewById(R.id.deathscase);
        mtodaydeaths = view.findViewById(R.id.todaydeaths);
        mpiechart = view.findViewById(R.id.piechart);
        spinner = view.findViewById(R.id.spinner);
        mfilter = view.findViewById(R.id.filter);
        recyclerView = view.findViewById(R.id.recyclerview);
        countryCodePicker = view.findViewById(R.id.ccp);
        rcyclerview_covid_data=view.findViewById(R.id.rcyclerview_covid_data);

    }
    private void callAPIRecyclerview() {

        CovUpdatesController statescontroller = new CovUpdatesController(CovUpdatesFragment.this);
        statescontroller.callAPI();

        CovUpdatesController controller = new CovUpdatesController(CovUpdatesFragment.this);
        controller.callAPIRecyclerviewData();

    }

    public void getResponse(ArrayList<CountrywiseDataModel> modelClassList) {

        if (!modelClassList.isEmpty()) {
            countryCodeList = modelClassList;
            setAdapter(modelClassList);
            initiateCountryCodePicker();
        }
    }

    private void initiateCountryCodePicker() {
        countryCodePicker.setAutoDetectedCountry(true);
        country = countryCodePicker.getSelectedCountryName();
        fetchData(countryCodeList, country);
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryName();
                fetchData(countryCodeList, country);
            }
        });
    }

    private void fetchData(List<CountrywiseDataModel> countryCodeList, String country) {

        if (!countryCodeList.isEmpty()) {
            for (int i = 0; i < countryCodeList.size(); i++) {
                if (countryCodeList.get(i).getCountry().equals(country)) {
                    mactive.setText((countryCodeList.get(i).getActive()));
                    mtodaydeaths.setText((countryCodeList.get(i).getTodayDeaths()));
                    mtodayrecovered.setText((countryCodeList.get(i).getTodayRecovered()));
                    mtodaytotal.setText((countryCodeList.get(i).getTodayCases()));
                    mtotal.setText((countryCodeList.get(i).getCases()));
                    mdeaths.setText((countryCodeList.get(i).getDeaths()));
                    mrecovered.setText((countryCodeList.get(i).getRecovered()));

                    int active, deaths, recovered, cases;

                    active = Integer.parseInt(countryCodeList.get(i).getActive());
                    cases = Integer.parseInt(countryCodeList.get(i).getCases());
                    recovered = Integer.parseInt(countryCodeList.get(i).getRecovered());
                    deaths = Integer.parseInt(countryCodeList.get(i).getDeaths());

                    Updategraph(cases, active, recovered, deaths);

                }
            }
        } else {
            Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
        }
    }

    private void Updategraph(int cases, int active, int recovered, int deaths) {
        mpiechart.clearChart();
        mpiechart.addPieSlice(new PieModel("Cases", cases, Color.parseColor("#FFB701")));
        mpiechart.addPieSlice(new PieModel("Active", active, Color.parseColor("#045F5F")));
        mpiechart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#17A4EB")));
        mpiechart.addPieSlice(new PieModel("Deaths", deaths, Color.parseColor("#E65319")));
        mpiechart.startAnimation();
    }

    private void setAdapter(ArrayList<CountrywiseDataModel> modelClassList) {
        recyclerViewAdapterData = new RecyclerViewAdapterData(modelClassList, CovUpdatesFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapterData);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = types[position];
        mfilter.setText(item);
        recyclerViewAdapterData.filter(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void getStatesResponse(StatewiseDataModel model) {
        if (model != null) {
            StateRecyclerViewAdapter adapterData = new StateRecyclerViewAdapter(model.getData().getRegional(), getActivity());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcyclerview_covid_data.setLayoutManager(layoutManager);
            rcyclerview_covid_data.setAdapter(adapterData);
        }
    }
}