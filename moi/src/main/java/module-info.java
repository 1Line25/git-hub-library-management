module com.example.moi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql; // Added the missing java.sql dependency

    // External libraries
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    // Open packages to allow reflection (JavaFX, FXML)
    opens com.example.moi to javafx.fxml;
    opens com.example.moi.controllers to javafx.fxml; // Open controllers for FXML

    // Export necessary packages to be accessible by other modules or classes
    exports com.example.moi;
    exports com.example.moi.giaodien2;
    exports com.example.moi.controllers;
    exports com.example.moi.models;
}