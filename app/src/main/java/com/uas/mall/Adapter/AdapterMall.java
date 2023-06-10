package com.uas.mall.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mall.API.APIRequestData;
import com.uas.mall.API.RetroServer;
import com.uas.mall.Activity.MainActivity;
import com.uas.mall.Activity.UbahActivity;
import com.uas.mall.Model.ModelMall;
import com.uas.mall.Model.ModelResponse;
import com.uas.mall.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMall extends RecyclerView.Adapter<AdapterMall.VHMall> {
    private Context ctx;
    private List<ModelMall> ListMall;

    public AdapterMall(Context ctx, List<ModelMall> listMall) {
        this.ctx = ctx;
        this.ListMall = listMall;
    }

    @NonNull
    @Override
    public VHMall onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_mall, parent, false);
        return new VHMall(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHMall holder, int position) {
        ModelMall MM = ListMall.get(position);
        holder.tvId.setText(MM.getId());
        holder.tvDeskripsi.setText(MM.getDeskripsi());
        holder.tvNama.setText(MM.getNama());
        holder.tvKoordinat.setText(MM.getKoordinat());
        holder.tvAlamat.setText(MM.getAlamat());
        holder.tvTahun.setText(MM.getTahun());
    }

    @Override
    public int getItemCount() {
        return ListMall.size();
    }

    public class VHMall extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvDeskripsi, tvKoordinat, tvAlamat, tvTahun;
        Button btnHapus, btnUbah;

        public VHMall(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvKoordinat = itemView.findViewById(R.id.tv_koordinat);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvTahun = itemView.findViewById(R.id.tv_tahun);

            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnUbah = itemView.findViewById(R.id.btn_ubah);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteMall(tvId.getText().toString());
                }
            });

            btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    pindah.putExtra("xKoordinat", tvKoordinat.getText().toString());
                    pindah.putExtra("xAlamat", tvAlamat.getText().toString());
                    pindah.putExtra("xTahun", tvTahun.getText().toString());
                    ctx.startActivity(pindah);
                }
            });

        }

        void deleteMall(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode: " + kode+ " Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveMall();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Error! Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
