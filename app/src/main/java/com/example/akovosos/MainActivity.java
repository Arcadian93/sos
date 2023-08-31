package com.example.akovosos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static  final int REQUEST_CALL =1;
    private TextView callText;
    private Button CallBtn;
    private static final int PHONE_CALL_REQUEST=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callText = findViewById(R.id.callTxt1);
        CallBtn = findViewById(R.id.callButton1);
        CallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton ();
            }
        });
    }
    private void CallButton (){
        String number = callText.getText().toString();
        if (number.trim().length()>0){
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else {
                String dial="tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }
}