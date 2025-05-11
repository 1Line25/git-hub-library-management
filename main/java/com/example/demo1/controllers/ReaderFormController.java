package com.example.demo1.controllers;

import com.example.demo1.models.Reader;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class ReaderFormController {

    // FXML fields
    @FXML private TextField maDocGiaField;
    @FXML private TextField tenDocGiaField;
    @FXML private TextField diaChiField;
    @FXML private TextField soDienThoaiField;

    @FXML private TableView<Reader> readerTable;
    @FXML private TableColumn<Reader, String> maDocGiaColumn;
    @FXML private TableColumn<Reader, String> tenDocGiaColumn;
    @FXML private TableColumn<Reader, String> diaChiColumn;
    @FXML private TableColumn<Reader, String> soDienThoaiColumn;

    @FXML private Button themButton;
    @FXML private Button suaButton;
    @FXML private Button xoaButton;

    private final ObservableList<Reader> readerList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Set table columns to use model properties
        maDocGiaColumn.setCellValueFactory(cellData -> cellData.getValue().maDocGiaProperty());
        tenDocGiaColumn.setCellValueFactory(cellData -> cellData.getValue().tenDocGiaProperty());
        diaChiColumn.setCellValueFactory(cellData -> cellData.getValue().diaChiProperty());
        soDienThoaiColumn.setCellValueFactory(cellData -> cellData.getValue().soDienThoaiProperty());

        // Load data and set to table
        readerTable.setItems(readerList);
        loadReadersFromDataStore();

        // Event on row click
        readerTable.setOnMouseClicked(this::handleTableClick);
    }

    // Load all readers from DataStore
    private void loadReadersFromDataStore() {
        readerList.setAll(DataStore.getAllReaders());
    }

    // Add a new reader
    @FXML
    private void addReader() {
        String maDocGia = maDocGiaField.getText().trim();
        String tenDocGia = tenDocGiaField.getText().trim();
        String diaChi = diaChiField.getText().trim();
        String soDienThoai = soDienThoaiField.getText().trim();

        if (maDocGia.isEmpty() || tenDocGia.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin.");
            return;
        }

        // Check if reader already exists based on maDocGia
        if (DataStore.getReader(maDocGia) != null) {
            showAlert("Lỗi", "Mã độc giả đã tồn tại.");
            return;
        }

        Reader newReader = new Reader(maDocGia, tenDocGia, diaChi, soDienThoai);
        DataStore.addReader(newReader);
        readerList.add(newReader);
        clearForm();
        readerTable.refresh();
    }

    // Update an existing reader
    @FXML
    private void updateReader() {
        Reader selectedReader = readerTable.getSelectionModel().getSelectedItem();

        if (selectedReader == null) {
            showAlert("Thông báo", "Vui lòng chọn một độc giả để sửa.");
            return;
        }

        // Validate form fields
        String maDocGia = maDocGiaField.getText().trim();
        String tenDocGia = tenDocGiaField.getText().trim();
        String diaChi = diaChiField.getText().trim();
        String soDienThoai = soDienThoaiField.getText().trim();

        if (maDocGia.isEmpty() || tenDocGia.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin.");
            return;
        }

        selectedReader.setMaDocGia(maDocGia);
        selectedReader.setTenDocGia(tenDocGia);
        selectedReader.setDiaChi(diaChi);
        selectedReader.setSoDienThoai(soDienThoai);

        // Update the reader in DataStore
        DataStore.updateReader(selectedReader);

        // Refresh table view
        readerTable.refresh();
        clearForm();
    }

    // Delete selected reader
    @FXML
    private void deleteReader() {
        Reader selectedReader = readerTable.getSelectionModel().getSelectedItem();

        if (selectedReader == null) {
            showAlert("Thông báo", "Vui lòng chọn một độc giả để xóa.");
            return;
        }

        // Confirm deletion
        Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDeleteAlert.setTitle("Xóa độc giả");
        confirmDeleteAlert.setHeaderText("Bạn có chắc chắn muốn xóa độc giả này?");
        confirmDeleteAlert.setContentText(selectedReader.getTenDocGia());
        confirmDeleteAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Delete the reader from DataStore using the unique identifier (maDocGia)
                DataStore.deleteReader(selectedReader.getMaDocGia());

                // Remove the reader from the table list
                readerList.remove(selectedReader);
                clearForm();
            }
        });
    }

    // When a row is clicked, fill form with data
    private void handleTableClick(MouseEvent event) {
        Reader selectedReader = readerTable.getSelectionModel().getSelectedItem();
        if (selectedReader != null) {
            maDocGiaField.setText(selectedReader.getMaDocGia());
            tenDocGiaField.setText(selectedReader.getTenDocGia());
            diaChiField.setText(selectedReader.getDiaChi());
            soDienThoaiField.setText(selectedReader.getSoDienThoai());
        }
    }

    // Clear form fields
    private void clearForm() {
        maDocGiaField.clear();
        tenDocGiaField.clear();
        diaChiField.clear();
        soDienThoaiField.clear();
    }

    // Show alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
