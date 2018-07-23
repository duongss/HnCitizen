package com.example.admin.hncitizen.Dichvu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.admin.hncitizen.Dulieu.Data;
import com.example.admin.hncitizen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ThongbaoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView gettaikhoan;
    private RecyclerView recyclerView;
    private AdapterThongbao adapterThongbao;
    public ArrayList<Thongbao> listtb;
    SearchView searchView;
    Data db;
    String url = "http://192.168.0.102/thongbao.php";
    String TAG = Thongbao.class.getSimpleName();
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new Data(ThongbaoActivity.this);

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
        String tentk = getIntent().getStringExtra("tentk");
        Addthongbao();
        timkiem();
        Cauhinhlist();
        gettaikhoan.setText(tentk);
        SharedPreferences sharedPreferences = getSharedPreferences("Myuser", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("Taikhoan", tentk);
        edit.commit();
    }
    //    public void thongbao() {
//        listtb= new ArrayList<>();
//        Thongbao thongbao = new Thongbao();
//        thongbao.setMotaThongbao("ThongbaoTest");
//        thongbao.setTomtatThongbao("TomtatTest");
//        thongbao.setNoidungThongbao("NoidungTest");
//        thongbao.setAnhThongbao("http://caithuoclavn.com/upload/images/thuoc-cai-thuoc-la-nhanh.jpg");
//        thongbao.setNgayThongbao("10/2/2018");
//        db.addtb(thongbao);
//        Thongbao thongbao1 = new Thongbao();
//        thongbao1.setMotaThongbao("XTest2");
//        thongbao1.setTomtatThongbao("TomtatTest2");
//        thongbao1.setNoidungThongbao("NoidungTest2");
//        thongbao1.setAnhThongbao("http://caithuoclavn.com/upload/images/thuoc-cai-thuoc-la-nhanh.jpg");
//        thongbao1.setNgayThongbao("29/6/2018");
//        db.addtb(thongbao1);
//        Thongbao thongbao2 = new Thongbao();
//        thongbao2.setMotaThongbao("DTest3");
//        thongbao2.setTomtatThongbao("TomtatTest3");
//        thongbao2.setNoidungThongbao("NoidungTest3");
//        thongbao2.setAnhThongbao("http://caithuoclavn.com/upload/images/thuoc-cai-thuoc-la-nhanh.jpg");
//        thongbao2.setNgayThongbao("20/4/2018");
//        db.addtb(thongbao2);
//        Thongbao thongbao3 = new Thongbao();
//        thongbao3.setMotaThongbao("VTest4");
//        thongbao3.setTomtatThongbao("TomtatTest4");
//        thongbao3.setNoidungThongbao("NoidungTest4");
//        thongbao3.setAnhThongbao("http://caithuoclavn.com/upload/images/thuoc-cai-thuoc-la-nhanh.jpg");
//        thongbao3.setNgayThongbao("14/12/2018");
//        db.addtb(thongbao3);
//        listtb = db.gettb();
//
//    }
    public void Addthongbao() {
        listtb=new ArrayList<>();
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
                                db.addtb(thongbao);
                                listtb = db.gettb();
                                Cauhinhlist();
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
        } else if (id == R.id.nav_Dangxuat) {
            Intent intent = new Intent(ThongbaoActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
