package com.example.admin.hncitizen.Dichvu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.hncitizen.Dichvu.Adapter.AdapterBinhluan;
import com.example.admin.hncitizen.Doituong.Binhluan;
import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;
import com.example.admin.hncitizen.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BinhluanActivity extends AppCompatActivity {
    AdapterBinhluan arrayAdapter;
    ArrayList<Binhluan> binhluanArrayList;
    ListView listViewbl;
    EditText textbl;
    TextView noidungtb, time,zz;
    tkNguoidan tkNguoidan;
    Thongbao thongbao;
    ImageView image;
    Connection con;
    KetnoiData kc = new KetnoiData();
    Binhluan binhluan;
    String date;
    Data db;
    int idtb = 0, idtrangthai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binhluan);
        listViewbl = findViewById(R.id.listviewbl);
        image = findViewById(R.id.dangbinhluan);
        db = new Data(BinhluanActivity.this);
        zz=findViewById(R.id.zz);
        textbl = findViewById(R.id.textbl);
        noidungtb = findViewById(R.id.noidungtbtxt);
        final SharedPreferences sharedPreferences = getSharedPreferences("Myuser", Context.MODE_PRIVATE);
        final String taikhoan = sharedPreferences.getString("Taikhoan", "");
        final String noidungtbf = sharedPreferences.getString("noidungtb", "");
        idtb = sharedPreferences.getInt("idtb", 0);
        idtrangthai = sharedPreferences.getInt("idtrangthai", 0);
        noidungtb.setText(noidungtbf);
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        date = formatter.format(today);

        suatb();
        //
        hienthibl();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binhluanArrayList = new ArrayList<>();
                binhluan = new Binhluan();
                binhluan.setBinhluan(textbl.getText().toString());
                binhluan.setTkNguoidan(taikhoan);
                binhluan.setDate("(" + date + ")");
                binhluan.setIdThongbao(idtb);
                //               db.addbl(binhluan);
                //               binhluanArrayList = db.getbl(idtb);
                guibl(binhluan);
                textbl.setText("");
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                hienthibl();

            }
        });

    }

    private void Cauhinhlist() {
        arrayAdapter = new AdapterBinhluan(getLayoutInflater(), binhluanArrayList);
        listViewbl.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    private void suatb() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Connection con;
                    String sql;
                    sql = "UPDATE thongbao SET trangthai=? WHERE idthongbao=?";
                    con = kc.ketnoi();
                    PreparedStatement prest = con.prepareStatement(sql);
                    prest.setInt(2, idtb);
                    prest.setInt(1, idtrangthai);
                    prest.executeUpdate();
                    mIncomingHandler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();
    }

    private void guibl(final Binhluan ch) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                String sql = "Insert into binhluan  (`binhluan`, `taikhoanNguoidan`, `date`, `Idtb`) VALUES (?,?,?,?); ";

                try {
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setString(1, ch.getBinhluan());
                    stm.setString(2, ch.getTkNguoidan());
                    stm.setString(3, ch.getDate());
                    stm.setInt(4, ch.getIdThongbao());
                    stm.executeUpdate();
                    mIncomingHandler.sendEmptyMessage(0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    protected void hienthibl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                binhluanArrayList = new ArrayList<>();
                try {

                    String sql;
                    sql = "SELECT * FROM binhluan WHERE Idtb = ?";
                    PreparedStatement prest = con.prepareStatement(sql);
                    prest.setInt(1, idtb);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        Binhluan tb = new Binhluan();

                            tb.setBinhluan(rs.getString("binhluan"));
                            tb.setTkNguoidan(rs.getString("taikhoanNguoidan"));
                            tb.setDate(rs.getString("date"));
                            binhluanArrayList.add(tb);

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
            if (binhluanArrayList.size()==0)
            {
                zz.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(1200);
                    zz.setVisibility(View.VISIBLE);
                    zz.setText("Bạn có thể bình luận bên dưới");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                zz.setVisibility(View.INVISIBLE);

            }

            return true;
        }
    });


}
