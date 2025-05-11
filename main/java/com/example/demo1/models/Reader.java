package com.example.demo1.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reader {
    private final StringProperty maDocGia;
    private final StringProperty tenDocGia;
    private final StringProperty diaChi;
    private final StringProperty soDienThoai;

    // Constructor
    public Reader(String maDocGia, String tenDocGia, String diaChi, String soDienThoai) {
        this.maDocGia = new SimpleStringProperty(maDocGia);
        this.tenDocGia = new SimpleStringProperty(tenDocGia);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.soDienThoai = new SimpleStringProperty(soDienThoai);
    }

    // --- JavaFX Properties ---
    public StringProperty maDocGiaProperty() {
        return maDocGia;
    }

    public StringProperty tenDocGiaProperty() {
        return tenDocGia;
    }

    public StringProperty diaChiProperty() {
        return diaChi;
    }

    public StringProperty soDienThoaiProperty() {
        return soDienThoai;
    }

    // --- Standard Getters and Setters (for JDBC/logic) ---
    public String getMaDocGia() {
        return maDocGia.get();
    }

    public void setMaDocGia(String value) {
        maDocGia.set(value);
    }

    public String getTenDocGia() {
        return tenDocGia.get();
    }

    public void setTenDocGia(String value) {
        tenDocGia.set(value);
    }

    public String getDiaChi() {
        return diaChi.get();
    }

    public void setDiaChi(String value) {
        diaChi.set(value);
    }

    public String getSoDienThoai() {
        return soDienThoai.get();
    }

    public void setSoDienThoai(String value) {
        soDienThoai.set(value);
    }

    // Optional: toString() for debugging
    @Override
    public String toString() {
        return "Reader{" +
                "maDocGia='" + getMaDocGia() + '\'' +
                ", tenDocGia='" + getTenDocGia() + '\'' +
                ", diaChi='" + getDiaChi() + '\'' +
                ", soDienThoai='" + getSoDienThoai() + '\'' +
                '}';
    }
}
