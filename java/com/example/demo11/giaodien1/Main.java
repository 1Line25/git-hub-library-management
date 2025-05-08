package com.example.demo11.giaodien1;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo11.giaodien1.LoginForm;  // Đảm bảo đúng package của LoginForm

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo đối tượng LoginForm và hiển thị giao diện đăng nhập
        LoginForm loginForm = new LoginForm(primaryStage);
        loginForm.show();  // Hiển thị giao diện LoginForm
    }

    public static void main(String[] args) {
        launch(args);  // Khởi chạy ứng dụng JavaFX
    }
}
