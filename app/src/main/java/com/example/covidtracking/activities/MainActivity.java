package com.example.covidtracking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    Boolean btnChecker=false;

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

                str_username=edt_username.getText().toString().trim();

                if (btnChecker){
                    if (getandVerifyOTP())
                    startActivity(new Intent(activity,AppMain.class));
                }
            }
        });

        edt_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==10){
                    btnChecker=true;
                    btn_login.setAlpha(1.0f);
                    btn_login.setClickable(true);
                }
                else{
                    btnChecker=false;
                    btn_login.setAlpha(0.5f);
                    btn_login.setClickable(false);
                }
            }
        });
    }

    private boolean getandVerifyOTP() {


        return true;
    }

    private boolean credsValidations(String str_username) {
        if (Global.isNull(str_username)){
            Global.showToast(activity,"please enter username",1);
            return false;
        }
        return true;
    }

    private void initUI() {
        tv_register=findViewById(R.id.tv_register);
        edt_username=findViewById(R.id.edt_mobile);
        edt_password=findViewById(R.id.edt_password);
        btn_login=findViewById(R.id.btn_login);
        btnChecker=false;
        btn_login.setAlpha(0.5f);
        btn_login.setClickable(false);
        activity=this;
    }
}