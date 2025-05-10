package com.example.moi.giaodien2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginForm extends VBox {

    private final TextField tfUsername = new TextField();
    private final PasswordField tfPassword = new PasswordField();
    private final Button btnLogin = new Button("Login");
    private final Stage primaryStage;

    public LoginForm(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupUI();
        setupEvents();
    }

    private void setupUI() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setFillWidth(true); // Giúp các thành phần mở rộng theo chiều ngang
        getStyleClass().add("form-container");

        Label lblUsername = new Label("Username:");
        Label lblPassword = new Label("Password:");
        lblUsername.getStyleClass().add("form-label");
        lblPassword.getStyleClass().add("form-label");

        tfUsername.setPromptText("Enter your username");
        tfPassword.setPromptText("Enter your password");
        tfUsername.setMaxWidth(Double.MAX_VALUE);
        tfPassword.setMaxWidth(Double.MAX_VALUE);
        tfUsername.getStyleClass().add("text-field");
        tfPassword.getStyleClass().add("text-field");

        btnLogin.setMaxWidth(Double.MAX_VALUE);
        btnLogin.getStyleClass().add("button");

        getChildren().addAll(lblUsername, tfUsername, lblPassword, tfPassword, btnLogin);

        // Cho phép mở rộng
        VBox.setVgrow(tfUsername, Priority.ALWAYS);
        VBox.setVgrow(tfPassword, Priority.ALWAYS);
        VBox.setVgrow(btnLogin, Priority.ALWAYS);
    }

    private void setupEvents() {
        btnLogin.setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        if ("admin".equals(username) && "admin".equals(password)) {
            LibraryManager libraryManager = new LibraryManager(primaryStage);
            Scene libraryScene = new Scene(libraryManager, 1000, 700); // Phóng to hơn

            String cssFile = getClass().getResource("/styles.css").toExternalForm();
            libraryScene.getStylesheets().add(cssFile);

            primaryStage.setScene(libraryScene);
            primaryStage.setMinWidth(900); // Thiết lập kích thước tối thiểu
            primaryStage.setMinHeight(600);
            primaryStage.setResizable(true);
            primaryStage.setTitle("Library Manager");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password. Please try again.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
