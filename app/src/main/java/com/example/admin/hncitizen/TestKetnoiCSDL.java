package com.example.admin.hncitizen;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TestKetnoiCSDL extends AppCompatActivity implements Runnable{
    ArrayList<tkNguoidan>list;
    int Id;
    String taikhoan,matkhau,diachi,sodienthoai,hoten;
    int count = 0;
    Connection con ;
    KetnoiData kc=new KetnoiData();
    private String errmsg="";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textView0);
        Thread thread = new Thread(this);
        thread.start(); //khi chạy thành công trên màn hình giả lập sẻ hiện thị kết nối thành công vs database
    }

    public void run() {

        con=kc.ketnoi();
        list=new ArrayList<>();
try {
    String sql;
    sql = "SELECT * FROM taikhoankh";
    PreparedStatement prest = con.prepareStatement(sql);
    ResultSet rs = prest.executeQuery();
    while (rs.next()) {
        tkNguoidan th= new tkNguoidan();
        Id = rs.getInt("idtaikhoankh");
       taikhoan=rs.getString("taikhoan");
       matkhau=rs.getString("matkhau");
       diachi=rs.getString("diachi");
       sodienthoai=rs.getString("sodienthoai");
       hoten=rs.getString("hoten");

        list.add(th);
    }
    prest.close();
    con.close();
}
catch (Exception e)
{
    e.printStackTrace();
}

        mIncomingHandler.sendEmptyMessage(0);
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback()
    {
        public boolean handleMessage(Message msg) {
            if (list!=null)
            {
               // textView.setText("ket noi thanh cong");
                textView.setText(Id+taikhoan+matkhau+hoten+diachi+sodienthoai);
            }
            else
            {
               textView.setText("ket noi that cmn bai");
            }

            return true;
        }
    });
}

