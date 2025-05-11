package com.example.demo1.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Author {
    private int id;
    private final IntegerProperty maTacGia; // Mã tác giả
    private final StringProperty tenTacGia; // Tên tác giả
    private final ObjectProperty<LocalDate> ngaySinh; // Ngày sinh của tác giả

    // Constructor để khởi tạo các thuộc tính
    public Author(int maTacGia, String tenTacGia, LocalDate ngaySinh) {
        this.maTacGia = new SimpleIntegerProperty(maTacGia);
        this.tenTacGia = new SimpleStringProperty(tenTacGia);
        this.ngaySinh = new SimpleObjectProperty<>(ngaySinh);
    }

    // Getter và Setter cho các thuộc tính
    public int getMaTacGia() {
        return maTacGia.get();
    }

    public IntegerProperty maTacGiaProperty() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia.set(maTacGia);
    }

    public String getTenTacGia() {
        return tenTacGia.get();
    }

    public StringProperty tenTacGiaProperty() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia.set(tenTacGia);
    }

    public LocalDate getNgaySinh() {
        return ngaySinh.get();
    }

    public ObjectProperty<LocalDate> ngaySinhProperty() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }
    public int getId() {
        return id;
    }
}
