package com.example.demo1.controllers;

import com.example.demo1.models.Librarian;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class LibrarianFormController {

    @FXML
    private TextField maThuThuField;
    @FXML
    private TextField hoTenField;
    @FXML
    private DatePicker ngaySinhPicker;
    @FXML
    private ComboBox<String> gioiTinhComboBox;
    @FXML
    private TextField soDienThoaiField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker ngayVaoLamPicker;

    @FXML
    private TableView<Librarian> librarianTable;
    @FXML
    private TableColumn<Librarian, String> maThuThuColumn;
    @FXML
    private TableColumn<Librarian, String> hoTenColumn;
    @FXML
    private TableColumn<Librarian, String> ngaySinhColumn;
    @FXML
    private TableColumn<Librarian, String> gioiTinhColumn;
    @FXML
    private TableColumn<Librarian, String> soDienThoaiColumn;
    @FXML
    private TableColumn<Librarian, String> emailColumn;
    @FXML
    private TableColumn<Librarian, String> ngayVaoLamColumn;

    private ObservableList<Librarian> librarianList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize ComboBox for Gender
        gioiTinhComboBox.getItems().addAll("Nam", "Nữ");

        // Initialize columns in the TableView
        maThuThuColumn.setCellValueFactory(cellData -> cellData.getValue().maThuThuProperty());
        hoTenColumn.setCellValueFactory(cellData -> cellData.getValue().hoTenProperty());
        ngaySinhColumn.setCellValueFactory(cellData -> cellData.getValue().ngaySinhProperty());
        gioiTinhColumn.setCellValueFactory(cellData -> cellData.getValue().gioiTinhProperty());
        soDienThoaiColumn.setCellValueFactory(cellData -> cellData.getValue().soDienThoaiProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        ngayVaoLamColumn.setCellValueFactory(cellData -> cellData.getValue().ngayVaoLamProperty());

        // Load librarian data from DataStore
        loadLibrarians();
    }

    private void loadLibrarians() {
        librarianList.setAll(DataStore.getAllLibrarians());
        librarianTable.setItems(librarianList);
    }

    @FXML
    private void themButtonAction() {
        String maThuThu = maThuThuField.getText().trim();
        String hoTen = hoTenField.getText().trim();
        String ngaySinh = ngaySinhPicker.getValue() != null ? ngaySinhPicker.getValue().toString() : "";
        String gioiTinh = gioiTinhComboBox.getValue();
        String soDienThoai = soDienThoaiField.getText().trim();
        String email = emailField.getText().trim();
        String ngayVaoLam = ngayVaoLamPicker.getValue() != null ? ngayVaoLamPicker.getValue().toString() : "";

        // Validate input
        if (maThuThu.isEmpty() || hoTen.isEmpty() || gioiTinh == null || ngaySinh.isEmpty() || soDienThoai.isEmpty() || email.isEmpty() || ngayVaoLam.isEmpty()) {
            showErrorDialog("Validation Error", "All fields are required!", "Please fill in all fields.");
            return;
        }

        Librarian librarian = new Librarian(maThuThu, hoTen, ngaySinh, gioiTinh, soDienThoai, email, ngayVaoLam);
        DataStore.addLibrarian(librarian);  // Add librarian using DataStore
        loadLibrarians();  // Reload the table after insertion
    }

    @FXML
    private void xoaButtonAction() {
        Librarian selectedLibrarian = librarianTable.getSelectionModel().getSelectedItem();
        if (selectedLibrarian != null) {
            DataStore.deleteLibrarian(selectedLibrarian.getMaThuThu());  // Delete librarian using DataStore
            loadLibrarians();  // Reload the table after deletion
        } else {
            showErrorDialog("Selection Error", "No librarian selected", "Please select a librarian to delete.");
        }
    }

    @FXML
    private void lamMoiButtonAction(MouseEvent event) {
        // Clear all fields for a fresh start
        maThuThuField.clear();
        hoTenField.clear();
        ngaySinhPicker.setValue(null);
        gioiTinhComboBox.setValue(null);
        soDienThoaiField.clear();
        emailField.clear();
        ngayVaoLamPicker.setValue(null);
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
