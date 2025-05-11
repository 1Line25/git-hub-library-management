package com.example.demo1.controllers;

import com.example.demo1.models.BookTitle;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class BookTitleFormController {

    @FXML
    private TextField tenSachField;
    @FXML
    private TextField tacGiaField;
    @FXML
    private TextField theLoaiField;
    @FXML
    private TextField nxbField;
    @FXML
    private TextField namXuatBanField;
    @FXML
    private Button themButton;
    @FXML
    private Button suaButton;
    @FXML
    private Button xoaButton;
    @FXML
    private TableView<BookTitle> bookTitleTable;
    @FXML
    private TableColumn<BookTitle, Integer> idColumn;
    @FXML
    private TableColumn<BookTitle, String> tenSachColumn;
    @FXML
    private TableColumn<BookTitle, String> tacGiaColumn;
    @FXML
    private TableColumn<BookTitle, String> theLoaiColumn;
    @FXML
    private TableColumn<BookTitle, String> nxbColumn;
    @FXML
    private TableColumn<BookTitle, Integer> namXBColumn;

    private ObservableList<BookTitle> bookTitleList;

    public BookTitleFormController() {
        bookTitleList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Initialize columns for TableView
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tenSachColumn.setCellValueFactory(cellData -> cellData.getValue().tenSachProperty());
        tacGiaColumn.setCellValueFactory(cellData -> cellData.getValue().tacGiaProperty());
        theLoaiColumn.setCellValueFactory(cellData -> cellData.getValue().theLoaiProperty());
        nxbColumn.setCellValueFactory(cellData -> cellData.getValue().nxbProperty());
        namXBColumn.setCellValueFactory(cellData -> cellData.getValue().namXBProperty().asObject());

        // Load the book titles from DataStore
        loadBookTitles();
    }

    private void loadBookTitles() {
        // Get the list of BookTitles from DataStore
        bookTitleList.clear();
        bookTitleList.addAll(DataStore.getAllBookTitles());
        bookTitleTable.setItems(bookTitleList);
    }

    @FXML
    private void themButtonAction() {
        String tenSach = tenSachField.getText();
        String tacGia = tacGiaField.getText();
        String theLoai = theLoaiField.getText();
        String nxb = nxbField.getText();
        int namXB = Integer.parseInt(namXuatBanField.getText());

        BookTitle newBookTitle = new BookTitle(tenSach, tacGia, theLoai, nxb, namXB);

        // Add the new book title to DataStore
        DataStore.addBookTitle(newBookTitle);

        loadBookTitles();  // Reload the table
    }

    @FXML
    private void suaButtonAction() {
        BookTitle selectedBook = bookTitleTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            String tenSach = tenSachField.getText();
            String tacGia = tacGiaField.getText();
            String theLoai = theLoaiField.getText();
            String nxb = nxbField.getText();
            int namXB = Integer.parseInt(namXuatBanField.getText());

            // Update the selected book title
            selectedBook.setTenSach(tenSach);
            selectedBook.setTacGia(tacGia);
            selectedBook.setTheLoai(theLoai);
            selectedBook.setNxb(nxb);
            selectedBook.setNamXB(namXB);

            // Update the book title in DataStore
            DataStore.updateBookTitle(selectedBook);

            loadBookTitles();  // Reload the table
        }
    }

    @FXML
    private void xoaButtonAction() {
        BookTitle selectedBook = bookTitleTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // Delete the selected book title from DataStore
            DataStore.deleteBookTitle(selectedBook.getId());

            loadBookTitles();  // Reload the table
        }
    }

    @FXML
    private void lamMoiButtonAction(MouseEvent event) {
        // Clear all fields for a fresh start
        tenSachField.clear();
        tacGiaField.clear();
        theLoaiField.clear();
        nxbField.clear();
        namXuatBanField.clear();
    }
}
