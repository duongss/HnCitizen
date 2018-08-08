package com.example.admin.hncitizen.Ketnoicsdl;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetnoiData {

    String Host="192.168.0.102";  //vào cmd run adminstration gõ ipconfig tìm DNS iv4
    String user="root";
    String pass="1234";
    String data="hanoicitizen";
    private String errmsg="";
    Connection con=null;

    public Connection ketnoi()
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + Host + ":3306/"+data+"", user, pass);
        }
        catch (Exception e){
            e.printStackTrace();
            errmsg=errmsg+e.getMessage();
        }
       return con;
    }

}
