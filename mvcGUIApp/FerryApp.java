import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FerryApp extends Application {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ferry Management App");

        AppModel model = new AppModel();
        AppController controller = new AppController(model, ferryManagementModel);
        AppView view = new AppView(controller, model, primaryStage);

        Scene scene = new Scene(view.asParent(), 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}