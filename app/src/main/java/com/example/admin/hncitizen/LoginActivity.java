package com.example.admin.hncitizen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.hncitizen.Dulieu.Data;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<tkNguoidan> Listtk;
    Button dangnhapbt,dangkybt;
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

    public void Xacnhantk() {
        Listtk = db.gettk();
    }

    public void Dangnhap() {
        taikhoan = findViewById(R.id.tkhoan);
        matkhau = findViewById(R.id.mkhau);
        dangnhapbt = findViewById(R.id.Dangnhap);
        dangkybt=findViewById(R.id.DangkyN);
        dangkybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        dangnhapbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xacnhantk();

for(tkNguoidan c:Listtk)
{
    taikhoanxacthuc=c.getTaikhoan();
    matkhauxacthuc=c.getMatkhau();

                if (taikhoan.getText().toString().equals(taikhoanxacthuc)&&matkhau.getText().toString().equals(matkhauxacthuc)) {
                    Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                    break;
                }
//finish 15/7
}
            }
        });
    }
}
