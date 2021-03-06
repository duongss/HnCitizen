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
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    Button dangkybt, huybobt;
    EditText taikhoan, matkhau, diachi, sodienthoai, hoten;
    Data db;
    ArrayList<tkNguoidan> s = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        taikhoan = findViewById(R.id.taikhoanTx);
        matkhau = findViewById(R.id.matkhauTx);
        diachi = findViewById(R.id.DiachiTx);
        sodienthoai = findViewById(R.id.SodienthoaiTx);
        hoten = findViewById(R.id.HotenTx);
        dangkybt = findViewById(R.id.DangkyBtn);
        huybobt = findViewById(R.id.HuyboBtn);
        huybobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        dangky();
    }

    public void dangky() {
        dangkybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taikhoan.getText().toString().matches("")&&matkhau.getText().toString().matches("")) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thiếu", Toast.LENGTH_SHORT).show();
                } else {

                    tkNguoidan tk = new tkNguoidan(taikhoan.getText().toString(), matkhau.getText().toString(), diachi.getText().toString(), sodienthoai.getText().toString(), hoten.getText().toString());
                 //   db = new Data(RegisterActivity.this);
                 //   db.addS(tk);
                    themtk(tk);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    protected void themtk(final tkNguoidan tkNguoidan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con;
                KetnoiData kc = new KetnoiData();
                con = kc.ketnoi();
                String sql = "Insert into taikhoankh (`taikhoan`, `matkhau`, `sodienthoai`, `hoten`, `diachi`) VALUES (?,?,?,?,?) ";

                try {
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setString(1, tkNguoidan.getTaikhoan());
                    stm.setString(2, tkNguoidan.getMatkhau());
                    stm.setString(3, tkNguoidan.getSodienthoai());
                    stm.setString(4, tkNguoidan.getHoten());
                    stm.setString(5, tkNguoidan.getDiachi());

                    stm.executeUpdate();
                    mIncomingHandler.sendEmptyMessage(0);
                } catch (SQLException e) {
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
