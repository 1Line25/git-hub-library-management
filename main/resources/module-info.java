module com.example.demo1 {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Java built-in modules
    requires java.sql;

    // Optional third-party libraries (bỏ nếu không dùng chúng thực tế)
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    // Nếu bạn không viết plugin compiler, nên bỏ dòng này:
    // requires jdk.compiler;

    // Mở package cho JavaFX reflection (FXML loader)
    opens com.example.demo1 to javafx.fxml;
    opens com.example.demo1.controllers to javafx.fxml;
    opens com.example.demo1.models to javafx.fxml;
    opens com.example.demo1.storage to javafx.fxml;
    opens com.example.demo1.utils to javafx.fxml;
    opens com.example.demo1.application to javafx.fxml;

    // Export các package để cho phép sử dụng từ bên ngoài (nếu cần)

    exports com.example.demo1;
    exports com.example.demo1.controllers;
    exports com.example.demo1.models;
    exports com.example.demo1.storage;
    exports com.example.demo1.utils;
    exports com.example.demo1.application;
}
