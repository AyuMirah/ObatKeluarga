package com.tugas.obatkeluarga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class input_pasien extends AppCompatActivity {

    EditText nama,keluhan;
    Button btn_daftar;
    String resepObat;
    String rawatInap;
    String rawatJalan;
    String Getnama, Getkeluhan, Getgender, Getpenanganan,Getumur;
    String sex="default";
    RadioGroup gender;
    CheckBox rawatinap, rawatjalan, resepobat;
    RadioButton rb;
    SeekBar seekBar;
    TextView textView2, TextGender, Textpenanganan;

    private int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pasien);

        nama = findViewById(R.id.nama);
        keluhan = findViewById(R.id.keluhan);
        btn_daftar = findViewById(R.id.daftar);
        gender = (RadioGroup) findViewById(R.id.gender);
        seekBar = findViewById(R.id.seekBar);
        textView2 = findViewById(R.id.umur);
        TextGender = findViewById(R.id.textView8);
        resepobat = findViewById(R.id.resepobat);
        rawatinap = findViewById(R.id.rawatinap);
        rawatjalan = findViewById(R.id.rawatjalan);
        Textpenanganan = findViewById(R.id.textpenanganan);




        textView2.setText(seekBar.getProgress() + "/" + seekBar.getMax() + " Tahun");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText(progress + "/" + seekBar.getMax() + " Tahun");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Getnama = nama.getText().toString();
                Getkeluhan = keluhan.getText().toString();
                Getgender = sex;
                cek_box();
                Getpenanganan = resepObat + rawatInap + rawatJalan;
                Getumur = Integer.toString(progress);

                if(nama.getText().toString().length()==0) {
                    nama.setError("Nama diperlukan!");
                    Toast.makeText(input_pasien.this, "Nama Masih Kosong", Toast.LENGTH_LONG).show();
                } else if(keluhan.getText().toString().length()==0) {
                    keluhan.setError("Keluhan diperlukan!");
                    Toast.makeText(input_pasien.this, "Keluhan Masih Kosong", Toast.LENGTH_LONG).show();
                }else if(Getgender=="default") {
                    TextGender.setError("Pilihan diperlukan!");
                    Toast.makeText(input_pasien.this, "Gender Masih Kosong", Toast.LENGTH_LONG).show();
                }else if(Getpenanganan.length()==0) {
                    TextGender.setError(null);
                    Textpenanganan.setError("Pilihan diperlukan!");
                    Toast.makeText(input_pasien.this, "Penanganan Masih Kosong", Toast.LENGTH_LONG).show();
                }else {
                    Textpenanganan.setError(null);
                    shwDialog();
                }



            }
        });

    }

    public void rbclick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.pria:
                if (checked)
                    sex = "Laki-Laki";
                break;
            case R.id.wanita:
                if (checked)
                    sex = "Perempuan";
                break;
        }
    }

    public void cek_box(){
        if(resepobat.isChecked()){
            resepObat ="Resep Obat ";
        }else if(!resepobat.isChecked()){
            resepObat = "";
        }
        if(rawatinap.isChecked()){
            rawatInap ="Rawat Inap ";
        }else if(!rawatinap.isChecked()){
            rawatInap = "";
        }
        if(rawatjalan.isChecked()){
            rawatJalan ="Rawat Jalan";
        }else if(!rawatjalan.isChecked()){
            rawatJalan = "";
        }
    }


    private void shwDialog(){
        Dialog dialog = new Dialog(this) ;
        dialog.setContentView(R.layout.dialogview);

        TextView hasilnama = dialog.findViewById(R.id.tv_nama);
        TextView hasilkeluhan = dialog.findViewById(R.id.tv_keluhan);
        TextView hasilgender = dialog.findViewById(R.id.tv_gender);
        TextView hasilpenanganan = dialog.findViewById(R.id.tv_penanganan);
        TextView hasilumur = dialog.findViewById(R.id.tv_umur);
        Button btkirim = dialog.findViewById(R.id.kirim);

        hasilnama.setText(Getnama);
        hasilkeluhan.setText(Getkeluhan);
        hasilgender.setText(Getgender);
        hasilpenanganan.setText(Getpenanganan);
        hasilumur.setText(Getumur+" tahun");

        btkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHelper mydh = new DataHelper(input_pasien.this);
                mydh.addpasien(Getnama.trim(), Getumur.trim(),
                        Getgender.trim(), Getkeluhan.trim(), Getpenanganan.trim());
                Intent intent = new Intent(input_pasien.this,detail_pasien.class);
                intent.putExtra("KEY_nama", Getnama);
                intent.putExtra("KEY_umur", Getumur);
                intent.putExtra("KEY_gender", Getgender);
                intent.putExtra("KEY_keluhan", Getkeluhan);
                intent.putExtra("KEY_penanganan", Getpenanganan);

                startActivity(intent);
                finish();



            }
        });

        dialog.show();
    }
}
