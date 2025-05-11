package com.example.demo1.controllers;

import com.example.demo1.models.LibraryCard;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;

public class LibraryCardController {

    @FXML
    private TextField maTheField;
    @FXML
    private TextField maDocGiaField;
    @FXML
    private DatePicker ngayCapDatePicker;
    @FXML
    private TableView<LibraryCard> libraryCardTable;
    @FXML
    private TableColumn<LibraryCard, String> maTheColumn;
    @FXML
    private TableColumn<LibraryCard, String> maDocGiaColumn;
    @FXML
    private TableColumn<LibraryCard, LocalDate> ngayCapColumn;

    private ObservableList<LibraryCard> libraryCardList = FXCollections.observableArrayList();

    // Add library card

    @FXML
    public void addLibraryCard() {
        String maThe = maTheField.getText();
        String maDocGia = maDocGiaField.getText();
        LocalDate ngayCap = ngayCapDatePicker.getValue();

        if (maThe.isEmpty() || maDocGia.isEmpty() || ngayCap == null) {
            showAlert("Lỗi", "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        LibraryCard newLibraryCard = new LibraryCard(maThe, maDocGia, ngayCap);
        DataStore.addLibraryCard(newLibraryCard); // Save to database using DataStore
        clearForm();
        updateTable();
    }


    // Update library card
    @FXML
    public void updateLibraryCard() {
        LibraryCard selectedCard = libraryCardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            selectedCard.setMaThe(maTheField.getText());
            selectedCard.setMaDocGia(maDocGiaField.getText());
            selectedCard.setNgayCap(ngayCapDatePicker.getValue());

            DataStore.updateLibraryCard(selectedCard); // Update in the database using DataStore
            updateTable();
            clearForm();
        } else {
            showAlert("Lỗi", "Vui lòng chọn một thẻ thư viện để sửa!");
        }
    }


    // Delete library card
    @FXML
    public void deleteLibraryCard() {
        LibraryCard selectedCard = libraryCardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            DataStore.deleteLibraryCard(selectedCard.getMaThe()); // Delete from database using DataStore
            libraryCardList.remove(selectedCard);  // Remove from the list used in UI
            updateTable();  // Ensure the table is updated after deletion
            clearForm();  // Clear the form
        } else {
            showAlert("Lỗi", "Vui lòng chọn một thẻ thư viện để xóa!");
        }
    }


    // Clear form fields
    @FXML
    public void clearForm() {
        maTheField.clear();
        maDocGiaField.clear();
        ngayCapDatePicker.setValue(null);
    }

    // Update table
    private void updateTable() {
        libraryCardList.setAll(DataStore.getAllLibraryCards()); // Fetch updated list from DataStore
        libraryCardTable.setItems(libraryCardList);
    }

    // Show alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Handle table click event to display selected library card information
    @FXML
    public void handleTableClick(MouseEvent event) {
        LibraryCard selectedCard = libraryCardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            maTheField.setText(selectedCard.getMaThe());
            maDocGiaField.setText(selectedCard.getMaDocGia());
            ngayCapDatePicker.setValue(selectedCard.getNgayCap());
        }
    }

    // Initialize table and load data
    @FXML
    public void initialize() {
        maTheColumn.setCellValueFactory(cellData -> cellData.getValue().maTheProperty());
        maDocGiaColumn.setCellValueFactory(cellData -> cellData.getValue().maDocGiaProperty());
        ngayCapColumn.setCellValueFactory(cellData -> cellData.getValue().ngayCapProperty());

        updateTable(); // Load data into table on initialization
    }

}
