package com.solidtype.atenas_apk_2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation.BluetoothActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, BluetoothActivity.class);
            startActivity(intent);
            finish();
        },1500);
    }
}