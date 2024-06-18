module partybedarf.com {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;

    opens partybedarf.com to javafx.fxml;
    exports partybedarf.com;
}
