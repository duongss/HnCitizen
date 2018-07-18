package com.example.admin.hncitizen;

import android.content.Context;
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

import com.example.admin.hncitizen.Doituong.Thongbao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterThongbao extends RecyclerView.Adapter<AdapterThongbao.appviewHolder> implements Filterable {
    LayoutInflater layoutInflater;
    ArrayList<Thongbao> thongbaoArrayList, timlist;
    Context context;
    timkiemAdapter timkiemAdapter;

    public AdapterThongbao(LayoutInflater layoutInflater, ArrayList<Thongbao> thongbaoArrayList, Context context) {
        this.layoutInflater = layoutInflater;
        this.thongbaoArrayList = thongbaoArrayList;
        this.timlist=thongbaoArrayList;
        this.context = context;
    }


    @Override
    public Filter getFilter() {
        if (timkiemAdapter == null) {
            timkiemAdapter = new timkiemAdapter(this, timlist);
        }
        return timkiemAdapter;
    }

    @Override
    public appviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new appviewHolder(layoutInflater.inflate(R.layout.adapterthongbao, parent, false));
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
    }

    @Override
    public int getItemCount() {
        return thongbaoArrayList.size();
    }


    class appviewHolder extends RecyclerView.ViewHolder {
        TextView textmota, texttomtat,ngaythongbao;
        ImageView imgAnh;
        CardView cardView;
        Button buttonthemgio;

        public appviewHolder(View itemView) {
            super(itemView);
            textmota = itemView.findViewById(R.id.textMotaThuocGioHang);
            texttomtat = itemView.findViewById(R.id.textTomtatthongbao);
            imgAnh = itemView.findViewById(R.id.anhThongbao);
            cardView = itemView.findViewById(R.id.itemX);
            ngaythongbao=itemView.findViewById(R.id.textngaythongbao);
        }
    }
}
