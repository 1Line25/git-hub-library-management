package com.example.demo.giaodien;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginForm loginForm = new LoginForm(primaryStage);
        loginForm.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
