package com.tugas.obatkeluarga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Rekam_medis extends AppCompatActivity {

    RecyclerView recyclerView;

    DataHelper mydh;
    ArrayList<String> id_pasien, nama_pasien, penanganan,alamat_member, gender_member, pembayaran_member, interval_member;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_medis);

        recyclerView = findViewById(R.id.recyclerView);

        mydh = new DataHelper(Rekam_medis.this);
        id_pasien = new ArrayList<>();
        nama_pasien = new ArrayList<>();
        penanganan = new ArrayList<>();


        displayData();

        customAdapter = new CustomAdapter(Rekam_medis.this,this, id_pasien, nama_pasien, penanganan);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Rekam_medis.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = mydh.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                id_pasien.add(cursor.getString(0));
                nama_pasien.add(cursor.getString(1));
                penanganan.add(cursor.getString(5));

            }
        }
    }
}