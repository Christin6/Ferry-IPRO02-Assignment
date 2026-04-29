import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello! Bye!");

        Label label = new Label("Click the button!");

        Button helloButton = new Button();
        helloButton.setText("Say 'Hello!'");

        VBox root = new VBox();
        root.getChildren().addAll(label, helloButton);
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}