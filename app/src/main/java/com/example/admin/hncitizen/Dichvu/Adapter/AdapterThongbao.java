package com.example.admin.hncitizen.Dichvu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.hncitizen.Dichvu.BinhluanActivity;
import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterThongbao extends RecyclerView.Adapter<AdapterThongbao.appviewHolder> implements Filterable {
    LayoutInflater layoutInflater;
    ArrayList<Thongbao> thongbaoArrayList, timlist;
    Context context;
    AdapterTimkiem timkiemAdapter;

    public AdapterThongbao(LayoutInflater layoutInflater, ArrayList<Thongbao> thongbaoArrayList, Context context) {
        this.layoutInflater = layoutInflater;
        this.thongbaoArrayList = thongbaoArrayList;
        this.timlist = thongbaoArrayList;
        this.context = context;
    }


    @Override
    public Filter getFilter() {
        if (timkiemAdapter == null) {
            timkiemAdapter = new AdapterTimkiem(this, timlist);
        }
        return timkiemAdapter;
    }

    @Override
    public appviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new appviewHolder(layoutInflater.inflate(R.layout.itemlisttb, parent, false));
    }

    @Override
    public void onBindViewHolder(appviewHolder holder, int position) {
        final Thongbao th = thongbaoArrayList.get(position);
        holder.textmota.setText(th.getMotaThongbao());
        holder.texttomtat.setText(th.getTomtatThongbao());
        holder.ngaythongbao.setText(th.getNgayThongbao());
        Picasso.with(context)
                .load(th.getAnhThongbao())
                .into(holder.imgAnh);
        holder.binhluanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("Myuser", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("idtb", th.getIdThongbao());
                edit.commit();
                Intent intent = new Intent(context, BinhluanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              //  intent.putExtra("idTb",th.getIdThongbao());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thongbaoArrayList.size();
    }


    class appviewHolder extends RecyclerView.ViewHolder {
        TextView textmota, texttomtat, ngaythongbao;
        ImageView imgAnh;
        CardView cardView;
        Button binhluanbtn;

        public appviewHolder(View itemView) {
            super(itemView);
            textmota = itemView.findViewById(R.id.textMota);
            texttomtat = itemView.findViewById(R.id.textTomtatthongbao);
            imgAnh = itemView.findViewById(R.id.anhThongbao);
            ngaythongbao = itemView.findViewById(R.id.textngaythongbao);
            cardView = itemView.findViewById(R.id.itemX);
            binhluanbtn = itemView.findViewById(R.id.binhluanbtn);
        }
    }
}
