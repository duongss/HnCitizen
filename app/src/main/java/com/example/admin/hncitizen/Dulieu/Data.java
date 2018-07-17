package com.example.admin.hncitizen.Dulieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.hncitizen.tkNguoidan;

import java.util.ArrayList;

public class Data extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "Hanoicitizen";
    private static final String TABLE_NAME = "taikhoanNguoidan";
    private static final String KEY_ID = "Id";
    private static final String KEY_TAIKHOAN = "Taikhoan";
    private static final String KEY_MATKHAU = "Matkhau";
    private static final String KEY_DIACHI = "Diachi";
    private static final String KEY_SODIENTHOAI = "Sodienthoai";
    private static final String KEY_HOTEN = "Hoten";
    public Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE ="CREATE TABLE "+ TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TAIKHOAN + " TEXT," + KEY_MATKHAU
                + " TEXT,"  + KEY_DIACHI + " TEXT,"  + KEY_SODIENTHOAI + " TEXT,"  + KEY_HOTEN + " TEXT"+")";
       db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    ///
    public void addS(tkNguoidan tk)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

            values.put(KEY_TAIKHOAN, tk.getTaikhoan());
            values.put(KEY_MATKHAU, tk.getMatkhau());
            values.put(KEY_DIACHI, tk.getDiachi());
            values.put(KEY_SODIENTHOAI, tk.getSodienthoai());
            values.put(KEY_HOTEN, tk.getHoten());
            db.insert(TABLE_NAME, null, values);
            db.close();

    }

    public ArrayList<tkNguoidan> gettk(){
        ArrayList<tkNguoidan> lists = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                tkNguoidan tk = new tkNguoidan();
                tk.setId(cursor.getInt(0));
                tk.setTaikhoan(cursor.getString(1));
                tk.setMatkhau(cursor.getString(2));
                tk.setDiachi(cursor.getString(3));
                tk.setSodienthoai(cursor.getString(4));
                tk.setHoten(cursor.getString(5));
                lists.add(tk);

            }
            while(cursor.moveToNext());

        }
        return lists;
    }

}
