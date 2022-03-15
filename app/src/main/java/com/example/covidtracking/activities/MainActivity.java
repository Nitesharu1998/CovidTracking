package com.example.covidtracking.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidtracking.BroacastReceiver.SmsBroadCastReceiver;
import com.example.covidtracking.R;
import com.example.covidtracking.Utils.Global;
import com.example.covidtracking.Utils.Validator;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_USER_CONSENT = 200;
    TextView tv_register;
    TextInputEditText edt_mobile, edt_otp;
    Button btn_login;
    String str_username, str_password;
    Activity activity;
    Boolean btnChecker = false;
    String generatedOtp, checkString, newgeneratedOTP, timestamp, test;
    Random random = new Random();
    Context context;
    SmsBroadCastReceiver smsBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initListeners();
    }

    private void initListeners() {
        btn_login.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                testmessage();
                /*if (Global.checkEqualIgnoreCase(btn_login.getText().toString(),"Validate OTP")){
                    str_username = edt_mobile.getText().toString().trim();

                    if (btnChecker) {
                        getNumber();
                    }
                }
                if (Global.checkEqualIgnoreCase(btn_login.getText().toString(),"login")){
                    if (Global.checkEqualIgnoreCase(newgeneratedOTP,edt_otp.getText().toString())){
                        startActivity(new Intent(activity,AppMain.class));
                    }
                }*/

            }
        });

        edt_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 10) {
                    btnChecker = true;
                    btn_login.setAlpha(1.0f);
                    btn_login.setClickable(true);
                } else {
                    btnChecker = false;
                    btn_login.setAlpha(0.5f);
                    btn_login.setClickable(false);
                }
            }
        });
    }

    private void GenerateOtp(String phone) {
        generatedOtp = String.valueOf(random.nextInt(1000000));
        if (generatedOtp.length() == 6) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, "your OTP is  " + generatedOtp, null, null);
        } else {
            GenerateOtp(phone);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getNumber() {
        if (Validator.mobileNoValidation(activity, edt_mobile)) {
            GenerateOtp(edt_mobile.getText().toString());
            smartUserConsent();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void smartUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(context);
        client.startSmsUserConsent(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_USER_CONSENT){
            if ((resultCode==RESULT_OK) && (data !=null)){
                String message=data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                btn_login.setText("Validate OTP");
                getOTP(message);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getOTP(String message) {

        Pattern patternOfOtp= Pattern.compile("(|^)\\d{6}");
        Matcher matcher = patternOfOtp.matcher(message);
        if (matcher.find()){
            System.out.println("MATCHER"+matcher.group(0));
            edt_otp.setText(matcher.group(0));
            newgeneratedOTP=matcher.group(0);
            edt_otp.setVisibility(View.VISIBLE);
            btn_login.setText("Login");

        }
    }

    private void testmessage() {
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage("8451079482", null, "Your OTP is generated", null, null);
            Global.showToast(activity, "Message Sent",0);
        } catch (Exception ex) {
            Global.showToast(activity, ex.getMessage(),0);
            ex.printStackTrace();
        }
    }

    private void registerBroadcast(){
        smsBroadCastReceiver = new SmsBroadCastReceiver();
        smsBroadCastReceiver.smsBroadcastinterface= new SmsBroadCastReceiver.SmsBroadcastinterface() {
            @Override
            public void onSuccess(Intent intent) {
                startActivityForResult(intent,REQ_USER_CONSENT);
            }
            @Override
            public void onFailure() {
            }

        };

        IntentFilter intentFilter=new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        context.registerReceiver(smsBroadCastReceiver,intentFilter);

    }

    @Override
    public void onStart() {
        super.onStart();
        registerBroadcast();
    }

    private void initUI() {
        edt_otp=findViewById(R.id.edt_otp);
        edt_mobile = findViewById(R.id.edt_mobile);
        btn_login = findViewById(R.id.btn_login);
        btnChecker = false;
        btn_login.setAlpha(0.5f);
        btn_login.setClickable(false);
        activity = this;
        context = this.getApplicationContext();
    }
}