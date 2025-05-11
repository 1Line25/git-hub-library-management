package com.example.demo1.application;

import com.example.demo1.storage.DataStore;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private Main mainApp;  // Tham chiếu tới MainForm

    @FXML
    private TextField taiKhoanField;

    @FXML
    private PasswordField matKhauField;

    // Setter để nhận tham chiếu MainForm từ MainForm.java
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    // Xử lý đăng nhập
    @FXML
    public void handleDangNhap() {
        String taiKhoan = taiKhoanField.getText();
        String matKhau = matKhauField.getText();

        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Tài khoản hoặc mật khẩu không được để trống.");
            return;
        }

        // Call the isValidUser method to verify the user
        boolean isValid = DataStore.isValidUser(taiKhoan, matKhau);
        if (isValid) {
            showAlert(Alert.AlertType.INFORMATION, "Đăng nhập thành công", "Chào mừng bạn đã đăng nhập!");
            try {
                mainApp.showMain(taiKhoan);  // Gọi MainForm để chuyển màn hình
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện chính: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi đăng nhập", "Tài khoản hoặc mật khẩu không chính xác.");
        }
    }


    @FXML
    public void handleDangKy() {
        showAlert(Alert.AlertType.INFORMATION, "Đăng ký", "Đây là nơi bạn có thể thêm chức năng đăng ký.");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
