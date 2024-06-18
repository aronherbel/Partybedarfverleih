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
import javafx.event.EventHandler;
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

public class SignInController implements Initializable{

    @FXML
    private Button btnsignin;

    @FXML
    private TextField iptEmail;

    @FXML
    private PasswordField iptPassword;

    @FXML
    private PasswordField iptPasswordagain;

    @FXML
    private Label lblLogin;

    @FXML
    private Label messageLabelA;


    private static final String FILE_PATH = "users.txt";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnsignin.setOnAction(this::handleSignIn);
        lblLogin.setOnMouseClicked(e -> switchToLogin());
    }

    private void handleSignIn(ActionEvent e) {
        String email = iptEmail.getText();
        String password = iptPassword.getText();
        String passwordagain = iptPasswordagain.getText();

        if(password != null && passwordagain.equals(password) )
        {
            if(addUser(email, passwordagain)){
                messageLabelA.setText("User created You can go back to login now");
                messageLabelA.setStyle("-fx-text-fill: green");
            }
          

            }
           else{
            messageLabelA.setText("Make sure you entered the password correctly");
            messageLabelA.setStyle("-fx-text-fill: red;");
           }
        }
        

    private void switchToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnsignin.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean addUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(email)) {
                    messageLabelA.setText("Email already exists");
                    messageLabelA.setStyle("-fx-text-fill: red;");
                    return false; // Email already in use
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(email + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

   
}