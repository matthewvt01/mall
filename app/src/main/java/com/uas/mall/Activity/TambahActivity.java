package com.uas.mall.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.uas.mall.API.APIRequestData;
import com.uas.mall.API.RetroServer;
import com.uas.mall.Model.ModelResponse;
import com.uas.mall.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etDeskripsi, etKoordinat, etAlamat, etTahun;
    private Button btnTambah;
    private String nama, deskripsi, koordinat, alamat, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etKoordinat = findViewById(R.id.et_koordinat);
        etAlamat = findViewById(R.id.et_alamat);
        etTahun = findViewById(R.id.et_tahun);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                koordinat = etKoordinat.getText().toString();
                alamat = etAlamat.getText().toString();
                tahun = etTahun.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if(deskripsi.trim().isEmpty()){
                    etDeskripsi.setError("Deskripsi Tidak Boleh Kosong");
                }
                else if(koordinat.trim().isEmpty()){
                    etKoordinat.setError("Koordinat Tidak Boleh Kosong");
                }
                else if (alamat.trim().isEmpty()) {
                    etAlamat.setError("Alamat Tidak Boleh Kosong");
                }
                else if(tahun.trim().isEmpty()){
                    etTahun.setError("Tahun Tidak Boleh Kosong");
                }
                else{
                    tambahMall();
                }
            }
        });
    }

    private void tambahMall(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardCreate(nama, deskripsi, koordinat, alamat, tahun);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + " Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Error: Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                Log.d("DISINI", "Errornya itu: " + t.getMessage());
            }
        });
    }
}