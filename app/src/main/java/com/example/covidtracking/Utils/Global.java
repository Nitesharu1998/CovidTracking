package com.example.covidtracking.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.telephony.CellSignalStrength;
import android.widget.Toast;

public class Global {

    public static boolean isNull(String str){
        return str==null || str.equalsIgnoreCase("null")||str.trim().equalsIgnoreCase("null")||
                str.equalsIgnoreCase(null);
    }

    public static void showToast(Activity activity, String message, int duration){
        try {
            if (!isNull(message)){
                if (duration==1){
                    Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ProgressDialog progress(Context context, boolean autcancel) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Kindly Wait..");
        progress.setMessage("Processing Request");
        progress.setCanceledOnTouchOutside(autcancel);
        progress.setCancelable(autcancel);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progress;
    }

    public static boolean checkEqualIgnoreCase(String str1, String str2) {
        return !isNull(str1) && !isNull(str2) && str1.equalsIgnoreCase(str2);
    }



}
