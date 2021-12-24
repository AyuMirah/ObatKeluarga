package com.tugas.obatkeluarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class detail_pasien extends AppCompatActivity {

    String hasilnama, hasilumur, hasilgender, hasilkeluhan, hasilpenanganan;
    TextView tampil_nama,tampil_umur, tampil_gender, tampil_keluhan, tampil_penanganan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);
        Toast.makeText(detail_pasien.this, "Berhasil menambahkan Pasien",
                Toast.LENGTH_LONG).show();
        Intent intent=getIntent();
        hasilnama = intent.getStringExtra("KEY_nama");
        hasilumur = intent.getStringExtra("KEY_umur");
        hasilgender = intent.getStringExtra("KEY_gender");
        hasilkeluhan = intent.getStringExtra("KEY_keluhan");
        hasilpenanganan = intent.getStringExtra("KEY_penanganan");

        tampil_nama = findViewById(R.id.tv_nama);
        tampil_umur = findViewById(R.id.tv_umur);
        tampil_gender = findViewById(R.id.tv_gender);
        tampil_keluhan = findViewById(R.id.tv_keluhan);
        tampil_penanganan = findViewById(R.id.tv_penanganan);

        tampil_nama.setText(hasilnama);
        tampil_umur.setText(hasilumur +" Tahun");
        tampil_gender.setText(hasilgender);
        tampil_keluhan.setText(hasilkeluhan);
        tampil_penanganan.setText(hasilpenanganan);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Aplikasi Obat", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Aplikasi Berhenti", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        Toast.makeText(getApplicationContext(), "Aplikasi Ditutup", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Activity Terhenti Paksa", Toast.LENGTH_SHORT).show();
    }
    public void bt_home(View view) {
        Intent hmintent = new Intent(detail_pasien.this, MainActivity.class);
        startActivity(hmintent);
        finish();
    }
}