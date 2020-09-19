package com.example.run_time_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int My_Permission_request_Read_Conatacts = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if permission not granted
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //permission not granted,so i will request again
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS)) {
                //i'm using alerDialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Grant Permission Please");
                builder.setMessage("To run this app you must give permission otherwise it may create some problem.\nSo,please allow them");

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, My_Permission_request_Read_Conatacts);
                    }
                });
                builder.setPositiveButton("Grant Permission", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, My_Permission_request_Read_Conatacts);
                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, My_Permission_request_Read_Conatacts);
            }
        } else {
            //permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case My_Permission_request_Read_Conatacts: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Now,you are good to go", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You must grant permission,otherwise app may crash", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            //for other permissions,here i will add those casese
        }
    }
}
