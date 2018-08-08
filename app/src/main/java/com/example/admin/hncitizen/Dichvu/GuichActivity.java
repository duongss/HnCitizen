package com.example.admin.hncitizen.Dichvu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.hncitizen.Doituong.Cauhoi;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;
import com.example.admin.hncitizen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GuichActivity extends AppCompatActivity {
    TextView taikhoannguoigui, time;
    EditText nhapcauhoi;
    private Button buttonImage;
    private ImageView imageView;
    Data db;
    Connection con;
    KetnoiData kc = new KetnoiData();
    String taikhoan;
    TextClock textClock;
    String h,date, url = "http://192.168.0.102/guicauhoi.php";
    public static final String TAG = GuichActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guich);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarch);
        setSupportActionBar(toolbar);
        db = new Data(GuichActivity.this);
        taikhoannguoigui = findViewById(R.id.taikhoanguitext);
        buttonImage = findViewById(R.id.btnlayanh);
        nhapcauhoi = findViewById(R.id.editnhapcauhoi);
        imageView = findViewById(R.id.imgview);
        time = findViewById(R.id.textclock);

        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        date = formatter.format(today);
        time.setText(date);
        h = String.valueOf(time);
        final SharedPreferences sharedPreferences = getSharedPreferences("Myuser", Context.MODE_PRIVATE);
        taikhoan = sharedPreferences.getString("Taikhoan", "");
        taikhoannguoigui.setText(taikhoan);
        layanh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guich, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionsendCH:
                ArrayList<Cauhoi> ch = new ArrayList<>();
                Toast.makeText(this, "Gui cau hoi", Toast.LENGTH_SHORT).show();
                Cauhoi cauhoi = new Cauhoi();
                cauhoi.setNoidungCauhoi(nhapcauhoi.getText().toString());
                cauhoi.setTaikhoanNguoidan(taikhoan);
                cauhoi.setNgayGuicauhoi(date);
                cauhoi.setAnhCauhoi(imageView.toString());
                //          db.addch(cauhoi);
                guiCauhoi(cauhoi);
                Toast.makeText(this, "Đã gửi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GuichActivity.this, ThongbaoActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void layanh() {
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePicture();
            }
        });
    }

    private void capturePicture() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(getApplication(), "Camera không được hỗ trợ", Toast.LENGTH_LONG).show();
        }
    }

    private void guiCauhoi(final Cauhoi ch) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                String sql = "Insert into cauhoi (`noidungCauhoi`, `taikhoanNguoidan`, `ngayGuicauhoi`, `anhCauhoi`) VALUES (?,?,?,?) ";

                try {
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setString(1, ch.getNoidungCauhoi());
                    stm.setString(2, ch.getTaikhoanNguoidan());
                    stm.setString(3, ch.getNgayGuicauhoi());
                    stm.setString(4, ch.getAnhCauhoi());

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }
}
