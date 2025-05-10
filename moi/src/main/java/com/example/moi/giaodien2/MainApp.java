package com.example.moi.giaodien2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo LoginForm và truyền primaryStage
        LoginForm loginForm = new LoginForm(primaryStage); // VBox kế thừa nên dùng được trực tiếp

        // Tạo Scene đăng nhập
        Scene loginScene = new Scene(loginForm, 400, 300);

        // Thêm CSS
        String cssFile = getClass().getResource("/styles.css").toExternalForm();
        loginScene.getStylesheets().add(cssFile);

        // Cấu hình Stage chính
        primaryStage.setTitle("Library Management - Login");
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(true); // ✅ Cho phép thay đổi kích thước
        primaryStage.setMinWidth(400);   // ✅ Thiết lập kích thước tối thiểu nếu muốn
        primaryStage.setMinHeight(300);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}