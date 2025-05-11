package com.example.demo1.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class LendingSlip {

    private final IntegerProperty maPhieuMuon;
    private final StringProperty docGia;
    private final StringProperty sach;
    private final IntegerProperty soLuong;
    private final ObjectProperty<LocalDate> ngayMuon;
    private final ObjectProperty<LocalDate> hanTra;

    public LendingSlip(int maPhieuMuon, String docGia, String sach, int soLuong, LocalDate ngayMuon, LocalDate hanTra) {
        this.maPhieuMuon = new SimpleIntegerProperty(maPhieuMuon);
        this.docGia = new SimpleStringProperty(docGia);
        this.sach = new SimpleStringProperty(sach);
        this.soLuong = new SimpleIntegerProperty(soLuong);
        this.ngayMuon = new SimpleObjectProperty<>(ngayMuon);
        this.hanTra = new SimpleObjectProperty<>(hanTra);
    }

    public LendingSlip() {
        this(0, "", "", 0, LocalDate.now(), LocalDate.now());
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon.get();
    }

    public void setMaPhieuMuon(int value) {
        maPhieuMuon.set(value);
    }

    public IntegerProperty maPhieuMuonProperty() {
        return maPhieuMuon;
    }

    public String getDocGia() {
        return docGia.get();
    }

    public void setDocGia(String value) {
        docGia.set(value);
    }

    public StringProperty docGiaProperty() {
        return docGia;
    }

    public String getSach() {
        return sach.get();
    }

    public void setSach(String value) {
        sach.set(value);
    }

    public StringProperty sachProperty() {
        return sach;
    }

    public int getSoLuong() {
        return soLuong.get();
    }

    public void setSoLuong(int value) {
        soLuong.set(value);
    }

    public IntegerProperty soLuongProperty() {
        return soLuong;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon.get();
    }

    public void setNgayMuon(LocalDate value) {
        ngayMuon.set(value);
    }

    public ObjectProperty<LocalDate> ngayMuonProperty() {
        return ngayMuon;
    }

    public LocalDate getHanTra() {
        return hanTra.get();
    }

    public void setHanTra(LocalDate value) {
        hanTra.set(value);
    }

    public ObjectProperty<LocalDate> hanTraProperty() {
        return hanTra;
    }

    @Override
    public String toString() {
        return "LendingSlip{" +
                "maPhieuMuon=" + getMaPhieuMuon() +
                ", docGia='" + getDocGia() + '\'' +
                ", sach='" + getSach() + '\'' +
                ", soLuong=" + getSoLuong() +
                ", ngayMuon=" + getNgayMuon() +
                ", hanTra=" + getHanTra() +
                '}';
    }
}
