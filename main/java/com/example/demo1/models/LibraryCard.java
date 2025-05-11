package com.example.demo1.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class LibraryCard {

    private final StringProperty maThe;
    private final StringProperty maDocGia;
    private final ObjectProperty<LocalDate> ngayCap;

    public LibraryCard(String maThe, String maDocGia, LocalDate ngayCap) {
        this.maThe = new SimpleStringProperty(maThe);
        this.maDocGia = new SimpleStringProperty(maDocGia);
        this.ngayCap = new SimpleObjectProperty<>(ngayCap);
    }

    // Getter và Setter cho maThe
    public String getMaThe() {
        return maThe.get();
    }

    public void setMaThe(String maThe) {
        this.maThe.set(maThe);
    }

    public StringProperty maTheProperty() {
        return maThe;
    }

    // Getter và Setter cho maDocGia
    public String getMaDocGia() {
        return maDocGia.get();
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia.set(maDocGia);
    }

    public StringProperty maDocGiaProperty() {
        return maDocGia;
    }

    // Getter và Setter cho ngayCap
    public LocalDate getNgayCap() {
        return ngayCap.get();
    }

    public void setNgayCap(LocalDate ngayCap) {
        this.ngayCap.set(ngayCap);
    }

    public ObjectProperty<LocalDate> ngayCapProperty() {
        return ngayCap;
    }

    @Override
    public String toString() {
        return "LibraryCard{" +
                "maThe='" + maThe + '\'' +
                ", maDocGia='" + maDocGia + '\'' +
                ", ngayCap=" + ngayCap +
                '}';
    }
}
