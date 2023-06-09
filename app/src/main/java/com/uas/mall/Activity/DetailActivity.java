package com.uas.mall.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.uas.mall.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvDeskripsi, tvKoordinat, tvAlamat, tvTahun;
    private String yNama, yTDeskripsi, yKoordinat, yAlamat, yTahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvKoordinat = findViewById(R.id.tv_koordinat);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvTahun = findViewById(R.id.tv_tahun);

        Intent terima = getIntent();
        yNama = terima.getStringExtra("xNama");
        yTDeskripsi = terima.getStringExtra("xDeskripsi");
        yKoordinat = terima.getStringExtra("xKoordinat");
        yAlamat = terima.getStringExtra("xAlamat");
        yTahun = terima.getStringExtra("xTahun");


        tvNama.setText(yNama);
        tvDeskripsi.setText(yTDeskripsi);
        tvKoordinat.setText(yKoordinat);
        tvAlamat.setText(yAlamat);
        tvTahun.setText(yTahun);

    }

}