package thanhdat.example.nongsan;

import android.net.Uri;

import java.util.Date;

public class User {
    public User() {
    }


    public User(String password, String hoten, String sdt, String ngaysinh, String diachi, String gioitinh, String tencongty, String diachicongty, String sdtcongty, String webcongty, String avatar) {
        this.password = password;
        this.hoten = hoten;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.tencongty = tencongty;
        this.diachicongty = diachicongty;
        this.sdtcongty = sdtcongty;
        this.webcongty = webcongty;
        this.avatar = avatar;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getTencongty() {
        return tencongty;
    }

    public void setTencongty(String tencongty) {
        this.tencongty = tencongty;
    }

    public String getDiachicongty() {
        return diachicongty;
    }

    public void setDiachicongty(String diachicongty) {
        this.diachicongty = diachicongty;
    }

    public String getSdtcongty() {
        return sdtcongty;
    }

    public void setSdtcongty(String sdtcongty) {
        this.sdtcongty = sdtcongty;
    }

    public String getWebcongty() {
        return webcongty;
    }

    public void setWebcongty(String webcongty) {
        this.webcongty = webcongty;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String hoten;
    private String sdt;
    private String ngaysinh;
    private String diachi;
    private String gioitinh;
    private String tencongty;
    private String diachicongty;
    private String sdtcongty;
    private String webcongty;
    private String avatar;


}
