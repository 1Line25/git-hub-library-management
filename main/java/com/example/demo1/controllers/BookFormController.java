package com.example.demo1.controllers;

import com.example.demo1.models.Book;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class BookFormController {

    @FXML
    private TextField maSachField;
    @FXML
    private TextField tenSachField;
    @FXML
    private TextField tacGiaField;
    @FXML
    private DatePicker ngayXuatBanDatePicker;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> maSachColumn;
    @FXML
    private TableColumn<Book, String> tenSachColumn;
    @FXML
    private TableColumn<Book, String> tacGiaColumn;
    @FXML
    private TableColumn<Book, LocalDate> ngayXuatBanColumn;  // Chỉnh sửa kiểu dữ liệu

    private ObservableList<Book> bookList;

    public BookFormController() {
        bookList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Set up the table columns
        maSachColumn.setCellValueFactory(cellData -> cellData.getValue().maSachProperty());
        tenSachColumn.setCellValueFactory(cellData -> cellData.getValue().tenSachProperty());
        tacGiaColumn.setCellValueFactory(cellData -> cellData.getValue().tacGiaProperty());
        ngayXuatBanColumn.setCellValueFactory(cellData -> cellData.getValue().ngayXuatBanProperty());  // Sửa kiểu dữ liệu thành LocalDate

        bookTable.setItems(bookList);

        loadBookData(); // Load book data from the database
    }

    private void loadBookData() {
        // Load the list of books from DataStore
        bookList.clear();
        bookList.addAll(DataStore.getAllBooks());
    }

    @FXML
    private void addBook() {
        String maSach = maSachField.getText();
        String tenSach = tenSachField.getText();
        String tacGia = tacGiaField.getText();
        LocalDate ngayXuatBan = ngayXuatBanDatePicker.getValue();  // Đảm bảo sử dụng LocalDate

        Book newBook = new Book(maSach, tenSach, tacGia, ngayXuatBan);  // Thay đổi kiểu maSach từ int sang String

        // Add the new book to the DataStore
        DataStore.addBook(newBook);

        bookList.clear();
        loadBookData(); // Reload data from the database
    }

    @FXML
    private void updateBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            return;
        }

        String maSach = maSachField.getText();
        String tenSach = tenSachField.getText();
        String tacGia = tacGiaField.getText();
        LocalDate ngayXuatBan = ngayXuatBanDatePicker.getValue();  // Đảm bảo sử dụng LocalDate

        selectedBook.setMaSach(maSach);  // Thay đổi kiểu maSach từ int sang String
        selectedBook.setTenSach(tenSach);
        selectedBook.setTacGia(tacGia);
        selectedBook.setNgayXuatBan(ngayXuatBan);

        // Update the book in the DataStore
        DataStore.updateBook(selectedBook);

        bookList.clear();
        loadBookData(); // Reload data from the database
    }

    @FXML
    private void deleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            return;
        }

        // Delete the selected book from the DataStore
        DataStore.deleteBook(selectedBook.getMaSach());

        bookList.remove(selectedBook); // Remove it from the local list
    }
}
