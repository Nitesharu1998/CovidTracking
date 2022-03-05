package com.example.covidtracking.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracking.ModelClasses.StatewiseDataModel;
import com.example.covidtracking.R;

import java.util.List;

public class RecyclerViewAdapterData extends RecyclerView.Adapter<RecyclerViewAdapterData.ViewHolder> {

    private List<StatewiseDataModel.DataDTO.RegionalDTO> regionalDTOS;
    private Activity context;

    public RecyclerViewAdapterData(List<StatewiseDataModel.DataDTO.RegionalDTO> regionalDTOS, Activity context) {
        this.regionalDTOS = regionalDTOS;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.statedetails_list, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StatewiseDataModel.DataDTO.RegionalDTO regionalDTO = regionalDTOS.get(position);
        holder.tv_state.setText(regionalDTO.getLoc() + "");
        holder.tv_recovered.setText(regionalDTO.getDischarged() + "");
        holder.tv_deceased.setText(regionalDTO.getDeaths() + "");
        holder.tv_confirmed.setText(regionalDTO.getTotalConfirmed() + "");

        int activeData = regionalDTO.getTotalConfirmed() - regionalDTO.getDischarged() - regionalDTO.getDeaths();
        holder.tv_active.setText(activeData + "");
    }

    @Override
    public int getItemCount() {
        return regionalDTOS.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_state, tv_active, tv_recovered, txt_recovered, tv_deceased, txt_deceased, tv_confirmed, txt_confirmed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_active = itemView.findViewById(R.id.tv_active);
            tv_recovered = itemView.findViewById(R.id.tv_recovered);

            tv_deceased = itemView.findViewById(R.id.tv_deceased);

            tv_confirmed = itemView.findViewById(R.id.tv_confirmed);


        }
    }
}
