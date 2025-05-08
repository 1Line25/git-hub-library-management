package com.example.demo.giaodien;

import java.time.LocalDate;
import javafx.scene.control.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private String type;
    private String publisher;
    private LocalDate publishDate;
    private String authorId;
    private int quantity;
    private double price;

    public static Book fromForm(TextField id, TextField name, TextField type, TextField pub,
                                DatePicker date, TextField author, TextField qty, TextField price) {
        return new Book(
                id.getText(),
                name.getText(),
                type.getText(),
                pub.getText(),
                date.getValue(),
                author.getText(),
                Integer.parseInt(qty.getText()),
                Double.parseDouble(price.getText())
        );
    }
}
