package com.example.admin.hncitizen.Dichvu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.example.admin.hncitizen.Dichvu.Adapter.AdapterLichsuch;
import com.example.admin.hncitizen.Doituong.Cauhoi;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;
import com.example.admin.hncitizen.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LichsuChActivity extends AppCompatActivity {
    ArrayList<Cauhoi> listch;
    AdapterLichsuch adapterLichsuch;
    Data db;
    Connection con;
    KetnoiData kc = new KetnoiData();
    ListView listViewls;
    String taikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu_ch);
        listViewls = findViewById(R.id.listls);
        hienthich();
        final SharedPreferences sharedPreferences = getSharedPreferences("Myuser", Context.MODE_PRIVATE);
        taikhoan = sharedPreferences.getString("Taikhoan", "");
    }

    protected void hienthich() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                listch = new ArrayList<>();
                try {

                    String sql;
                    sql = "SELECT * FROM cauhoi WHERE taikhoanNguoidan = ?";
                    PreparedStatement prest = con.prepareStatement(sql);
                    prest.setString(1, taikhoan);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        Cauhoi tb = new Cauhoi();
                        tb.setNgayGuicauhoi(rs.getString("ngayGuiCauhoi"));
                        tb.setNoidungCauhoi(rs.getString("noidungCauhoi"));
                        listch.add(tb);
                        mIncomingHandler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Cauhinhlist();
            return true;
        }
    });

    private void Cauhinhlist() {

        adapterLichsuch = new AdapterLichsuch(listch, getLayoutInflater());
        listViewls.setAdapter(adapterLichsuch);
    }
}
