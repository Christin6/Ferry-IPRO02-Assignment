import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FerryApp extends Application {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ferry Management App");
        Label label = new Label("Text");
        VBox root = new VBox(label);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}