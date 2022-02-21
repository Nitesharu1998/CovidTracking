package com.example.covidtracking.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.covidtracking.Adapters.HelplineAdapter;
import com.example.covidtracking.ModelClasses.HelplineModel;
import com.example.covidtracking.R;
import com.example.covidtracking.activities.AssessmentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class UserStatusFragment extends Fragment {
    RecyclerView rcl_numbers;
    ArrayList<Integer> mainIcons = new ArrayList();
    ArrayList<String> helplineTexts = new ArrayList();
    ArrayList<String> helplineNumbers = new ArrayList();
    ArrayList<HelplineModel.HelplineData> helplineData = new ArrayList<>();

    View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_userstatus, container, false);
        initUI(root);
        fillArraylists();
        setAdapter();
        return root;
    }

    private void openAssessmentPopup(View root) {
        BottomSheetDialog bottomDialog = new BottomSheetDialog(root.getContext());
        bottomDialog.setContentView(R.layout.take_assessment);
        bottomDialog.setCancelable(false);
        getActivity().overridePendingTransition(android.R.style.Widget_DeviceDefault_Light_PopupWindow, android.R.id.accessibilityActionScrollDown);

        Button btn_proceed;
        TextView tv_skip;

        btn_proceed = bottomDialog.findViewById(R.id.btn_proceed);
        tv_skip = bottomDialog.findViewById(R.id.tv_skip);
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AssessmentActivity.class));
                bottomDialog.dismiss();
            }
        });
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });


        bottomDialog.show();
    }

    private void setAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcl_numbers.setLayoutManager(manager);
        HelplineAdapter adapter = new HelplineAdapter(getContext(), helplineData);
        rcl_numbers.setAdapter(adapter);

        openAssessmentPopup(root);
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
            HelplineModel.HelplineData testdata = new HelplineModel.HelplineData();
            testdata.setImage(mainIcons.get(i));
            testdata.setMaintext(helplineTexts.get(i));
            testdata.setNumber(helplineNumbers.get(i));

            helplineData.add(testdata);
        }
    }

    private void initUI(View root) {
        rcl_numbers = root.findViewById(R.id.rcl_numbers);

    }
}