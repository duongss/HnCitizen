package com.example.admin.hncitizen.Dichvu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.hncitizen.Doituong.Binhluan;
import com.example.admin.hncitizen.R;

import java.util.ArrayList;

public class AdapterBinhluan extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Binhluan> binhluansArrayList;
    Context context;
TextView taikh,binhluanbl;
    public AdapterBinhluan(LayoutInflater layoutInflater, ArrayList<Binhluan> binhluansArrayList, Context context) {
        this.layoutInflater = layoutInflater;
        this.binhluansArrayList = binhluansArrayList;
        this.context = context;
    }

    public AdapterBinhluan(LayoutInflater layoutInflater, ArrayList<Binhluan> binhluansArrayList) {
        this.layoutInflater = layoutInflater;
        this.binhluansArrayList = binhluansArrayList;
    }

    @Override
    public int getCount() {
        return binhluansArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return binhluansArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.itemlistbl,  null);

        }

        taikh=convertView.findViewById(R.id.taikhoanbl);
        binhluanbl=convertView.findViewById(R.id.binhluanbl);
        Binhluan bl = binhluansArrayList.get(position);
        taikh.setText(bl.getTkNguoidan());
        binhluanbl.setText(bl.getBinhluan());
        return convertView;
    }

}
