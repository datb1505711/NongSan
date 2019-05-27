package thanhdat.example.nongsan;

import java.time.LocalDate;
import java.util.Date;

public class Baiban {
    private String tieude;
    private String tinhthanh;
    private String danhmucsanpham;
    private String motasanpham;
    private String giasanpham;
    private String idhinhanhsanpham;
    private String loaigiasanpham;
    private String ngaydangbaiviet;
    private String idnguoiviet;

    public Baiban(String tieude, String tinhthanh, String danhmucsanpham, String motasanpham, String giasanpham, String idhinhanhsanpham, String loaigiasanpham, String ngaydangbaiviet, String idnguoiviet) {
        this.tieude = tieude;
        this.tinhthanh = tinhthanh;
        this.danhmucsanpham = danhmucsanpham;
        this.motasanpham = motasanpham;
        this.giasanpham = giasanpham;
        this.idhinhanhsanpham = idhinhanhsanpham;
        this.loaigiasanpham = loaigiasanpham;
        this.ngaydangbaiviet = ngaydangbaiviet;
        this.idnguoiviet = idnguoiviet;
    }

    public String getIdnguoiviet() {
        return idnguoiviet;
    }

    public void setIdnguoiviet(String idnguoiviet) {
        this.idnguoiviet = idnguoiviet;
    }

    public String getIdhinhanhsanpham() {
        return idhinhanhsanpham;
    }

    public void setIdhinhanhsanpham(String idhinhanhsanpham) {
        this.idhinhanhsanpham = idhinhanhsanpham;
    }


    public Baiban() {
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getTinhthanh() {
        return tinhthanh;
    }

    public void setTinhthanh(String tinhthanh) {
        this.tinhthanh = tinhthanh;
    }

    public String getDanhmucsanpham() {
        return danhmucsanpham;
    }

    public void setDanhmucsanpham(String danhmucsanpham) {
        this.danhmucsanpham = danhmucsanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getLoaigiasanpham() {
        return loaigiasanpham;
    }

    public void setLoaigiasanpham(String loaigiasanpham) {
        this.loaigiasanpham = loaigiasanpham;
    }

    public String getNgaydangbaiviet() {
        return ngaydangbaiviet;
    }

    public void setNgaydangbaiviet(String ngaydangbaiviet) {
        this.ngaydangbaiviet = ngaydangbaiviet;
    }
}
