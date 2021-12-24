package com.tugas.obatkeluarga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText up_nama, up_umur, up_gender, up_keluhan, up_penanganan;
    Button update_btn, hapus_btn, hapus;
    String id, nama, umur, gender, keluhan, penanganan;
    String falidnama, falidumur, falidkeluhan;
    DataHelper mydh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mydh = new DataHelper(UpdateActivity.this);
        up_nama = findViewById(R.id.tv_nama);
        up_umur = findViewById(R.id.tv_umur);
        up_gender = findViewById(R.id.tv_gender);
        up_keluhan = findViewById(R.id.tv_keluhan);
        up_penanganan = findViewById(R.id.tv_penanganan);
        update_btn = findViewById(R.id.update_btn);
        hapus_btn =findViewById(R.id.hapus_btn);



        getIntendata();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = up_nama.getText().toString();
                umur = up_umur.getText().toString();
                gender= up_gender.getText().toString();
                keluhan = up_keluhan.getText().toString();
                penanganan = up_penanganan.getText().toString();

                if (nama.compareTo(falidnama)==0 && umur.compareTo(falidumur)==0 && keluhan.compareTo(falidkeluhan)==0 )   {
                    Toast.makeText(UpdateActivity.this, "Tidak ada perubahan data", Toast.LENGTH_LONG).show();
                }else{
                    mydh.updateData(id, nama, umur, gender, keluhan, penanganan);
                }



            }
        });
        hapus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasi();
            }
        });
    }

    void getIntendata() {
        getIntent().hasExtra("nama");
        //get data from intent
        id = getIntent().getStringExtra("id");
        //namaa = getIntent().getStringExtra("nama");
        getData();
    }



    void getData(){
        Cursor cursor = mydh.getdata(id);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                up_nama.setText(cursor.getString(1));
                up_umur.setText(cursor.getString(2));
                up_gender.setText(cursor.getString(3));
                up_keluhan.setText(cursor.getString(4));
                up_penanganan.setText(cursor.getString(5));


                falidnama = (cursor.getString(1));
                falidumur = (cursor.getString(2));
                falidkeluhan = (cursor.getString(4));
            }
        }
    }

    void konfirmasi(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data " + falidnama +" ?");
        builder.setMessage("Data akan hilang setelah dihapus apakah yakin ingin menghapus data "+falidnama +" ?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataHelper mydh = new DataHelper(UpdateActivity.this);
                mydh.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}