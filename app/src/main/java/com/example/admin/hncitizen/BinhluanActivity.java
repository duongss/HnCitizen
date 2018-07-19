package com.example.admin.hncitizen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.admin.hncitizen.Doituong.Binhluan;
import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Dulieu.Data;

import java.util.ArrayList;

public class BinhluanActivity extends AppCompatActivity {
    ArrayAdapter<Binhluan> arrayAdapter;
    ArrayList<Binhluan> binhluanArrayList;
    ListView listViewbl;
    EditText textbl;
    tkNguoidan tkNguoidan;
    Thongbao thongbao;
    ImageButton imageButton;
    Binhluan binhluan;
    Data db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binhluan);
        listViewbl = findViewById(R.id.listbinhluan);
        imageButton = findViewById(R.id.dangbinhluan);
        db = new Data(BinhluanActivity.this);
        textbl = findViewById(R.id.textbl);
          Dulieu();
        final SharedPreferences sharedPreferences = getSharedPreferences("Myuser", Context.MODE_PRIVATE);
      final String  taikhoan = sharedPreferences.getString("Taikhoan", "");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binhluanArrayList = new ArrayList<>();
                binhluan = new Binhluan();
                binhluan.setBinhluan(textbl.getText().toString());
                binhluan.setTkNguoidan(taikhoan);
                binhluan.setIdThongbao(0);
                db.addbl(binhluan);
                binhluanArrayList = db.getbl();
                listViewbl.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, binhluanArrayList);
        listViewbl.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
    private void Dulieu()
    { binhluanArrayList = new ArrayList<>();
        binhluan= new Binhluan("hellzz","dssds");
        binhluan.setBinhluan("heloo");
        binhluan.setIdThongbao(0);
        binhluan.setTkNguoidan("dss");

        db.addbl(binhluan);
        binhluanArrayList =db.getbl();
    }

}
