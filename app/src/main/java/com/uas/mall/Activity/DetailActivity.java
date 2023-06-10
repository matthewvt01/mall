package com.uas.mall.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.uas.mall.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvDeskripsi, tvKoordinat, tvAlamat, tvTahun;
    private String dNama, dDeskripsi, dKoordinat, dAlamat, dTahun;

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
        dNama = terima.getStringExtra("xNama");
        dDeskripsi = terima.getStringExtra("xDeskripsi");
        dKoordinat = terima.getStringExtra("xKoordinat");
        dAlamat = terima.getStringExtra("xAlamat");
        dTahun = terima.getStringExtra("xTahun");


        tvNama.setText(dNama);
        tvDeskripsi.setText(dDeskripsi);
        tvKoordinat.setText(dKoordinat);
        tvAlamat.setText(dAlamat);
        tvTahun.setText(dTahun);

    }

}