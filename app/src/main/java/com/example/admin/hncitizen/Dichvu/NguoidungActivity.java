package com.example.admin.hncitizen.Dichvu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;
import com.example.admin.hncitizen.R;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NguoidungActivity extends AppCompatActivity {
String hoten,diachi,taikhoan,matkhau,sodienthoai;
TextView hotentx,diachitx,taikhoantx,matkhautx,sodienthoaitx;
Button chinhsua,huy;
    KetnoiData kc = new KetnoiData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidung);
        hotentx = findViewById(R.id.khhoten);
        taikhoantx = findViewById(R.id.taikhoankh);
        sodienthoaitx = findViewById(R.id.khsdt);
        chinhsua = findViewById(R.id.edit);
        diachitx = findViewById(R.id.khdiachi);
        matkhautx=findViewById(R.id.khmatkhau);
        SharedPreferences sharedPreferences = getSharedPreferences("Myuser", MODE_PRIVATE);
        taikhoan = sharedPreferences.getString("Taikhoan", "");
        hoten = sharedPreferences.getString("Hoten", "ho ten rong");
        matkhau = sharedPreferences.getString("Matkhau", "sdt rong");
        diachi = sharedPreferences.getString("Diachi", "anh khach hang rong");
        sodienthoai = sharedPreferences.getString("Sodienthoai", "anh khach hang rong");
        hotentx.setText(hoten);
        sodienthoaitx.setText(sodienthoai);
        diachitx.setText(diachi);
        matkhautx.setText(matkhau);
        taikhoantx.setText(taikhoan);
        huy=findViewById(R.id.cancel);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NguoidungActivity.this, ThongbaoActivity.class);
                intent.putExtra("tentk", taikhoan);
                startActivity(intent);
            }
        });
        chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chinhsua();
            }
        });
    }
    public void chinhsua() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection con;
                    String sql;
                    sql = "UPDATE taikhoankh SET hoten=?,sodienthoai=?,diachi=?,matkhau=? WHERE taikhoan=?";
                    con = kc.ketnoi();
                    PreparedStatement prest = con.prepareStatement(sql);
                    prest.setString(5, taikhoan);
                    if (hotentx.getText() != null || sodienthoaitx.getText() != null || diachitx.getText() != null) {
                        prest.setString(1, hotentx.getText().toString().trim());
                        prest.setString(2, sodienthoaitx.getText().toString().trim());
                        prest.setString(3, diachitx.getText().toString().trim());
                        prest.setString(4, matkhautx.getText().toString().trim());
                        prest.executeUpdate();

                        Intent intent = new Intent(NguoidungActivity.this, ThongbaoActivity.class);
                        intent.putExtra("tentk", taikhoan);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NguoidungActivity.this,"thiếu dữ liệu nhập",Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }

                    mIncomingHandler.sendEmptyMessage(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {

            return true;
        }
    });
}
