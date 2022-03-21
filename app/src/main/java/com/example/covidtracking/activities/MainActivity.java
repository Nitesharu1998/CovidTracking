package com.example.covidtracking.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.EditText;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_USER_CONSENT = 200;
    TextView tv_register;
    EditText edt_mobile, edt_otp;
    Button btn_login;
    String str_mobile, str_password, message;
    Activity activity;
    Boolean btnChecker = false;
    String generatedOtp, checkString, newgeneratedOTP, timestamp, test;
    Random random = new Random();
    Context context;
    SmsBroadCastReceiver smsBroadCastReceiver;
    ProgressDialog dialog;

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
                if (Validator.mobileNoValidation(activity, edt_mobile))
                    if (Global.checkEqualIgnoreCase(btn_login.getText().toString(), "Get OTP")) {
                        str_mobile = edt_mobile.getText().toString().trim();
                        dialog = new ProgressDialog(activity);
                        dialog.setTitle("Please wait.....");
                        dialog.show();
                        GenerateOtp(str_mobile);
                        smartUserConsent();
                    }
                if (Global.checkEqualIgnoreCase(btn_login.getText().toString(), "login")) {
                    if (Global.checkEqualIgnoreCase(newgeneratedOTP, edt_otp.getText().toString())) {
                        startActivity(new Intent(activity, AppMain.class));
                        finish();
                    }
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

    private void registerBroadcast() {
        smsBroadCastReceiver = new SmsBroadCastReceiver();
        smsBroadCastReceiver.smsBroadcastinterface = new SmsBroadCastReceiver.SmsBroadcastinterface() {
            @Override
            public void onSuccess(Intent intent) {
                startActivityForResult(intent, REQ_USER_CONSENT);
                smartUserConsent();
            }

            @Override
            public void onFailure() {

            }

        };

        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        activity.registerReceiver(smsBroadCastReceiver, intentFilter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void smartUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(activity);
        client.startSmsUserConsent(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_USER_CONSENT&& resultCode==RESULT_OK) {
            message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                getOTP(message);

        }
    }

    private void getOTP(String message) {

        Pattern patternOfOtp = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = patternOfOtp.matcher(message);
        if (matcher.find()) {
            btn_login.setText("Login");
            newgeneratedOTP = matcher.group(0);
            edt_mobile.setVisibility(View.INVISIBLE);
            edt_otp.setVisibility(View.VISIBLE);
            edt_otp.setText(newgeneratedOTP);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        registerBroadcast();


    }

    @Override
    public void onStop() {
        super.onStop();
        activity.unregisterReceiver(smsBroadCastReceiver);
    }


    private void initUI() {
        edt_otp = findViewById(R.id.edt_otp);
        edt_mobile = findViewById(R.id.edt_mobile);
        edt_mobile.setVisibility(View.VISIBLE);
        edt_otp.setVisibility(View.INVISIBLE);
        btn_login = findViewById(R.id.btn_login);
        btnChecker = false;
        activity = this;
        context = this.getApplicationContext();
    }
}