package com.example.covidtracking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracking.ModelClasses.HelplineModel;
import com.example.covidtracking.R;

import java.util.ArrayList;

public class HelplineAdapter extends RecyclerView.Adapter<HelplineAdapter.ViewHolder> {
    Context context;
    ArrayList<HelplineModel.HelplineData> helplineData=new ArrayList<>();
    public HelplineAdapter(Context context, ArrayList<HelplineModel.HelplineData> helplineData) {
        this.helplineData=helplineData;
        this.context=context;
    }

    @NonNull
    @Override
    public HelplineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.helpline_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HelplineAdapter.ViewHolder holder, int position) {
            holder.iv_mainicon.setImageResource(helplineData.get(position).getImage());
            holder.tv_supportname.setText(helplineData.get(position).getMaintext());
            holder.tv_textsupport_no.setText(helplineData.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return helplineData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_mainicon;
        TextView tv_supportname,tv_textsupport_no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_mainicon= itemView.findViewById(R.id.iv_mainicon);
            tv_supportname= itemView.findViewById(R.id.tv_supportname);
            tv_textsupport_no= itemView.findViewById(R.id.tv_textsupport_no);
        }
    }
}
