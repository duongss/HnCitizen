package com.example.admin.hncitizen.Doituong;

public class Thongbao {
    int idThongbao;
    String motaThongbao,tomtatThongbao,noidungThongbao,anhThongbao,ngayThongbao;

    public Thongbao(int idThongbao, String motaThongbao, String tomtatThongbao, String noidungThongbao) {
        this.idThongbao = idThongbao;
        this.motaThongbao = motaThongbao;
        this.tomtatThongbao = tomtatThongbao;
        this.noidungThongbao = noidungThongbao;
    }

    public Thongbao(String motaThongbao, String tomtatThongbao, String noidungThongbao) {
        this.motaThongbao = motaThongbao;
        this.tomtatThongbao = tomtatThongbao;
        this.noidungThongbao = noidungThongbao;
    }

    public String getNgayThongbao() {
        return ngayThongbao;
    }

    public void setNgayThongbao(String ngayThongbao) {
        this.ngayThongbao = ngayThongbao;
    }

    public Thongbao()
{}
    public String getAnhThongbao() {
        return anhThongbao;
    }

    public void setAnhThongbao(String anhThongbao) {
        this.anhThongbao = anhThongbao;
    }

    public int getIdThongbao() {
        return idThongbao;
    }

    public void setIdThongbao(int idThongbao) {
        this.idThongbao = idThongbao;
    }

    public String getMotaThongbao() {
        return motaThongbao;
    }

    public void setMotaThongbao(String motaThongbao) {
        this.motaThongbao = motaThongbao;
    }

    public String getTomtatThongbao() {
        return tomtatThongbao;
    }

    public void setTomtatThongbao(String tomtatThongbao) {
        this.tomtatThongbao = tomtatThongbao;
    }

    public String getNoidungThongbao() {
        return noidungThongbao;
    }

    public void setNoidungThongbao(String noidungThongbao) {
        this.noidungThongbao = noidungThongbao;
    }
}
