package com.example.moi.giaodien2;

import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

public class Book {
    private String id;
    private String name;
    private String type;
    private String publisher;
    private LocalDate publishDate;
    private String authorId;
    private int quantity;
    private double price;

    public Book(String id, String name, String type, String publisher, LocalDate publishDate,
                String authorId, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.authorId = authorId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Tạo Book từ form, kèm kiểm tra dữ liệu
    public static Book fromForm(TextField tfId, TextField tfName, TextField tfType, TextField tfPublisher,
                                DatePicker dpDate, TextField tfAuthor, TextField tfQuantity, TextField tfPrice) {
        try {
            String id = tfId.getText().trim();
            String name = tfName.getText().trim();
            String type = tfType.getText().trim();
            String publisher = tfPublisher.getText().trim();
            LocalDate publishDate = dpDate.getValue();
            String authorId = tfAuthor.getText().trim();
            String quantityText = tfQuantity.getText().trim();
            String priceText = tfPrice.getText().trim();

            if (id.isEmpty() || name.isEmpty() || type.isEmpty() || publisher.isEmpty() ||
                    publishDate == null || authorId.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                showAlert("Vui lòng điền đầy đủ tất cả các trường.");
                return null;
            }

            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);

            return new Book(id, name, type, publisher, publishDate, authorId, quantity, price);
        } catch (NumberFormatException e) {
            showAlert("Vui lòng nhập số hợp lệ cho số lượng và giá tiền.");
            return null;
        }
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Lỗi");
        alert.setHeaderText("Lỗi nhập dữ liệu");
        alert.showAndWait();
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
