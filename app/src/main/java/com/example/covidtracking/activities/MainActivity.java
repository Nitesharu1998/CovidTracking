package com.example.covidtracking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.covidtracking.R;
import com.example.covidtracking.Utils.Global;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextView tv_register;
    TextInputEditText edt_username,edt_password;
    Button btn_login;
    String str_username,str_password;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initListeners();
    }

    private void initListeners() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_password=edt_password.getText().toString().trim();
                str_username=edt_username.getText().toString().trim();
                if (credsValidations(str_username,str_password)){
                    //to the homepage // need to bring the usertype.
                    // admin and user different screens
                }
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(activity,NewUser.class));
            }
        });
    }

    private boolean credsValidations(String str_username, String str_password) {
        if (Global.isNull(str_username)){
            Global.showToast(activity,"please enter username",1);
            return false;
        }
        if(Global.isNull(str_password)){
            Global.showToast(activity,"please enter password",1);
            return false;
        }
        return true;
    }

    private void initUI() {
        tv_register=findViewById(R.id.tv_register);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        btn_login=findViewById(R.id.btn_login);
        activity=this;
    }
}