package com.example.demo1.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private String loggedInUsername;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        showLoginForm();
    }

    // Mở form đăng nhập
    public void showLoginForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/LoginForm.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            primaryStage.setTitle("Đăng nhập");
            primaryStage.setScene(scene);
            primaryStage.show();

            LoginController controller = loader.getController();
            controller.setMainApp(this);  // Truyền MainForm vào LoginController
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mở giao diện chính sau khi đăng nhập
    public void showMain(String username) {
        this.loggedInUsername = username;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/Main.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            primaryStage.setTitle("Ứng dụng Quản lý Thư viện");
            primaryStage.setScene(scene);
            primaryStage.show();

            MainController controller = loader.getController();
            controller.setMainApp(this);
            controller.setLoggedInUsername(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProfileForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/ProfileForm.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);
            Stage profileStage = new Stage();
            profileStage.setTitle("Hồ sơ người dùng");
            profileStage.setScene(scene);
            profileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
