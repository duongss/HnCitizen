package com.example.admin.hncitizen.Doituong;

public class Cauhoi {
    int idCauhoi;
    String noidungCauhoi,taikhoanNguoidan,ngayGuicauhoi,anhCauhoi;

    public Cauhoi(String noidungCauhoi, String taikhoanNguoidan) {
        this.noidungCauhoi = noidungCauhoi;
        this.taikhoanNguoidan = taikhoanNguoidan;
    }
public Cauhoi()
{}

    public Cauhoi(String noidungCauhoi, String taikhoanNguoidan, String anhCauhoi) {
        this.noidungCauhoi = noidungCauhoi;
        this.taikhoanNguoidan = taikhoanNguoidan;
        this.anhCauhoi = anhCauhoi;
    }

    public String getAnhCauhoi() {
        return anhCauhoi;
    }

    public void setAnhCauhoi(String anhCauhoi) {
        this.anhCauhoi = anhCauhoi;
    }

    public String getNgayGuicauhoi() {
        return ngayGuicauhoi;
    }

    public void setNgayGuicauhoi(String ngayGuicauhoi) {
        this.ngayGuicauhoi = ngayGuicauhoi;
    }

    public int getIdCauhoi() {
        return idCauhoi;
    }

    public void setIdCauhoi(int idCauhoi) {
        this.idCauhoi = idCauhoi;
    }

    public String getNoidungCauhoi() {
        return noidungCauhoi;
    }

    public void setNoidungCauhoi(String noidungCauhoi) {
        this.noidungCauhoi = noidungCauhoi;
    }

    public String getTaikhoanNguoidan() {
        return taikhoanNguoidan;
    }

    public void setTaikhoanNguoidan(String taikhoanNguoidan) {
        this.taikhoanNguoidan = taikhoanNguoidan;
    }
}
