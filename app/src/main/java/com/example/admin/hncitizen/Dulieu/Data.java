package com.example.admin.hncitizen.Dulieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.hncitizen.Doituong.Cauhoi;
import com.example.admin.hncitizen.Doituong.Thongbao;
import com.example.admin.hncitizen.Doituong.tkNguoidan;
import com.example.admin.hncitizen.RegisterActivity;

import java.util.ArrayList;

public class Data extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "Hanoicitizen";
    //Nametable
    private static final String TABLE_NAME1 = "taikhoanNguoidan";
    private static final String TABLE_NAME2 = "Thongbao";
    private static final String TABLE_NAME3 = "Cauhoi";
    //Thongbao
    private static final String KEY_MOTATB = "MotaThongbao";
    private static final String KEY_TOMTATTB = "TomtatThongbao";
    private static final String KEY_NOIDUNGTB = "NoidungThongbao";
    private static final String KEY_ANHTB = "AnhThongbao";
    private static final String KEY_NGAYTB = "NgayThongbao";
    //Cauhoi
    private static final String KEY_NOIDUNGCH = "NoidungCauhoi";
    private static final String KEY_TAIKHOANCH = "TaikhoanCauhoi";
    private static final String KEY_NGAYGUICAUHOI = "NgayguiCauhoi";
    private static final String KEY_ANHCAUHOI = "AnhCauhoi";
    //tkNguoidan
    private static final String KEY_ID = "Id";
    private static final String KEY_TAIKHOAN = "Taikhoan";
    private static final String KEY_MATKHAU = "Matkhau";
    private static final String KEY_DIACHI = "Diachi";
    private static final String KEY_SODIENTHOAI = "Sodienthoai";
    private static final String KEY_HOTEN = "Hoten";

    //
    public Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE1 = "CREATE TABLE " + TABLE_NAME1 + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TAIKHOAN + " TEXT," + KEY_MATKHAU
                + " TEXT," + KEY_DIACHI + " TEXT," + KEY_SODIENTHOAI + " TEXT," + KEY_HOTEN + " TEXT" + ")";

        String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_MOTATB + " TEXT," + KEY_TOMTATTB
                + " TEXT," + KEY_NOIDUNGTB + " TEXT," + KEY_ANHTB + " TEXT," + KEY_NGAYTB+ " TEXT" + ")";

        String CREATE_TABLE3 = "CREATE TABLE " + TABLE_NAME3 + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOIDUNGCH + " TEXT," + KEY_TAIKHOANCH + " TEXT," + KEY_ANHCAUHOI+ " TEXT" + ")";

        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }

    ///
    public void addS(tkNguoidan tk) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TAIKHOAN, tk.getTaikhoan());
        values.put(KEY_MATKHAU, tk.getMatkhau());
        values.put(KEY_DIACHI, tk.getDiachi());
        values.put(KEY_SODIENTHOAI, tk.getSodienthoai());
        values.put(KEY_HOTEN, tk.getHoten());
        db.insert(TABLE_NAME1, null, values);
        db.close();

    }
    public void addch(Cauhoi tk) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOIDUNGCH, tk.getNoidungCauhoi());
        values.put(KEY_TAIKHOANCH, tk.getTaikhoanNguoidan());
        values.put(KEY_ANHCAUHOI, tk.getAnhCauhoi());
        db.insert(TABLE_NAME3, null, values);
        db.close();

    }
    public void addtb(Thongbao tk) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_MOTATB, tk.getMotaThongbao());
        values.put(KEY_TOMTATTB, tk.getTomtatThongbao());
        values.put(KEY_NOIDUNGTB, tk.getNoidungThongbao());
        values.put(KEY_ANHTB, tk.getAnhThongbao());
        values.put(KEY_NGAYTB,tk.getNgayThongbao());
        db.insert(TABLE_NAME2, null, values);
        db.close();

    }
    public ArrayList<tkNguoidan> gettk() {
        ArrayList<tkNguoidan> lists = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                tkNguoidan tk = new tkNguoidan();
                tk.setId(cursor.getInt(0));
                tk.setTaikhoan(cursor.getString(1));
                tk.setMatkhau(cursor.getString(2));
                tk.setDiachi(cursor.getString(3));
                tk.setSodienthoai(cursor.getString(4));
                tk.setHoten(cursor.getString(5));
                lists.add(tk);

            }
            while (cursor.moveToNext());

        }
        return lists;
    }
    public ArrayList<Thongbao> gettb() {
        ArrayList<Thongbao> lists = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Thongbao tk = new Thongbao();
                tk.setIdThongbao(cursor.getInt(0));
                tk.setMotaThongbao(cursor.getString(1));
                tk.setTomtatThongbao(cursor.getString(2));
                tk.setNoidungThongbao(cursor.getString(3));
                tk.setAnhThongbao(cursor.getString(4));
                lists.add(tk);

            }
            while (cursor.moveToNext());

        }
        return lists;
    }
}
