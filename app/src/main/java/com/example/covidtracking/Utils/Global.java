package com.example.covidtracking.Utils;

import android.app.Activity;
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

}
