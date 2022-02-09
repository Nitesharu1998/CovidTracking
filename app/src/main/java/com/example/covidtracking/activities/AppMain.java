package com.example.covidtracking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.covidtracking.Adapters.ViewPagerAdapter;
import com.example.covidtracking.Fragments.CertsFragment;
import com.example.covidtracking.Fragments.CovUpdatesFragment;
import com.example.covidtracking.Fragments.RecentContactsFragment;
import com.example.covidtracking.Fragments.UserStatusFragment;
import com.example.covidtracking.R;
import com.example.covidtracking.Utils.Global;
import com.google.android.material.tabs.TabLayout;

public class AppMain extends AppCompatActivity {
    ViewPager base_viewpager;
    TabLayout base_tablayout;
    Activity activity;
    LinearLayout ll_status, ll_updates, ll_contacts, ll_certs;
    ImageView iv_status, iv_updates, iv_contacts, iv_certs;
    FrameLayout frm_tabframe;
    boolean isStatus = false, isUpdates = false, isContacts = false, isCerts = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        initUI();
        initListeners();

        fragmentSwitcher(0);
        /*base_tablayout.setupWithViewPager(base_viewpager);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new UserStatusFragment(),"Status");
        adapter.addFrag(new CovUpdatesFragment(),"Updates");
        adapter.addFrag(new RecentContactsFragment(),"Recent");
        adapter.addFrag(new CertsFragment(),"Certificates");

        base_viewpager.setAdapter(adapter);*/
    }

    private void initListeners() {
         ll_status.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 fragmentSwitcher(0);
             }
         });
         ll_updates.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 fragmentSwitcher(1);
             }
         });

        ll_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSwitcher(2);
            }
        });

        ll_certs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSwitcher(3);
            }
        });

    }

    private void fragmentSwitcher(int Checker) {
        if (Checker == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_tabframe, new UserStatusFragment()).commit();
            iv_status.setImageResource(R.drawable.heart_active);
            iv_updates.setImageResource(R.drawable.covgraph_inactive);
            iv_certs.setImageResource(R.drawable.cert_inactive);
            iv_contacts.setImageResource(R.drawable.recents_inactive);

            iv_status.setBackgroundResource(R.drawable.active_tab);
            iv_updates.setBackgroundResource(R.drawable.inactive_tab);
            iv_certs.setBackgroundResource(R.drawable.inactive_tab);
            iv_contacts.setBackgroundResource(R.drawable.inactive_tab);

        }
        if (Checker == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_tabframe, new CovUpdatesFragment()).commit();
            iv_status.setImageResource(R.drawable.heart_inactive);
            iv_updates.setImageResource(R.drawable.covgraph_active);
            iv_certs.setImageResource(R.drawable.cert_inactive);
            iv_contacts.setImageResource(R.drawable.recents_inactive);

            iv_status.setBackgroundResource(R.drawable.inactive_tab);
            iv_updates.setBackgroundResource(R.drawable.active_tab);
            iv_certs.setBackgroundResource(R.drawable.inactive_tab);
            iv_contacts.setBackgroundResource(R.drawable.inactive_tab);
        }
        if (Checker == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_tabframe, new RecentContactsFragment()).commit();
            iv_status.setImageResource(R.drawable.heart_inactive);
            iv_updates.setImageResource(R.drawable.covgraph_inactive);
            iv_certs.setImageResource(R.drawable.cert_inactive);
            iv_contacts.setImageResource(R.drawable.recents_active);

            iv_status.setBackgroundResource(R.drawable.inactive_tab);
            iv_updates.setBackgroundResource(R.drawable.inactive_tab);
            iv_certs.setBackgroundResource(R.drawable.inactive_tab);
            iv_contacts.setBackgroundResource(R.drawable.active_tab);
        }
        if (Checker == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_tabframe, new CertsFragment()).commit();
            iv_status.setImageResource(R.drawable.heart_inactive);
            iv_updates.setImageResource(R.drawable.covgraph_inactive);
            iv_certs.setImageResource(R.drawable.cert_active);
            iv_contacts.setImageResource(R.drawable.recents_inactive);

            iv_status.setBackgroundResource(R.drawable.inactive_tab);
            iv_updates.setBackgroundResource(R.drawable.inactive_tab);
            iv_certs.setBackgroundResource(R.drawable.active_tab);
            iv_contacts.setBackgroundResource(R.drawable.inactive_tab);
        }
    }

    private void initUI() {
        activity = this;
        ll_status = findViewById(R.id.ll_status);
        ll_updates = findViewById(R.id.ll_updates);
        ll_contacts = findViewById(R.id.ll_contacts);
        ll_certs = findViewById(R.id.ll_certs);

        iv_status = findViewById(R.id.iv_status);
        iv_updates = findViewById(R.id.iv_updates);
        iv_contacts = findViewById(R.id.iv_contacts);
        iv_certs = findViewById(R.id.iv_certs);

        frm_tabframe = findViewById(R.id.frm_tabframe);
    }
}