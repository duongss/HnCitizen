package com.example.admin.hncitizen.Dichvu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;
import com.example.admin.hncitizen.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<tkNguoidan> Listtk;
    Button dangnhapbt, dangkybt;
    EditText taikhoan, matkhau;
    String taikhoanxacthuc, matkhauxacthuc;
    Data db;
    Connection con;
    KetnoiData kc = new KetnoiData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new Data(this);
        xacthucTk();
        Dangnhap();
    }

    public void Dangnhap() {
        taikhoan = findViewById(R.id.tkhoan);
        matkhau = findViewById(R.id.mkhau);
        dangnhapbt = findViewById(R.id.Dangnhap);
        dangkybt = findViewById(R.id.DangkyN);
        dangkybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        dangnhapbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (tkNguoidan c : Listtk) {
                    taikhoanxacthuc = c.getTaikhoan();
                    matkhauxacthuc = c.getMatkhau();
                    if (taikhoan.getText().toString().equals(taikhoanxacthuc)&&matkhau.getText().toString().equals(matkhauxacthuc)) {
                        Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent x = new Intent(LoginActivity.this, ThongbaoActivity.class);
                        x.putExtra("tentk", taikhoan.getText().toString());
                        startActivity(x);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    protected void xacthucTk()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                Listtk = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM taikhoankh";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        tkNguoidan tk =new tkNguoidan();
                        tk.setTaikhoan(rs.getString("taikhoan"));
                        tk.setMatkhau(rs.getString("matkhau"));
                        tk.setSodienthoai(rs.getString("sodienthoai"));
                        tk.setHoten(rs.getString("hoten"));
                        tk.setDiachi(rs.getString("diachi"));
                        Listtk.add(tk);
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

            return true;
        }
    });
}
