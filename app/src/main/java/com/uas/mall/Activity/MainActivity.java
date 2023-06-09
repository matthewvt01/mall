package com.uas.mall.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uas.mall.API.APIRequestData;
import com.uas.mall.API.RetroServer;
import com.uas.mall.Adapter.AdapterMall;
import com.uas.mall.Model.ModelMall;
import com.uas.mall.Model.ModelResponse;
import com.uas.mall.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMall;
    private FloatingActionButton fabTambah;
    private ProgressBar pbMall;

    private RecyclerView.Adapter adMall;
    private RecyclerView.LayoutManager lmMall;
    private List<ModelMall> listMall = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMall = findViewById(R.id.rv_mall);
        fabTambah = findViewById(R.id.fab_tambah);
        pbMall = findViewById(R.id.pb_mall);

        lmMall = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMall.setLayoutManager(lmMall);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveMall();
    }

    public void retrieveMall(){
        pbMall.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listMall = response.body().getData();

                adMall = new AdapterMall(MainActivity.this, listMall);
                rvMall.setAdapter(adMall);
                adMall.notifyDataSetChanged();

                pbMall.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed Connect to Server !", Toast.LENGTH_SHORT).show();
                pbMall.setVisibility(View.GONE);
            }
        });

    }
}