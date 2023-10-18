module com.example.dataanalyticshubfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.dataanalyticshubfx to javafx.fxml;
    exports com.example.dataanalyticshubfx;
}