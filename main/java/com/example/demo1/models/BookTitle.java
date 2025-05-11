package com.example.demo1.models;

import javafx.beans.property.*;

public class BookTitle {
    private final IntegerProperty id;
    private final StringProperty tenSach;
    private final StringProperty tacGia;
    private final StringProperty theLoai;
    private final StringProperty nxb;
    private final IntegerProperty namXB;

    public BookTitle(String tenSach, String tacGia, String theLoai, String nxb, int namXB) {
        this.id = new SimpleIntegerProperty(); // Initialize id without a specific value in the constructor
        this.tenSach = new SimpleStringProperty(tenSach);
        this.tacGia = new SimpleStringProperty(tacGia);
        this.theLoai = new SimpleStringProperty(theLoai);
        this.nxb = new SimpleStringProperty(nxb);
        this.namXB = new SimpleIntegerProperty(namXB);
    }

    // Getter and Setter methods for each attribute
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTenSach() {
        return tenSach.get();
    }

    public StringProperty tenSachProperty() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach.set(tenSach);
    }

    public String getTacGia() {
        return tacGia.get();
    }

    public StringProperty tacGiaProperty() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia.set(tacGia);
    }

    public String getTheLoai() {
        return theLoai.get();
    }

    public StringProperty theLoaiProperty() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai.set(theLoai);
    }

    public String getNxb() {
        return nxb.get();
    }

    public StringProperty nxbProperty() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb.set(nxb);
    }

    public int getNamXB() {
        return namXB.get();
    }

    public IntegerProperty namXBProperty() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB.set(namXB);
    }
}
