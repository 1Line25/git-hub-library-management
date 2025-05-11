package com.example.demo1.application;


import com.example.demo1.models.Reader;
import com.example.demo1.storage.DataStore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button hoSoButton;
    @FXML
    private Button quanLyDauSachButton;
    @FXML
    private Button quanLySachButton;
    @FXML
    private Button quanLyDocGiaButton;
    @FXML
    private Button quanLyThuThuButton;
    @FXML
    private Button quanLyTheThuVienButton;
    @FXML
    private Button quanLyTacGiaButton;
    @FXML
    private Button quanLyPhieuMuonButton;

    // Method to initialize the controller and handle button actions
    @FXML
    public void initialize() {
        // Set action for Hồ sơ button
        hoSoButton.setOnAction(event -> handleHoSoButton());
        // Set action for Quản lý đầu sách button
        quanLyDauSachButton.setOnAction(event -> handleQuanLyDauSachButton());
        // Set action for Quản lý sách button
        quanLySachButton.setOnAction(event -> handleQuanLySachButton());
        // Set action for Quản lý độc giả button
        quanLyDocGiaButton.setOnAction(event -> handleQuanLyDocGiaButton());
        // Set action for Quản lý thủ thư button
        quanLyThuThuButton.setOnAction(event -> handleQuanLyThuThuButton());
        // Set action for Quản lý thẻ thư viện button
        quanLyTheThuVienButton.setOnAction(event -> handleQuanLyTheThuVienButton());
        // Set action for Quản lý tác giả button
        quanLyTacGiaButton.setOnAction(event -> handleQuanLyTacGiaButton());
        // Set action for Quản lý phiếu mượn button
        quanLyPhieuMuonButton.setOnAction(event -> handleQuanLyPhieuMuonButton());
    }

    private void handleHoSoButton() {
        System.out.println("Hồ sơ button clicked");

        // Nạp giao diện ProfileForm
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/ProfileForm.fxml"));
            AnchorPane profileForm = loader.load();

            // Lấy controller của ProfileForm
            ProfileFormController profileController = loader.getController();

            // Giả sử bạn đã có method để đặt thông tin người dùng vào form
            profileController.setLoggedInUsername("DG001"); // Truyền tên người dùng

            // Hiển thị giao diện ProfileForm
            Scene scene = new Scene(profileForm);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Thông tin Hồ Sơ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Không thể nạp giao diện Hồ sơ!");
        }
    }


    // Quản lý đầu sách button action
    private void handleQuanLyDauSachButton() {
        try {
            // Tạo FXMLLoader để load BookTitleForm.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/BookTitleForm.fxml"));

            // Load giao diện và gán vào VBox (thay vì AnchorPane)
            VBox root = loader.load();

            // Khởi tạo stage mới để mở BookTitleForm
            Stage stage = new Stage();
            stage.setTitle("Quản lý đầu sách");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Thông báo lỗi nếu không thể tải FXML
            System.out.println("Error loading the FXML file: " + e.getMessage());
        }
    }


    // Quản lý sách button action
    private void handleQuanLySachButton() {
        try {
            // Tạo FXMLLoader để load BookForm.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/BookForm.fxml"));

            // Load root node (VBox theo như FXML của bạn)
            VBox root = loader.load();

            // Tạo Stage và Scene mới
            Stage stage = new Stage();
            stage.setTitle("Quản lý sách");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Không thể load BookForm.fxml: " + e.getMessage());
        }
    }


    // Quản lý độc giả button action
    private void handleQuanLyDocGiaButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/ReaderForm.fxml"));
            VBox root = loader.load(); // VBox vì FXML dùng VBox làm root

            Stage stage = new Stage();
            stage.setTitle("Quản lý độc giả");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể mở form Quản lý độc giả:\n" + e.getMessage());
        }
    }

    // Phương thức hiển thị cảnh báo đơn giản
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Quản lý thủ thư button action

    @FXML
    private void handleQuanLyThuThuButton() {
        try {
            System.out.println("Quản lý thủ thư button clicked");

            // Tải FXML cho form quản lý thủ thư
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/LibrarianForm.fxml"));
            Parent librarianForm = loader.load();

            // Cập nhật scene và chuyển sang màn hình quản lý thủ thư
            Scene scene = new Scene(librarianForm);
            Stage stage = (Stage) quanLyThuThuButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            // Xử lý lỗi, ví dụ in thông báo lỗi ra console
            e.printStackTrace();
        }
    }



    // Quản lý thẻ thư viện button action
    @FXML
    private void handleQuanLyTheThuVienButton() {
        System.out.println("Quản lý thẻ thư viện button clicked");

        // Fetch and display all library cards from the database
        DataStore.getAllLibraryCards().forEach(libraryCard -> System.out.println(libraryCard));

        // Mở màn hình quản lý thẻ thư viện (LibraryCardFormController)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/LibraryCardForm.fxml"));
            Parent libraryCardForm = loader.load();

            Scene scene = new Scene(libraryCardForm);
            Stage stage = (Stage) quanLyTheThuVienButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();  // Xử lý lỗi khi tải FXML
        }
    }



    // Quản lý tác giả button action
    @FXML
    private void handleQuanLyTacGiaButton() {
        System.out.println("Quản lý tác giả button clicked");

        // Fetch and display all authors from the database
        DataStore.getAllAuthors().forEach(author -> System.out.println(author));

        // Mở màn hình quản lý tác giả (AuthorFormController)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/AuthorForm.fxml"));
            Parent authorForm = loader.load();

            Scene scene = new Scene(authorForm);
            Stage stage = (Stage) quanLyTacGiaButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();  // Xử lý lỗi khi tải FXML
        }
    }



    // Quản lý phiếu mượn button action
    @FXML
    private void handleQuanLyPhieuMuonButton() {
        System.out.println("Quản lý phiếu mượn button clicked");

        // In ra danh sách phiếu mượn (tùy mục đích debug)
        DataStore.getAllLendingSlips().forEach(loanSlip -> System.out.println(loanSlip));

        // Chuyển sang màn hình LendingSlipForm
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/views/LendingSlipForm.fxml"));
            Parent lendingSlipForm = loader.load();

            Scene scene = new Scene(lendingSlipForm);
            Stage stage = (Stage) quanLyPhieuMuonButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi khi không tải được FXML
        }
    }

    private Main mainApp;
    private String loggedInUsername;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        // Bạn có thể thực hiện các hành động khác sau khi nhận được username, ví dụ:
        System.out.println("Đã đăng nhập với tên người dùng: " + loggedInUsername);
        // Cập nhật giao diện người dùng để hiển thị thông tin người dùng, v.v.
    }

    // Các phương thức và thuộc tính khác của MainController
}



