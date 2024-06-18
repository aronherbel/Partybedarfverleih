package partybedarf.com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.Icon;

public class MainController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ToggleButton darkModeToggle;


    @FXML
    private Icon logout;

    @FXML
    private Icon icAddBar;

    @FXML
    private Icon icAddDeco;

    @FXML
    private Icon icAddDjPult;

    @FXML
    private Icon icAddLights;

    @FXML
    private Icon icAddMicrophone;

    @FXML
    private Icon icAddMusicBox;

    @FXML
    private Label lblCount;

    private int count = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logout.setOnMouseClicked(e -> logout());
        icAddBar.setOnMouseClicked(e -> addWare());
        icAddDeco.setOnMouseClicked(e -> addWare());
        icAddDjPult.setOnMouseClicked(e -> addWare());
        icAddLights.setOnMouseClicked(e -> addWare());
        icAddMicrophone.setOnMouseClicked(e -> addWare());
        icAddMusicBox.setOnMouseClicked(e -> addWare());


    }

   

    private void addWare(){
        count++;
        lblCount.setText(String.valueOf(count));
    }

    private void logout() {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setMode(ActionEvent e) {
        if (darkModeToggle.isSelected()) {
            // Darkmode
            rootPane.setStyle("-fx-background-color: #434040;");
            applyStyles(rootPane, "-fx-text-fill: #18F1EA;", "-fx-fill: #18F1EA;");
        } else {
            // Lightmode (example colors, you can set any colors you want)
            rootPane.setStyle("-fx-background-color: #FFFFFF;");
            applyStyles(rootPane, "-fx-text-fill: #000000;", "-fx-fill: #000000;");
        }
    }

    private void applyStyles(Node node, String textColor, String iconColor) {
        if (node instanceof Label) {
            node.setStyle(textColor);
        } else if (node instanceof Icon) {
            node.setStyle(iconColor);
        }

        if (node instanceof javafx.scene.Parent) {
            ((javafx.scene.Parent) node).getChildrenUnmodifiable().forEach(child -> applyStyles(child, textColor, iconColor));
        }
    }
}
