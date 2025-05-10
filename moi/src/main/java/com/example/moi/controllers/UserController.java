package com.example.moi.controllers;

import com.example.moi.models.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserController {

    private final Library library = Library.getInstance();
    private Stage primaryStage;

    public void start(Stage stage) {
        this.primaryStage = stage;
        showLoginScreen();
    }

    private void showLoginScreen() {
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));
        loginBox.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Library Login");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #004080;");

        TextField idField = new TextField();
        idField.setPromptText("Enter ID");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white;");
        loginButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String password = passwordField.getText();
            Person person = library.getPersonById(id);

            if (person != null && person.getPassword().equals(password)) {
                if (person instanceof Staff) showStaffMenu((Staff) person);
                else showBorrowerMenu((Borrower) person);
            } else {
                showAlert("Login Failed", "Invalid ID or password.");
            }
        });

        loginBox.getChildren().addAll(titleLabel, idField, passwordField, loginButton);
        Scene scene = new Scene(loginBox, 300, 250);
        primaryStage.setTitle("Library System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showStaffMenu(Staff staff) {
        VBox menuBox = new VBox(10);
        menuBox.setPadding(new Insets(20));
        menuBox.setStyle("-fx-background-color: white;");

        Label header = new Label("Staff Menu");
        header.setStyle("-fx-font-size: 18px; -fx-text-fill: #004080;");

        Button issueBookBtn = createMenuButton("Issue Book", () -> {/* Implement Issue Book */});
        Button returnBookBtn = createMenuButton("Return Book", () -> {/* Implement Return Book */});
        Button viewBorrowerBtn = createMenuButton("View Borrower Info", () -> {/* Implement View Borrower Info */});
        Button logoutBtn = createMenuButton("Logout", this::showLoginScreen);

        menuBox.getChildren().addAll(header, issueBookBtn, returnBookBtn, viewBorrowerBtn, logoutBtn);
        primaryStage.setScene(new Scene(menuBox, 400, 300));
    }

    private void showBorrowerMenu(Borrower borrower) {
        VBox menuBox = new VBox(10);
        menuBox.setPadding(new Insets(20));
        menuBox.setStyle("-fx-background-color: white;");

        Label header = new Label("Borrower Menu");
        header.setStyle("-fx-font-size: 18px; -fx-text-fill: #004080;");

        Button borrowBookBtn = createMenuButton("Borrow Book", () -> {/* Implement Borrow Book */});
        Button returnBookBtn = createMenuButton("Return Book", () -> {/* Implement Return Book */});
        Button viewLoansBtn = createMenuButton("View Loan Details", () -> {/* Implement View Loans */});
        Button payFineBtn = createMenuButton("Pay Fine", () -> {/* Implement Pay Fine */});
        Button logoutBtn = createMenuButton("Logout", this::showLoginScreen);

        menuBox.getChildren().addAll(header, borrowBookBtn, returnBookBtn, viewLoansBtn, payFineBtn, logoutBtn);
        primaryStage.setScene(new Scene(menuBox, 400, 300));
    }

    private Button createMenuButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
