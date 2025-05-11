package com.example.demo1.application;

import com.example.demo1.storage.DataStore;
import com.example.demo1.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ProfileFormController {

    @FXML
    private AnchorPane profileFormPane;

    @FXML
    private TextField idField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField genderField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button updateButton;

    @FXML
    private Label profileStatusLabel;

    private String loggedInUsername;

    // This method sets the logged-in username and loads the user data
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        initializeUserData();
    }

    // This method initializes the user data into the fields
    private void initializeUserData() {
        User user = DataStore.getUserByUsername(loggedInUsername);
        if (user != null) {
            idField.setText(user.getId());
            lastNameField.setText(user.getLastName());
            firstNameField.setText(user.getFirstName());
            genderField.setText(user.getGender());
            birthDatePicker.setValue(user.getBirthDate());
            emailField.setText(user.getEmail());
            addressField.setText(user.getAddress());
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
        } else {
            profileStatusLabel.setText("Không tìm thấy thông tin người dùng!");
        }
    }

    // This method is called when the user clicks the "Update" button
    @FXML
    private void handleUpdateProfile() {
        // Get the information from the fields
        String id = idField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String gender = genderField.getText();
        String birthDate = birthDatePicker.getValue() != null ? birthDatePicker.getValue().toString() : "";
        String email = emailField.getText();
        String address = addressField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if the required fields are filled out
        if (id.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || email.isEmpty()) {
            profileStatusLabel.setText("Vui lòng điền đầy đủ thông tin!");
        } else {
            // Call the DataStore method to update the user's information
            boolean isUpdated = updateUserInfo(id, lastName, firstName, gender, birthDate, email, address, username, password);

            if (isUpdated) {
                profileStatusLabel.setText("Thông tin đã được cập nhật!");
            } else {
                profileStatusLabel.setText("Cập nhật thất bại! Vui lòng thử lại.");
            }
        }
    }

    // This method updates the user info in the DataStore
    private boolean updateUserInfo(String id, String lastName, String firstName, String gender, String birthDate,
                                   String email, String address, String username, String password) {
        // Assuming you have a User class and a method in DataStore to update user information
        boolean isUpdated = DataStore.updateUserProfile(id, lastName, firstName, gender, birthDate, email, address, username, password);
        return isUpdated;
    }
}
