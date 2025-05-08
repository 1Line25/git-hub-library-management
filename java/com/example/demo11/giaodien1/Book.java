package com.example.demo11.giaodien1;

import java.time.LocalDate;
import javafx.scene.control.*;

public class Book {
    private String id;
    private String name;
    private String type;
    private String publisher;
    private LocalDate publishDate;
    private String authorId;
    private int quantity;
    private double price;

    // Constructor không tham số
    public Book() {
    }

    // Constructor đầy đủ tham số
    public Book(String id, String name, String type, String publisher,
                LocalDate publishDate, String authorId, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.authorId = authorId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter và Setter cho tất cả các trường
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Phương thức tạo đối tượng Book từ các input field
    public static Book fromForm(TextField id, TextField name, TextField type, TextField pub,
                                DatePicker date, TextField author, TextField qty, TextField price) {
        if (id.getText().isEmpty() || name.getText().isEmpty() || type.getText().isEmpty() ||
                pub.getText().isEmpty() || author.getText().isEmpty() || qty.getText().isEmpty() ||
                price.getText().isEmpty() || date.getValue() == null) {
            throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin!");
        }

        int quantity;
        double priceValue;

        try {
            quantity = Integer.parseInt(qty.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Số lượng phải là một số hợp lệ!");
        }

        try {
            priceValue = Double.parseDouble(price.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Giá tiền phải là một số hợp lệ!");
        }

        return new Book(
                id.getText(),
                name.getText(),
                type.getText(),
                pub.getText(),
                date.getValue(),
                author.getText(),
                quantity,
                priceValue
        );
    }
}
