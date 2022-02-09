package com.example.covidtracking.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidtracking.Adapters.HelplineAdapter;
import com.example.covidtracking.ModelClasses.HelplineModel;
import com.example.covidtracking.R;

import java.util.ArrayList;

public class UserStatusFragment extends Fragment {
    RecyclerView rcl_numbers;
    ArrayList<Integer> mainIcons =new ArrayList();
    ArrayList<String> helplineTexts =new ArrayList();
    ArrayList<String> helplineNumbers =new ArrayList();
    ArrayList<HelplineModel.HelplineData> helplineData= new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_userstatus, container, false);
        initUI(root);
        fillArraylists();
        setAdapter();
        return root;
    }

    private void setAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcl_numbers.setLayoutManager(manager);
        HelplineAdapter adapter=new HelplineAdapter(getContext(),helplineData);
        rcl_numbers.setAdapter(adapter);
    }

    private void fillArraylists() {
        mainIcons.add(R.drawable.ic_support);
        mainIcons.add(R.drawable.ic_childsupport);
        mainIcons.add(R.drawable.ic_mentalhealth);
        mainIcons.add(R.drawable.ic_seniorcitizen);
        mainIcons.add(R.drawable.ic_ayurveda);
        mainIcons.add(R.drawable.ic_whatsapp);

        helplineTexts.add("Health Ministry Helpline");
        helplineTexts.add("Child Helpline");
        helplineTexts.add("Mental Health Helpline");
        helplineTexts.add("Senior Citizen Helpline");
        helplineTexts.add("Ayush Covid-19 Counselling Helpline");
        helplineTexts.add("MyGov WhatsApp Helpdesk");

        helplineNumbers.add("1075");
        helplineNumbers.add("1098");
        helplineNumbers.add("080046110007");
        helplineNumbers.add("14567");
        helplineNumbers.add("14443");
        helplineNumbers.add("9013151515");

        for (int i = 0; i < mainIcons.size(); i++) {
            HelplineModel.HelplineData testdata=new HelplineModel.HelplineData();
            testdata.setImage(mainIcons.get(i));
            testdata.setMaintext(helplineTexts.get(i));
            testdata.setNumber(helplineNumbers.get(i));

            helplineData.add(testdata);
        }
    }

    private void initUI(View root) {
        rcl_numbers=root.findViewById(R.id.rcl_numbers);

    }
}