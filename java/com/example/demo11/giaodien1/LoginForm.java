package com.example.demo11.giaodien1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginForm {
    private final Stage stage;

    public LoginForm(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Tạo giao diện đăng nhập
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: lightgreen");

        // Các trường nhập liệu
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Đăng nhập");
        Button exitButton = new Button("Thoát");

        // Tạo bảng biểu mẫu
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.addRow(0, new Label("Tên tài khoản:"), usernameField);
        form.addRow(1, new Label("Mật khẩu:"), passwordField);
        form.addRow(2, loginButton, exitButton);

        // Thêm form vào giao diện chính
        root.getChildren().addAll(form);

        // Cài đặt sự kiện cho nút Đăng nhập
        loginButton.setOnAction(e -> {
            if ("admin".equals(usernameField.getText()) && "admin".equals(passwordField.getText())) {
                // Nếu đăng nhập thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Đăng nhập thành công");
                alert.showAndWait();
                new LibraryManager(stage).show(); // Hiển thị màn hình quản lý thư viện
            } else {
                // Nếu đăng nhập thất bại
                new Alert(Alert.AlertType.ERROR, "Sai tên đăng nhập hoặc mật khẩu!").show();
            }
        });

        // Cài đặt sự kiện cho nút Thoát
        exitButton.setOnAction(e -> stage.close());

        // Thiết lập giao diện cho stage
        stage.setTitle("Quản lý Thư viện - [Đăng nhập]");
        stage.setScene(new Scene(root, 400, 200));
        stage.show();
    }
}
