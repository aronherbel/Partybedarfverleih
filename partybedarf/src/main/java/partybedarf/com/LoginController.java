package partybedarf.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField inpEmail;

    @FXML
    private PasswordField inpPassword;

    @FXML
    private Label messageLabel;

    @FXML
    private Label lblsignInOne;

    @FXML Label lblsignInTwo;

    private static final String FILE_PATH = "users.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(this::handleLogIn);
        lblsignInOne.setOnMouseClicked(e -> switchTosignin());
        lblsignInTwo.setOnMouseClicked(e -> switchTosignin());
    }

    private void switchTosignin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handleLogIn(ActionEvent event) {
        String email = inpEmail.getText();
        String password = inpPassword.getText();


        if (validateLogin(email, password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Invalid email or password. (sign up first)");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private boolean validateLogin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(email) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
