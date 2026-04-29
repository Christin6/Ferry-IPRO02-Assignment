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
    private VBox view;
    //private TableView<> ;

    private AppController controller;
    private AppModel model;
    private Stage primaryStage;
    
    public AppView(AppController controller, AppModel model, Stage primaryStage){
        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;

        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();
    }

    public Parent asParent() {
        return view;
    }

    private void observeModelAndUpdateControls() {

    }

    private void updateControllerFromListeners() {

    }

    private void createAndLayoutControls(){
        Label loginLabel = new Label("LOGIN PAGE");

        Button backToLoginBtn = new Button("Back to Login Page");
        backToLoginBtn.setOnAction(e -> {
            
        });

        Button customerBtn = new Button("Customer");
        customerBtn.setOnAction(e -> {
            //Scene needs to be adjusted later on
            Label customerLabel = new Label();

            VBox root = new VBox(5, customerLabel);
            root.setAlignment(Pos.CENTER);

            Scene customerScene = new Scene(root, 500, 300);

            primaryStage.setScene(customerScene);
        });

        Button adminBtn = new Button("Admin");
        adminBtn.setOnAction(e -> {
            Label adminLabel = new Label();

            VBox root = new VBox(5, adminLabel);
            root.setAlignment(Pos.CENTER);

            Scene adminScene = new Scene(root, 500, 300);

            primaryStage.setScene(adminScene);
        });

        VBox root = new VBox(loginLabel, customerBtn, adminBtn);
        root.setAlignment(Pos.CENTER);

        view.getChildren().addAll(loginLabel, customerBtn, adminBtn);
    }

    private void createAndConfigurePane() {
        view = new VBox(5);
        view.setAlignment(Pos.CENTER);
    }
}

