package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    private static final int ROWS = 20;    // Số hàng mới
    private static final int COLUMNS = 4;  // Số cột mới

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Document Manager");

        // Tạo GridPane để bố trí các nút tài liệu
        GridPane grid = new GridPane();
        grid.setHgap(5);  // Khoảng cách ngang giữa các nút
        grid.setVgap(5);  // Khoảng cách dọc giữa các nút

        // Tạo các nút mô phỏng tài liệu và thêm vào GridPane
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Button docButton = new Button("T" + (row * COLUMNS + col + 1)); // Tạo tên tài liệu T1, T2, ...
                docButton.setPrefWidth(60);  // Đặt chiều rộng nút
                docButton.setPrefHeight(40); // Đặt chiều cao nút
                grid.add(docButton, col, row); // Thêm nút vào GridPane tại vị trí (col, row)
            }
        }

        // Tạo cảnh (scene) cho ứng dụng
        Scene scene = new Scene(grid, 1280, 600);  // Kích thước cảnh thay đổi để vừa với 20 hàng và 4 cột
        primaryStage.setScene(scene);  // Gán cảnh vào cửa sổ
        primaryStage.show();  // Hiển thị cửa sổ
    }

    public static void main(String[] args) {
        launch(args);  // Khởi chạy ứng dụng JavaFX
    }
}
