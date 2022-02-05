package com.example.covidtracking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.covidtracking.Adapters.ViewPagerAdapter;
import com.example.covidtracking.Fragments.CertsFragment;
import com.example.covidtracking.Fragments.CovUpdatesFragment;
import com.example.covidtracking.Fragments.RecentContactsFragment;
import com.example.covidtracking.Fragments.UserStatusFragment;
import com.example.covidtracking.R;
import com.google.android.material.tabs.TabLayout;

public class AppMain extends AppCompatActivity {
    ViewPager base_viewpager;
    TabLayout base_tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        base_viewpager=findViewById(R.id.base_viewpager);
        base_tablayout=findViewById(R.id.base_tablayout);

        base_tablayout.setupWithViewPager(base_viewpager);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new UserStatusFragment(),"Status");
        adapter.addFrag(new CovUpdatesFragment(),"Updates");
        adapter.addFrag(new RecentContactsFragment(),"Recent");
        adapter.addFrag(new CertsFragment(),"Certificates");

        base_viewpager.setAdapter(adapter);
    }
}