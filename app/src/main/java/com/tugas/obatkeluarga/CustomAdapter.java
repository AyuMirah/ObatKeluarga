package com.tugas.obatkeluarga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_pasien, nama_pasien, penanganan;
    private Object v;
    Activity activity;


    CustomAdapter(Activity activity, Context context, ArrayList id_pasien, ArrayList nama_pasien, ArrayList penanganan){
        this.activity = activity;
        this.context = context;
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.penanganan = penanganan;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_pasien_txt.setText(String.valueOf(id_pasien.get(position)));
        holder.nama_pasien_txt.setText(String.valueOf(nama_pasien.get(position)));
        holder.penanganan_txt.setText(String.valueOf(penanganan.get(position)));


        holder.mainLayout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (context, UpdateActivity.class);
                intent.putExtra ("id",String.valueOf(id_pasien.get(position)));
                intent.putExtra ("nama",String.valueOf(nama_pasien.get(position)));
                intent.putExtra ("penanganan",String.valueOf(penanganan.get(position)));

                activity.startActivityForResult (intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_pasien.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_pasien_txt, nama_pasien_txt, penanganan_txt;
        View mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_pasien_txt = itemView.findViewById(R.id.id_pasien_txt);
            nama_pasien_txt = itemView.findViewById(R.id.nama_pasien_txt);
            penanganan_txt = itemView.findViewById(R.id.penanganan_txt);
            mainLayout = itemView.findViewById (R.id.mainLayout);

        }
    }
}
