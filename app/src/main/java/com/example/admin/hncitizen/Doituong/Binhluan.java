package com.example.admin.hncitizen.Doituong;

public class Binhluan extends Thongbao {
int idBl;
String Binhluan;
String tkNguoidan;

    public Binhluan(String binhluan, String tkNguoidan) {
        Binhluan = binhluan;
        this.tkNguoidan = tkNguoidan;
    }

    @Override
    public int getIdThongbao() {
        return super.getIdThongbao();
    }

    @Override
    public void setIdThongbao(int idThongbao) {
        super.setIdThongbao(idThongbao);
    }
    public Binhluan()
    {}

    public int getIdBl() {
        return idBl;
    }

    public void setIdBl(int idBl) {
        this.idBl = idBl;
    }

    public String getBinhluan() {
        return Binhluan;
    }

    public void setBinhluan(String binhluan) {
        Binhluan = binhluan;
    }

    public String getTkNguoidan() {
        return tkNguoidan;
    }

    public void setTkNguoidan(String tkNguoidan) {
        this.tkNguoidan = tkNguoidan;
    }
}
