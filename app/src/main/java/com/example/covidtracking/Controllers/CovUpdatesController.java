package com.example.covidtracking.Controllers;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.covidtracking.APIHandlers.APIClient;
import com.example.covidtracking.APIHandlers.APIInterface;
import com.example.covidtracking.Adapters.RecyclerViewAdapterData;
import com.example.covidtracking.Fragments.CovUpdatesFragment;
import com.example.covidtracking.ModelClasses.StatewiseDataModel;
import com.example.covidtracking.Utils.Global;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovUpdatesController {
    CovUpdatesFragment fragment;
    Activity mActivity;
    ProgressDialog dialog;

    public CovUpdatesController(CovUpdatesFragment covUpdatesFragment, Activity mActivity) {
        this.fragment = covUpdatesFragment;
        this.mActivity = mActivity;
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
                    fragment.getResponse(model);
                }
            }
            @Override
            public void onFailure(Call<StatewiseDataModel> call, Throwable t) {
                dialog.dismiss();
                Global.showToast(mActivity,"Something went wrong",0);
            }
        });

    }
}
