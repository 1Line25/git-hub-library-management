package com.example.demo;

import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibraryUI {
    private TableView<Book> table = new TableView<>();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    public BorderPane getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Table setup
        TableColumn<Book, String> titleCol = new TableColumn<>("Tiêu đề");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> authorCol = new TableColumn<>("Tác giả");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        table.getColumns().addAll(titleCol, authorCol, isbnCol);
        table.setItems(bookList);

        // Form nhập
        TextField isbnField = new TextField();
        isbnField.setPromptText("Nhập ISBN...");
        Button fetchBtn = new Button("Lấy từ Google Books");

        fetchBtn.setOnAction(e -> {
            String isbn = isbnField.getText();
            GoogleBooksAPI.fetchBookInfo(isbn, book -> {
                bookList.add(book); // cập nhật trong JavaFX Thread
            });
        });

        HBox inputBox = new HBox(10, isbnField, fetchBtn);
        inputBox.setPadding(new Insets(10));

        root.setTop(inputBox);
        root.setCenter(table);
        return root;
    }
}
