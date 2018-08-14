package com.example.admin.hncitizen.Dichvu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.hncitizen.Doituong.Cauhoi;
import com.example.admin.hncitizen.R;

import java.util.ArrayList;

public class AdapterLichsuch extends BaseAdapter {
    ArrayList<Cauhoi>ch;
    LayoutInflater layoutInflater;
    TextView Ngaych,noidungch;
    public AdapterLichsuch(ArrayList<Cauhoi> ch, LayoutInflater layoutInflater) {
        this.ch = ch;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return ch.size();
    }

    @Override
    public Object getItem(int position) {
        return ch.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.itemlsch,  null);

        }

        Ngaych=convertView.findViewById(R.id.ngayguitxt);
        noidungch=convertView.findViewById(R.id.noidungtxt);
        Cauhoi bl = ch.get(position);
        Ngaych.setText(bl.getNgayGuicauhoi());
        noidungch.setText(bl.getNoidungCauhoi());
        return convertView;
    }
}
