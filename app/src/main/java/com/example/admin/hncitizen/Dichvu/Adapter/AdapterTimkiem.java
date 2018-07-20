package com.example.admin.hncitizen.Dichvu.Adapter;

import android.widget.Filter;

import com.example.admin.hncitizen.Doituong.Thongbao;

import java.util.ArrayList;

public class AdapterTimkiem extends Filter {
    AdapterThongbao adapterThongbao;
    ArrayList<Thongbao>listthongbao;

    public AdapterTimkiem(AdapterThongbao adapterThongbao, ArrayList<Thongbao> listthongbao) {
        this.adapterThongbao = adapterThongbao;
        this.listthongbao = listthongbao;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint!=null && constraint.length()>0)
        {
            constraint=constraint.toString();
            ArrayList<Thongbao> timlist= new ArrayList<>();
            for (int i=0;i<listthongbao.size();i++)
            {
                if (listthongbao.get(i).getMotaThongbao().contains(constraint))
                {
                    timlist.add(listthongbao.get(i));

                }
            }
            filterResults.count=timlist.size();
            filterResults.values=timlist;
        }
        else
        {
            filterResults.count=listthongbao.size();
            filterResults.values=listthongbao;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterThongbao.thongbaoArrayList=((ArrayList<Thongbao>)results.values);
        adapterThongbao.notifyDataSetChanged();
    }
}
