package com.example.demo.giaodien;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginForm {
    private Stage stage;

    public LoginForm(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: lightgreen");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Đăng nhập");
        Button exitButton = new Button("Thoát");

        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.addRow(0, new Label("Tên tài khoản:"), usernameField);
        form.addRow(1, new Label("Mật khẩu:"), passwordField);
        form.addRow(2, loginButton, exitButton);

        root.getChildren().addAll(form);

        loginButton.setOnAction(e -> {
            if ("admin".equals(usernameField.getText()) && "admin".equals(passwordField.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Đăng nhập thành công");
                alert.showAndWait();
                new LibraryManager(stage).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Sai tên đăng nhập hoặc mật khẩu!").show();
            }
        });

        exitButton.setOnAction(e -> stage.close());

        stage.setTitle("Quản lý Thư viện - [Đăng nhập]");
        stage.setScene(new Scene(root, 400, 200));
        stage.show();
    }
}
