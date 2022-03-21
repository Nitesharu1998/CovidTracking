package com.example.covidtracking.Controllers;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.covidtracking.APIHandlers.APIClient;
import com.example.covidtracking.APIHandlers.APIInterface;
import com.example.covidtracking.Fragments.CovUpdatesFragment;
import com.example.covidtracking.ModelClasses.CountrywiseDataModel;
import com.example.covidtracking.ModelClasses.StatewiseDataModel;
import com.example.covidtracking.Utils.Global;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovUpdatesController {
    CovUpdatesFragment fragment;
    Activity mActivity;
    ArrayList<CountrywiseDataModel> modelClassList = new ArrayList<>();
    ProgressDialog dialog;

    public CovUpdatesController(CovUpdatesFragment fragment) {
        this.fragment = fragment;
        this.mActivity = fragment.getActivity();
    }
    public void callAPI() {
        dialog= Global.progress(mActivity,false);
        dialog.show();
        APIInterface apiInterface = APIClient.getInstance().getRetrofitClient("https://api.rootnet.in/covid19-in/").create(APIInterface.class);
        Call<StatewiseDataModel> call=apiInterface.getCovidData();
        call.enqueue(new Callback<StatewiseDataModel>() {
            @Override
            public void onResponse(Call<StatewiseDataModel> call, Response<StatewiseDataModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()&&response.body()!=null) {
                    StatewiseDataModel model = response.body();
                    fragment.getStatesResponse(model);
                }
            }
            @Override
            public void onFailure(Call<StatewiseDataModel> call, Throwable t) {
                dialog.dismiss();
                Global.showToast(mActivity,"Something went wrong",0);
            }
        });

    }
    public void callAPIRecyclerviewData() {
        try {
            APIInterface apiInterface = APIClient.getInstance().getRetrofitClient("https://disease.sh/v3/").create(APIInterface.class);
            Call<ArrayList<CountrywiseDataModel>> listCall = apiInterface.getCountryData();
            listCall.enqueue(new Callback<ArrayList<CountrywiseDataModel>>() {
                @Override
                public void onResponse(Call<ArrayList<CountrywiseDataModel>> call, Response<ArrayList<CountrywiseDataModel>> response) {
                    if (response.body() != null && response.isSuccessful()) {

                        modelClassList = response.body();
                        fragment.getResponse(modelClassList);
                        /*modelClassList2.addAll(response.body());

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(covidDataAdapter);*/
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CountrywiseDataModel>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
