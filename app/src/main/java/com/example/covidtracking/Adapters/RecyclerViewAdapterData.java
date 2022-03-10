package com.example.covidtracking.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracking.Fragments.CovUpdatesFragment;
import com.example.covidtracking.ModelClasses.CountrywiseDataModel;
import com.example.covidtracking.R;

import java.text.NumberFormat;
import java.util.List;

public class RecyclerViewAdapterData extends RecyclerView.Adapter<RecyclerViewAdapterData.ViewHolder> {

    int caseTypes = 1;
    List<CountrywiseDataModel> modelClassList;
    Context context;

    public RecyclerViewAdapterData(List<CountrywiseDataModel> modelClassList, CovUpdatesFragment fragment) {
        this.modelClassList = modelClassList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CountrywiseDataModel countrywiseDataModel = modelClassList.get(position);
        if (caseTypes == 1) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(countrywiseDataModel.getCases())));
        } else if (caseTypes == 2) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(countrywiseDataModel.getRecovered())));
        } else if (caseTypes == 3) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(countrywiseDataModel.getDeaths())));
        } else {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(countrywiseDataModel.getActive())));
        }

        holder.country.setText(countrywiseDataModel.getCountry());

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cases, country;

        public ViewHolder(View itemView) {
            super(itemView);

            cases = itemView.findViewById(R.id.countrycase);
            country = itemView.findViewById(R.id.countryname);
        }
    }


    public void filter(String item) {
        if (item.equals("Cases")) {
            caseTypes = 1;
        } else if (item.equals("Recovered")) {
            caseTypes = 2;
        } else if (item.equals("Deaths")) {
            caseTypes = 3;
        } else {
            caseTypes = 4;
        }

        notifyDataSetChanged();
    }
}
