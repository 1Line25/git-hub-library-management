package com.example.demo1.controllers;

import com.example.demo1.models.Author;
import com.example.demo1.storage.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class AuthorFormController {

    @FXML
    private TextField maTacGiaField;
    @FXML
    private TextField tenTacGiaField;
    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private Button themButton;
    @FXML
    private Button suaButton;
    @FXML
    private Button xoaButton;
    @FXML
    private TableView<Author> authorTable;
    @FXML
    private TableColumn<Author, Integer> maTacGiaColumn;
    @FXML
    private TableColumn<Author, String> tenTacGiaColumn;
    @FXML
    private TableColumn<Author, LocalDate> ngaySinhColumn;

    private ObservableList<Author> authorList = FXCollections.observableArrayList();

    @FXML
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize() {
        // Initialize columns for TableView
        maTacGiaColumn.setCellValueFactory(cellData -> cellData.getValue().maTacGiaProperty().asObject());
        tenTacGiaColumn.setCellValueFactory(cellData -> cellData.getValue().tenTacGiaProperty());
        ngaySinhColumn.setCellValueFactory(cellData -> cellData.getValue().ngaySinhProperty());

        // Load the authors from the database
        loadAuthors();
    }

    private void loadAuthors() {
        // Get authors from DataStore
        authorList.clear();
        authorList.addAll(DataStore.getAllAuthors()); // DataStore method to fetch authors
        authorTable.setItems(authorList);
    }

    @FXML
    private void addAuthor() {
        String tenTacGia = tenTacGiaField.getText();
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();

        Author newAuthor = new Author(0, tenTacGia, ngaySinh); // Assuming ID is auto-generated

        // Add the new author to the DataStore
        DataStore.addAuthor(newAuthor);

        loadAuthors();  // Reload the table
    }

    @FXML
    private void updateAuthor() {
        Author selectedAuthor = authorTable.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            String tenTacGia = tenTacGiaField.getText();
            LocalDate ngaySinh = ngaySinhDatePicker.getValue();

            selectedAuthor.setTenTacGia(tenTacGia);
            selectedAuthor.setNgaySinh(ngaySinh);

            // Update the selected author in the DataStore
            DataStore.updateAuthor(selectedAuthor);

            loadAuthors();  // Reload the table
        }
    }

    @FXML
    private void deleteAuthor() {
        Author selectedAuthor = authorTable.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            // Delete the selected author from the DataStore
            DataStore.deleteAuthor(selectedAuthor.getMaTacGia());  // Truyền vào tham số kiểu int

            loadAuthors();  // Reload the table
        }
    }


    @FXML
    private void lamMoiButtonAction(MouseEvent event) {
        // Clear all fields for a fresh start
        maTacGiaField.clear();
        tenTacGiaField.clear();
        ngaySinhDatePicker.setValue(null);
    }


}
