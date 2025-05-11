package com.example.demo1.controllers;

import com.example.demo1.models.LendingSlip;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class LendingSlipFormController {

    @FXML
    private TextField maPhieuMuonField;
    @FXML
    private ComboBox<String> docGiaComboBox;
    @FXML
    private ComboBox<String> sachComboBox;
    @FXML
    private TextField soLuongField;
    @FXML
    private DatePicker ngayMuonPicker;
    @FXML
    private DatePicker hanTraPicker;
    @FXML
    private Button themButton;
    @FXML
    private Button xoaButton;
    @FXML
    private Button lamMoiButton;
    @FXML
    private TableView<LendingSlip> lendingSlipTable;
    @FXML
    private TableColumn<LendingSlip, Integer> maPhieuMuonColumn;
    @FXML
    private TableColumn<LendingSlip, String> docGiaColumn;
    @FXML
    private TableColumn<LendingSlip, String> sachColumn;
    @FXML
    private TableColumn<LendingSlip, Integer> soLuongColumn;
    @FXML
    private TableColumn<LendingSlip, LocalDate> ngayMuonColumn;
    @FXML
    private TableColumn<LendingSlip, LocalDate> hanTraColumn;

    private ObservableList<LendingSlip> lendingSlipList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize columns for TableView
        maPhieuMuonColumn.setCellValueFactory(cellData -> cellData.getValue().maPhieuMuonProperty().asObject());
        docGiaColumn.setCellValueFactory(cellData -> cellData.getValue().docGiaProperty());
        sachColumn.setCellValueFactory(cellData -> cellData.getValue().sachProperty());
        soLuongColumn.setCellValueFactory(cellData -> cellData.getValue().soLuongProperty().asObject());
        ngayMuonColumn.setCellValueFactory(cellData -> cellData.getValue().ngayMuonProperty());
        hanTraColumn.setCellValueFactory(cellData -> cellData.getValue().hanTraProperty());

        // Load lending slips from the DataStore
        loadLendingSlips();
        loadComboBoxes();
    }

    private void loadLendingSlips() {
        lendingSlipList.setAll(DataStore.getAllLendingSlips());
        lendingSlipTable.setItems(lendingSlipList);
    }

    private void loadComboBoxes() {
        // Sample data, replace with actual data from database or DataStore
        docGiaComboBox.setItems(FXCollections.observableArrayList("Độc giả 1", "Độc giả 2", "Độc giả 3"));
        sachComboBox.setItems(FXCollections.observableArrayList("Sách 1", "Sách 2", "Sách 3"));
    }

    @FXML
    private void addLendingSlip() {
        String docGia = docGiaComboBox.getValue();
        String sach = sachComboBox.getValue();
        int soLuong = Integer.parseInt(soLuongField.getText());
        LocalDate ngayMuon = ngayMuonPicker.getValue();
        LocalDate hanTra = hanTraPicker.getValue();

        LendingSlip newLendingSlip = new LendingSlip(0, docGia, sach, soLuong, ngayMuon, hanTra);
        DataStore.addLendingSlip(newLendingSlip);  // Add the lending slip to the database

        loadLendingSlips();  // Reload the table
    }

    @FXML
    private void deleteLendingSlip() {
        LendingSlip selectedLendingSlip = lendingSlipTable.getSelectionModel().getSelectedItem();
        if (selectedLendingSlip != null) {
            DataStore.deleteLendingSlip(selectedLendingSlip.getMaPhieuMuon());  // Delete from database
            loadLendingSlips();  // Reload the table
        }
    }

    @FXML
    private void lamMoiButtonAction(MouseEvent event) {
        // Clear all fields for a fresh start
        maPhieuMuonField.clear();
        docGiaComboBox.getSelectionModel().clearSelection();
        sachComboBox.getSelectionModel().clearSelection();
        soLuongField.clear();
        ngayMuonPicker.setValue(null);
        hanTraPicker.setValue(null);
    }
}
