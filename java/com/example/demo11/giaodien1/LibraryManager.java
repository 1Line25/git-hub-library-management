package com.example.demo11.giaodien1;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LibraryManager {
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    private final TableView<Book> table = new TableView<>();
    private final TextField tfId = new TextField();
    private final TextField tfName = new TextField();
    private final TextField tfType = new TextField();
    private final TextField tfPublisher = new TextField();
    private final DatePicker dpDate = new DatePicker();
    private final TextField tfAuthor = new TextField();
    private final TextField tfQuantity = new TextField();
    private final TextField tfPrice = new TextField();
    private final TextField tfSearch = new TextField();

    private final Stage stage;

    public LibraryManager(Stage stage) {
        this.stage = stage;
        setupTable();
    }

    public void show() {
        // Form nhập liệu sách
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Mã Sách:"), tfId, new Label("Ngày Xuất Bản:"), dpDate);
        form.addRow(1, new Label("Tên Sách:"), tfName, new Label("Mã Tác Giả:"), tfAuthor);
        form.addRow(2, new Label("Thể Loại:"), tfType, new Label("Số Lượng:"), tfQuantity);
        form.addRow(3, new Label("Nhà Xuất Bản:"), tfPublisher, new Label("Giá Tiền:"), tfPrice);

        // Các nút hành động
        Button btnAdd = new Button("Thêm");
        Button btnEdit = new Button("Sửa");
        Button btnDelete = new Button("Xóa");
        Button btnShow = new Button("Hiển Thị");

        HBox buttons = new HBox(10, btnAdd, btnEdit, btnDelete, btnShow);
        buttons.setPadding(new Insets(10, 0, 10, 0));

        VBox right = new VBox(10, new Label("Tìm Kiếm Theo Mã Sách"), tfSearch, new Button("Tìm Kiếm"));
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.TOP_LEFT);

        HBox top = new HBox(10, form, right);
        top.setPadding(new Insets(10));

        VBox root = new VBox(top, buttons, table);
        root.setPadding(new Insets(10));

        // Xử lý sự kiện
        btnAdd.setOnAction(e -> addBook());
        btnEdit.setOnAction(e -> editBook());
        btnDelete.setOnAction(e -> deleteBook());
        btnShow.setOnAction(e -> table.setItems(books));

        tfSearch.setOnKeyReleased(e -> searchBook(tfSearch.getText()));

        // Hiển thị giao diện
        stage.setTitle("Quản Lý Thư Viện - [frmQuanLySach]");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

    private void setupTable() {
        table.getColumns().addAll(
                createColumn("Mã Sách", "id", 80),
                createColumn("Tên Sách", "name", 180),
                createColumn("Thể Loại", "type", 100),
                createColumn("Nhà Xuất Bản", "publisher", 150),
                createColumn("Ngày Xuất Bản", "publishDate", 120),
                createColumn("Mã Tác Giả", "authorId", 100),
                createColumn("Số Lượng", "quantity", 80),
                createColumn("Giá Tiền", "price", 100)
        );

        table.setItems(books);
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Book book = row.getItem();
                    fillForm(book);
                }
            });
            return row;
        });
    }

    private TableColumn<Book, ?> createColumn(String title, String prop, int width) {
        TableColumn<Book, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(prop));
        col.setPrefWidth(width);
        return col;
    }

    private void fillForm(Book b) {
        tfId.setText(b.getId());
        tfName.setText(b.getName());
        tfType.setText(b.getType());
        tfPublisher.setText(b.getPublisher());
        dpDate.setValue(b.getPublishDate());
        tfAuthor.setText(b.getAuthorId());
        tfQuantity.setText(String.valueOf(b.getQuantity()));
        tfPrice.setText(String.valueOf(b.getPrice()));
    }

    private void addBook() {
        books.add(Book.fromForm(tfId, tfName, tfType, tfPublisher, dpDate, tfAuthor, tfQuantity, tfPrice));
    }

    private void editBook() {
        Book selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            books.remove(selected);
            addBook();
        }
    }

    private void deleteBook() {
        books.remove(table.getSelectionModel().getSelectedItem());
    }

    private void searchBook(String keyword) {
        ObservableList<Book> filtered = books.filtered(b -> b.getId().contains(keyword));
        table.setItems(filtered);
    }
}
