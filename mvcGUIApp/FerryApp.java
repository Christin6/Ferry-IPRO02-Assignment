import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FerryApp extends Application {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ferry Management App");
        Label loginLabel = new Label("LOGIN PAGE");

        Button customerBtn = new Button("Customer");

        Button adminBtn = new Button("Admin");

        VBox root = new VBox(loginLabel, customerBtn, adminBtn);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}