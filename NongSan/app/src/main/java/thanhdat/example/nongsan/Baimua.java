package thanhdat.example.nongsan;

import java.util.Date;

public class Baimua {
    public Baimua() {
    }

    private String tenbaimua;
    private String motabaimua;
    private String danhmucsanphammua;
    private String tinhthanhmua;
    private Date ngaydangbaimua;

    public String getTenbaimua() {
        return tenbaimua;
    }

    public void setTenbaimua(String tenbaimua) {
        this.tenbaimua = tenbaimua;
    }

    public String getMotabaimua() {
        return motabaimua;
    }

    public void setMotabaimua(String motabaimua) {
        this.motabaimua = motabaimua;
    }

    public String getDanhmucsanphammua() {
        return danhmucsanphammua;
    }

    public void setDanhmucsanphammua(String danhmucsanphammua) {
        this.danhmucsanphammua = danhmucsanphammua;
    }

    public String getTinhthanhmua() {
        return tinhthanhmua;
    }

    public void setTinhthanhmua(String tinhthanhmua) {
        this.tinhthanhmua = tinhthanhmua;
    }

    public Date getNgaydangbaimua() {
        return ngaydangbaimua;
    }

    public void setNgaydangbaimua(Date ngaydangbaimua) {
        this.ngaydangbaimua = ngaydangbaimua;
    }

    public String getGiamua() {
        return giamua;
    }

    public void setGiamua(String giamua) {
        this.giamua = giamua;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public Baimua(String tenbaimua, String motabaimua, String danhmucsanphammua, String tinhthanhmua, Date ngaydangbaimua, String giamua, String donvi) {
        this.tenbaimua = tenbaimua;
        this.motabaimua = motabaimua;
        this.danhmucsanphammua = danhmucsanphammua;
        this.tinhthanhmua = tinhthanhmua;
        this.ngaydangbaimua = ngaydangbaimua;
        this.giamua = giamua;
        this.donvi = donvi;
    }

    private String giamua;
    private String donvi;
}
