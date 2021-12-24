package com.tugas.obatkeluarga;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tugas.obatkeluarga.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button bt_input = findViewById(R.id.input);
       Button bt_rekam_medis = findViewById(R.id.rekam_medis);

        bt_input.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, input_pasien.class);
                startActivity(intent);
            }
        });

        bt_rekam_medis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Rekam_medis.class);
                startActivity(intent);
            }
        });
    }
    public void bt_info(View view) {
        Intent intent = new Intent(MainActivity.this, info.class);
        startActivity(intent);
        //  overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
}