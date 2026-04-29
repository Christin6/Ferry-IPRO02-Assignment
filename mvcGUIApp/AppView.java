import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppView {
    private VBox loginView;
    private VBox customerView;
    private VBox adminView;
    // private TableView<> ;

    private AppController controller;
    private AppModel model;
    private Stage primaryStage;

    public AppView(AppController controller, AppModel model, Stage primaryStage) {
        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;

        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();
    }

    public Parent asParent() {
        return loginView;
    }

    private void observeModelAndUpdateControls() {

    }

    private void updateControllerFromListeners() {

    }

    private void createAndLayoutControls() {
        Button customerBtn = new Button("Customer");
        customerBtn.setOnAction(e -> {
            this.changeScene(customerView);
        });

        Button adminBtn = new Button("Admin");
        adminBtn.setOnAction(e -> {
            this.changeScene(adminView);
        });

        // login view setup
        Label loginLabel = new Label("LOGIN PAGE");
        loginView.getChildren().addAll(loginLabel, customerBtn, adminBtn);

        Button backToLoginBtn = new Button("Back to Login Page");
        backToLoginBtn.setOnAction(e -> {
            this.changeScene(loginView);
        });

        // Admin view setup
        Label adminLabel = new Label();
        adminView.getChildren().addAll(adminLabel, backToLoginBtn);

        // Customer view setup
        Label customerLabel = new Label();
        customerView.getChildren().addAll(customerLabel, backToLoginBtn);
    }

    private void createAndConfigurePane() {
        loginView = new VBox(5);
        loginView.setAlignment(Pos.CENTER);

        customerView = new VBox(5);
        customerView.setAlignment(Pos.CENTER);

        adminView = new VBox(5);
        adminView.setAlignment(Pos.CENTER);
    }

    private void changeScene(Parent root) {
        Scene newScene = new Scene(root, 600, 300);
        primaryStage.setScene(newScene);
    }
}
