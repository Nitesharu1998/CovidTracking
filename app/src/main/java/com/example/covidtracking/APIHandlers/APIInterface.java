package com.example.covidtracking.APIHandlers;

import com.example.covidtracking.ModelClasses.CountrywiseDataModel;
import com.example.covidtracking.ModelClasses.StatewiseDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("covid-19/countries")
    Call <ArrayList<CountrywiseDataModel>> getCountryData();

    @GET("stats/latest")
    Call<StatewiseDataModel> getCovidData();

}
