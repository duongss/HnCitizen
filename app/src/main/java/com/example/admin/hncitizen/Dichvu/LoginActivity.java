package com.example.admin.hncitizen.Dichvu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<tkNguoidan> Listtk;
    Button dangnhapbt, dangkybt;
    EditText taikhoan, matkhau;
    String taikhoanxacthuc, matkhauxacthuc;
    Data db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new Data(this);
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

                Listtk = db.gettk();
                for (tkNguoidan c : Listtk) {
                    taikhoanxacthuc = c.getTaikhoan();
                    matkhauxacthuc = c.getMatkhau();
                    if (taikhoan.getText().toString().equals(taikhoanxacthuc.toString())&&matkhau.getText().toString().equals(matkhauxacthuc.toString())) {
                        Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent x = new Intent(LoginActivity.this, ThongbaoActivity.class);
                        x.putExtra("tentk", taikhoan.getText().toString());
                        startActivity(x);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }

//finish 15/7

            }
        });
    }
}
