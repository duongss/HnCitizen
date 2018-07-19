package com.example.admin.hncitizen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.hncitizen.Doituong.Cauhoi;
import com.example.admin.hncitizen.Dulieu.Data;

public class GuichActivity extends AppCompatActivity {
    TextView taikhoannguoigui;
    EditText nhapcauhoi;
    private Button buttonImage;
    String time;
    private ImageView imageView;
    Data db;
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    String taikhoan;
    String formatdate;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guich);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarch);
        setSupportActionBar(toolbar);
        db=new Data(GuichActivity.this);
        taikhoannguoigui = findViewById(R.id.taikhoanguitext);
        buttonImage = findViewById(R.id.btnlayanh);
        nhapcauhoi = findViewById(R.id.editnhapcauhoi);
        imageView=findViewById(R.id.imgview);
     //   taikhoannguoigui.setVisibility(View.GONE);
        TextClock textClock = findViewById(R.id.textclock);
        formatdate = "E,d-M-yyyy ";
        textClock.setFormat24Hour(formatdate);

        time =textClock.toString();
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
                Toast.makeText(this, "Gui cau hoi", Toast.LENGTH_SHORT).show();
                Cauhoi cauhoi = new Cauhoi();
                cauhoi.setNoidungCauhoi(nhapcauhoi.getText().toString());
                cauhoi.setTaikhoanNguoidan(taikhoan);
                cauhoi.setAnhCauhoi(imageView.toString());

                db.addch(cauhoi);
                Toast.makeText(this, "Đã gửi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GuichActivity.this,ThongbaoActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
public void layanh()
{
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }
}
