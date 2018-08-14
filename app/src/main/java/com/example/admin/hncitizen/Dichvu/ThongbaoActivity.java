package com.example.admin.hncitizen.Dichvu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.hncitizen.Dichvu.Adapter.AdapterThongbao;
import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.Ketnoicsdl.KetnoiData;
import com.example.admin.hncitizen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ThongbaoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView gettaikhoan;
    private RecyclerView recyclerView;
    private AdapterThongbao adapterThongbao;
    public ArrayList<Thongbao> listtb;
    public ArrayList<tkNguoidan> listtk;
    SearchView searchView;
    Data db;
    String tentk, matkhau, sodienthoai, hoten, diachi;
    String url = "http://192.168.0.102/thongbao.php";
    String TAG = Thongbao.class.getSimpleName();
    private Handler mHandler;
    Connection con;
    KetnoiData kc = new KetnoiData();
    int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        gettaikhoan = headerview.findViewById(R.id.taikhoanZ);
        recyclerView = findViewById(R.id.listRethongbao);
        searchView = findViewById(R.id.searchviews);
        tentk = getIntent().getStringExtra("tentk");
        Addthongbao();
        hienthithongbao();
        laynguoidung();
        timkiem();
        Cauhinhlist();
        gettaikhoan.setText(tentk);
        this.mHandler = new Handler();

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
         intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ex();


    }

    public void ex() {
        this.mHandler.postDelayed(m_Runnable, 50000);
    }

    public void Addthongbao() {
        listtb = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject item = response.getJSONObject(i);

                                Thongbao thongbao = new Thongbao();
                                thongbao.setMotaThongbao(item.getString("motathongbao"));
                                thongbao.setTomtatThongbao(item.getString("tomtatthongbao"));
                                thongbao.setNoidungThongbao(item.getString("noidungthongbao"));
                                thongbao.setAnhThongbao(item.getString("anhthongbao"));
                                thongbao.setNgayThongbao(item.getString("ngaythongbao"));
                                thongbao.setTrangthai(item.getInt("trangthai"));
                                //                             db.addtb(thongbao);

                                if (listtb.size() < 8) {
                                    themthongbao(thongbao);

                                } else {
                                    Cauhinhlist();
                                }
                            }
                            adapterThongbao.notifyDataSetChanged();
                        } catch (Exception ex) {
                            Log.e(TAG, ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e(TAG, error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(ThongbaoActivity.this);
        requestQueue.add(request);
    }

    protected void themthongbao(final Thongbao tb) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                String sql = "Insert into thongbao (`trangthai`, `motaThongbao`, `tomtatThongbao`, `anhThongbao`, `ngayThongbao`, `noidungThongbao`) VALUES (?,?,?,?,?,?) ";

                try {
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setInt(1, tb.getTrangthai());
                    stm.setString(2, tb.getMotaThongbao());
                    stm.setString(3, tb.getTomtatThongbao());
                    stm.setString(4, tb.getAnhThongbao());
                    stm.setString(5, tb.getNgayThongbao());
                    stm.setString(6, tb.getNoidungThongbao());

                    stm.executeUpdate();
                    mIncomingHandler.sendEmptyMessage(0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    protected void hienthithongbao() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                listtb = new ArrayList<>();
                try {

                    String sql;
                    sql = "SELECT * FROM thongbao";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        Thongbao tb = new Thongbao();
                        tb.setIdThongbao(rs.getInt("idthongbao"));
                        tb.setTrangthai(rs.getInt("trangthai"));
                        tb.setMotaThongbao(rs.getString("motaThongbao"));
                        tb.setTomtatThongbao(rs.getString("tomtatThongbao"));
                        tb.setAnhThongbao(rs.getString("anhThongbao"));
                        tb.setNgayThongbao(rs.getString("ngayThongbao"));
                        tb.setNoidungThongbao(rs.getString("noidungThongbao"));
                        listtb.add(tb);

                        mIncomingHandler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected void laynguoidung() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                listtk = new ArrayList<>();
                try {

                    String sql;
                    sql = "SELECT * FROM taikhoankh WHERE taikhoan=?";
                    PreparedStatement prest = con.prepareStatement(sql);
                    prest.setString(1, tentk);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        tkNguoidan tb = new tkNguoidan();
                        tb.setId(rs.getInt("idtaikhoankh"));
                        tb.setMatkhau(rs.getString("matkhau"));
                        tb.setSodienthoai(rs.getString("sodienthoai"));
                        tb.setHoten(rs.getString("hoten"));
                        tb.setDiachi(rs.getString("diachi"));
                        matkhau = tb.getMatkhau();
                        sodienthoai = tb.getSodienthoai();
                        hoten = tb.getHoten();
                        diachi = tb.getDiachi();
                        listtk.add(tb);
                        SharedPreferences sharedPreferences = getSharedPreferences("Myuser", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("Taikhoan", tentk);
                        edit.putString("Matkhau", matkhau);
                        edit.putString("Sodienthoai", sodienthoai);
                        edit.putString("Hoten", hoten);
                        edit.putString("Diachi", diachi);
                        edit.apply();

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

            return true;
        }
    });


    private final Runnable m_Runnable = new Runnable() {
        public void run()

        {
            Intent intent = getIntent();
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(getIntent());
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }

    };//runnable

    private void timkiem() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterThongbao.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void Cauhinhlist() {

        adapterThongbao = new AdapterThongbao(getLayoutInflater(), listtb, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterThongbao);
        adapterThongbao.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            Collections.sort(listtb, new Sort());
            Cauhinhlist();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Sort implements Comparator<Thongbao> {

        @Override
        public int compare(Thongbao o1, Thongbao o2) {
            return o2.getNgayThongbao().compareTo(o1.getNgayThongbao());
        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_Cauhoi) {
            Intent intent = new Intent(ThongbaoActivity.this, GuichActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_thongtinkh) {
            Intent intent = new Intent(ThongbaoActivity.this, NguoidungActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_Dangxuat) {
            Intent intent = new Intent(ThongbaoActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_lichsuch) {
            Intent intent = new Intent(ThongbaoActivity.this, LichsuChActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_chiasevitri) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
