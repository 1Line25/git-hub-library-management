package com.example.moi.giaodien2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;

public class LibraryManager extends BorderPane {

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

    public LibraryManager(Stage primaryStage) {
        setPadding(new Insets(10));

        // Sidebar
        setLeft(createSidebar());

        // Content Area with flexible sizing
        setCenter(createContentArea());

        setupTable();
        loadInitialData();
        setupEvents();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.getStyleClass().add("sidebar");

        Label searchLabel = new Label("Tìm kiếm theo mã sách");
        searchLabel.getStyleClass().add("form-label");

        tfSearch.setPromptText("Nhập mã sách...");
        tfSearch.getStyleClass().add("text-field");

        sidebar.getChildren().addAll(searchLabel, tfSearch);
        return sidebar;
    }

    private VBox createContentArea() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getStyleClass().add("content-area");

        // Dynamic form and button layout
        GridPane form = createForm();
        HBox buttons = createButtons();

        VBox.setVgrow(table, Priority.ALWAYS);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        content.getChildren().addAll(form, buttons, table);
        VBox.setVgrow(content, Priority.ALWAYS);
        return content;
    }

    private GridPane createForm() {
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        tfId.getStyleClass().add("text-field");
        tfName.getStyleClass().add("text-field");
        tfType.getStyleClass().add("text-field");
        tfPublisher.getStyleClass().add("text-field");
        tfAuthor.getStyleClass().add("text-field");
        tfQuantity.getStyleClass().add("text-field");
        tfPrice.getStyleClass().add("text-field");

        form.addRow(0, createLabel("Mã Sách:"), tfId, createLabel("Ngày Xuất Bản:"), dpDate);
        form.addRow(1, createLabel("Tên Sách:"), tfName, createLabel("Mã Tác Giả:"), tfAuthor);
        form.addRow(2, createLabel("Thể Loại:"), tfType, createLabel("Số Lượng:"), tfQuantity);
        form.addRow(3, createLabel("Nhà Xuất Bản:"), tfPublisher, createLabel("Giá Tiền:"), tfPrice);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);  // Tăng cường độ rộng của cột 1
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);  // Tăng cường độ rộng của cột 2
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);  // Cột 3 giữ lại kích thước nhỏ
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(30);  // Cột 4 giữ lại kích thước nhỏ
        form.getColumnConstraints().addAll(col1, col2, col3, col4);

        return form;
    }

    private HBox createButtons() {
        Button btnAdd = createButton("Thêm");
        Button btnEdit = createButton("Sửa");
        Button btnDelete = createButton("Xóa");
        Button btnShow = createButton("Hiển thị");

        btnAdd.setOnAction(e -> addBook());
        btnEdit.setOnAction(e -> editBook());
        btnDelete.setOnAction(e -> deleteBook());
        btnShow.setOnAction(e -> table.setItems(books));
        tfSearch.setOnKeyReleased(e -> searchBook(tfSearch.getText()));

        HBox buttonBar = new HBox(10, btnAdd, btnEdit, btnDelete, btnShow);
        buttonBar.setAlignment(Pos.CENTER_LEFT);
        return buttonBar;
    }

    private Label createLabel(String text) {
        Label lbl = new Label(text);
        lbl.getStyleClass().add("form-label");
        return lbl;
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.getStyleClass().add("button");
        btn.setFont(Font.font(14));
        return btn;
    }

    private void setupTable() {
        table.getColumns().addAll(
                createColumn("Mã Sách", "id", 80),
                createColumn("Tên Sách", "name", 150),
                createColumn("Thể Loại", "type", 100),
                createColumn("Nhà Xuất Bản", "publisher", 120),
                createColumn("Ngày XB", "publishDate", 100),
                createColumn("Mã TG", "authorId", 80),
                createColumn("Số Lượng", "quantity", 70),
                createColumn("Giá Tiền", "price", 80)
        );

        table.setItems(books);
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty()) fillForm(row.getItem());
            });
            return row;
        });
    }

    private TableColumn<Book, ?> createColumn(String title, String prop, int width) {
        TableColumn<Book, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(prop));
        col.setPrefWidth(width);
        col.setResizable(true);  // Cho phép người dùng thay đổi kích thước cột
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
        Book newBook = Book.fromForm(tfId, tfName, tfType, tfPublisher, dpDate, tfAuthor, tfQuantity, tfPrice);
        if (newBook != null) {
            books.add(newBook);
            clearForm();
        }
    }

    private void editBook() {
        Book selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Book updatedBook = Book.fromForm(tfId, tfName, tfType, tfPublisher, dpDate, tfAuthor, tfQuantity, tfPrice);
            if (updatedBook != null) {
                books.set(books.indexOf(selected), updatedBook);
                clearForm();
            }
        } else {
            showAlert("Vui lòng chọn sách để sửa.");
        }
    }

    private void deleteBook() {
        Book selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            books.remove(selected);
            clearForm();
        } else {
            showAlert("Vui lòng chọn sách để xóa.");
        }
    }

    private void searchBook(String keyword) {
        if (keyword.isEmpty()) {
            table.setItems(books);
        } else {
            ObservableList<Book> filtered = books.filtered(b -> b.getId().contains(keyword));
            table.setItems(filtered);
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void clearForm() {
        tfId.clear();
        tfName.clear();
        tfType.clear();
        tfPublisher.clear();
        dpDate.setValue(null);
        tfAuthor.clear();
        tfQuantity.clear();
        tfPrice.clear();
    }

    private void loadInitialData() {
        books.add(new Book("1", "Book 1", "Fiction", "NXB A", LocalDate.of(2020, 1, 15), "TG1", 10, 25.0));
        books.add(new Book("2", "Book 2", "Non-Fiction", "NXB B", LocalDate.of(2021, 5, 20), "TG2", 5, 30.0));
        books.add(new Book("3", "Book 3", "Sci-Fi", "NXB C", LocalDate.of(2022, 10, 10), "TG3", 8, 28.0));
    }

    private void setupEvents() {
        // Events handled in createButtons()
    }
}
