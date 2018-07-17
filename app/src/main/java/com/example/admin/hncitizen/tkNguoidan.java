package com.example.admin.hncitizen;

public class tkNguoidan {
    int Id;
    String taikhoan,matkhau,diachi,sodienthoai,hoten;

    public tkNguoidan(int id, String taikhoan, String matkhau, String diachi, String sodienthoai, String hoten) {
        Id = id;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.hoten = hoten;
    }

    public tkNguoidan(int id) {
        Id = id;
    }
public tkNguoidan()
{}
    public tkNguoidan(String taikhoan, String matkhau, String diachi, String sodienthoai, String hoten) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.hoten = hoten;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
