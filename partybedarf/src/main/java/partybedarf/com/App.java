package partybedarf.com;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {  
        primaryStage = stage;
        setRoot("login");
        primaryStage.setTitle("Evrything for your Party");
        primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}