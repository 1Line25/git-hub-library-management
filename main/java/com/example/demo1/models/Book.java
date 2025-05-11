package com.example.demo1.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Book {
    private final StringProperty maSach;
    private final StringProperty tenSach;
    private final StringProperty tacGia;
    private final ObjectProperty<LocalDate> ngayXuatBan;

    // Constructor sử dụng maSach kiểu String
    public Book(String maSach, String tenSach, String tacGia, LocalDate ngayXuatBan) {
        this.maSach = new SimpleStringProperty(maSach);  // Đổi IntegerProperty thành StringProperty
        this.tenSach = new SimpleStringProperty(tenSach);
        this.tacGia = new SimpleStringProperty(tacGia);
        this.ngayXuatBan = new SimpleObjectProperty<>(ngayXuatBan);
    }

    // Getter and Setter methods for each property
    public String getMaSach() {
        return maSach.get();  // Trả về String thay vì int
    }

    public StringProperty maSachProperty() {
        return maSach;  // Trả về StringProperty
    }

    public void setMaSach(String maSach) {
        this.maSach.set(maSach);  // Cập nhật maSach là String
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

    public LocalDate getNgayXuatBan() {
        return ngayXuatBan.get();
    }

    public ObjectProperty<LocalDate> ngayXuatBanProperty() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(LocalDate ngayXuatBan) {
        this.ngayXuatBan.set(ngayXuatBan);
    }

}
