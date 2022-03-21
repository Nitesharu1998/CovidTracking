package com.example.covidtracking.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracking.ModelClasses.StatewiseDataModel;
import com.example.covidtracking.R;

import java.util.List;

public class StateRecyclerViewAdapter extends RecyclerView.Adapter<StateRecyclerViewAdapter.ViewHolder> {

    private List<StatewiseDataModel.DataDTO.RegionalDTO> regionalDTOS;
    private Activity context;

    public StateRecyclerViewAdapter(List<StatewiseDataModel.DataDTO.RegionalDTO> regionalDTOS, Activity context) {
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

        holder.ll_statercl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStateCasesDialog(regionalDTO);
            }
        });

    }

    private void openStateCasesDialog(StatewiseDataModel.DataDTO.RegionalDTO regionalDTO) {
        TextView tv_confirmed_case, tv_deceased_case,tv_recovered_case,tv_active_case,tv_state;
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.statedetails_popup);


        tv_confirmed_case=dialog.findViewById(R.id.tv_confirmed_case);
        tv_deceased_case=dialog.findViewById(R.id.tv_deceased_case);
        tv_recovered_case=dialog.findViewById(R.id.tv_recovered_case);
        tv_active_case=dialog.findViewById(R.id.tv_active_case);
        tv_state=dialog.findViewById(R.id.tv_state);

        tv_state.setText(regionalDTO.getLoc()+"");
        tv_confirmed_case.setText(regionalDTO.getTotalConfirmed()+"");
        tv_deceased_case.setText(regionalDTO.getDeaths()+"");
        tv_recovered_case.setText(regionalDTO.getDischarged()+"");
        tv_active_case.setText(regionalDTO.getConfirmedCasesIndian()+"");

        dialog.show();
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
        LinearLayout ll_statercl_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_statercl_main = itemView.findViewById(R.id.ll_statercl_main);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_active = itemView.findViewById(R.id.tv_active);
            tv_recovered = itemView.findViewById(R.id.tv_recovered);
            tv_deceased = itemView.findViewById(R.id.tv_deceased);
            tv_confirmed = itemView.findViewById(R.id.tv_confirmed);


        }
    }
}
