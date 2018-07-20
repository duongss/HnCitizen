package com.example.admin.hncitizen.Dichvu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admin.hncitizen.Dichvu.Adapter.AdapterBinhluan;
import com.example.admin.hncitizen.Doituong.Binhluan;
import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.R;

import java.util.ArrayList;

public class BinhluanActivity extends AppCompatActivity {
    AdapterBinhluan arrayAdapter;
    ArrayList<Binhluan> binhluanArrayList;
    ListView listViewbl;
    EditText textbl;
    tkNguoidan tkNguoidan;
    Thongbao thongbao;
    ImageView image;
    Binhluan binhluan;
    Data db;
    int idtb=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binhluan);
        listViewbl = findViewById(R.id.listviewbl);
        image = findViewById(R.id.dangbinhluan);
        db = new Data(BinhluanActivity.this);
        textbl = findViewById(R.id.textbl);

        final SharedPreferences sharedPreferences = getSharedPreferences("Myuser", Context.MODE_PRIVATE);
        final String taikhoan = sharedPreferences.getString("Taikhoan", "");
        idtb = sharedPreferences.getInt("idtb", 0);
      //  idtb = getIntent().getIntExtra("idTb",0);
        Dulieu();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binhluanArrayList = new ArrayList<>();
                binhluan = new Binhluan();
                binhluan.setBinhluan(textbl.getText().toString());
                binhluan.setTkNguoidan(taikhoan);
                binhluan.setIdThongbao(idtb);
                db.addbl(binhluan);
                binhluanArrayList = db.getbl(idtb);
                arrayAdapter = new AdapterBinhluan(getLayoutInflater(),binhluanArrayList);
                listViewbl.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        arrayAdapter = new AdapterBinhluan(getLayoutInflater(),binhluanArrayList);
        listViewbl.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    private void Dulieu() {
        binhluanArrayList = new ArrayList<>();
        binhluan = new Binhluan();
        binhluan.setBinhluan("heloo");
        binhluan.setIdThongbao(idtb);
        binhluan.setTkNguoidan("dss");

        db.addbl(binhluan);
        binhluanArrayList = db.getbl(idtb);

    }

}
