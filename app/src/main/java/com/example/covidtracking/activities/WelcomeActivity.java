package com.example.covidtracking.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.example.covidtracking.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Dexter.withActivity(WelcomeActivity.this)
                        // below line is use to request the number of
                        // permissions which are required in our app.
                        .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                // below is the list of permissions
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.SEND_SMS)
                        // after adding permissions we are
                        // calling an with listener method.
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                // this method is called when all permissions are granted
                                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                    // do you work now
                                    Toast.makeText(WelcomeActivity.this, "All the permissions are granted..", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                                    finish();
                                }
                                // check for permanent denial of any permission
                                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                                    // permission is denied permanently,
                                    // we will show user a dialog message.
                                    finish();
                                    showSettingsDialog();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                // this method is called when user grants some
                                // permission and denies some of them.
                                permissionToken.continuePermissionRequest();
                            }
                        }).withErrorListener(new PermissionRequestErrorListener() {
                    // this method is use to handle error
                    // in runtime permissions
                    @Override
                    public void onError(DexterError error) {
                        // we are displaying a toast message for error message.
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                        // below line is use to run the permissions
                        // on same thread and to check the permissions
                        .onSameThread().check();
            }

            // below is the shoe setting dialog
            // method which is use to display a
            // dialogue message.
            private void showSettingsDialog() {
                // we are displaying an alert dialog for permissions
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

                // below line is the title
                // for our alert dialog.
                builder.setTitle("Need Permissions");

                // below line is our message for our dialog
                builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
                builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // this method is called on click on positive
                        // button and on clicking shit button we
                        // are redirecting our user from our app to the
                        // settings page of our app.
                        dialog.cancel();
                        // below is the intent from which we
                        // are redirecting our user.
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, 101);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // this method is called when
                        // user click on negative button.
                        dialog.cancel();
                    }
                });
                // below line is used
                // to display our dialog
                builder.show();

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}