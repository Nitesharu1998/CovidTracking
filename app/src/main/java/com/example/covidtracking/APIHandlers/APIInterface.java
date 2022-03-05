package com.example.covidtracking.APIHandlers;

import com.example.covidtracking.ModelClasses.StatewiseDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("stats/latest")
    Call<StatewiseDataModel> getCovidData();
}
