package com.example.demo1.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Librarian {

    private final StringProperty maThuThu;
    private final StringProperty hoTen;
    private final StringProperty ngaySinh;
    private final StringProperty gioiTinh;
    private final StringProperty soDienThoai;
    private final StringProperty email;
    private final StringProperty ngayVaoLam;

    public Librarian(String maThuThu, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String email, String ngayVaoLam) {
        this.maThuThu = new SimpleStringProperty(maThuThu);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.soDienThoai = new SimpleStringProperty(soDienThoai);
        this.email = new SimpleStringProperty(email);
        this.ngayVaoLam = new SimpleStringProperty(ngayVaoLam);
    }

    // Getters and Setters for all properties
    public String getMaThuThu() {
        return maThuThu.get();
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu.set(maThuThu);
    }

    public StringProperty maThuThuProperty() {
        return maThuThu;
    }

    public String getHoTen() {
        return hoTen.get();
    }

    public void setHoTen(String hoTen) {
        this.hoTen.set(hoTen);
    }

    public StringProperty hoTenProperty() {
        return hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh.get();
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    public StringProperty ngaySinhProperty() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh.get();
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh.set(gioiTinh);
    }

    public StringProperty gioiTinhProperty() {
        return gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai.get();
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai.set(soDienThoai);
    }

    public StringProperty soDienThoaiProperty() {
        return soDienThoai;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam.get();
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam.set(ngayVaoLam);
    }

    public StringProperty ngayVaoLamProperty() {
        return ngayVaoLam;
    }
}
