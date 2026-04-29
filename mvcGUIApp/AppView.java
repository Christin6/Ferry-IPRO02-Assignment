import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppView {
    private VBox loginView;
    private VBox customerView;
    private VBox adminView;
    private TableView <FerryTrip> tripsView;

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

    private void createAndLayoutControls(){
        this.tripsView = new TableView<>();
        TableColumn<FerryTrip, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());

        TableColumn<FerryTrip, String> startingPointCol = new TableColumn<>("Starting Point");
        startingPointCol.setCellValueFactory(cellData -> cellData.getValue().startingPointProperty());

        TableColumn<FerryTrip, Double> basePriceCol = new TableColumn<>("Base Price");
        basePriceCol.setCellValueFactory(cellData -> cellData.getValue().basePriceProperty().asObject());

        this.tripsView.getColumns().addAll(destinationCol, startingPointCol, basePriceCol);
        this.tripsView.setItems(model.tripsProperty());

        //For now, all the admin buttons are below here
        Button viewDetailsBtn = new Button("View Details");
        
        Button addTripBtn = new Button("Add Trip");
        addTripBtn.setOnAction(e -> {
            createAddTripForm();
        });

        Button editTripBtn = new Button("Edit Trip");

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

        // Admin view setup
        Button backToLoginBtnFromAdmin = new Button("Logout");
        backToLoginBtnFromAdmin.setOnAction(e -> {
            this.changeScene(loginView);
        });
        Label adminLabel = new Label();
        //Added 'addTripBtn' just to test if the frame works
        adminView.getChildren().addAll(adminLabel, backToLoginBtnFromAdmin, addTripBtn); 

        // Customer view setup
        Button backToLoginBtnFromCust = new Button("Logout");
        backToLoginBtnFromCust.setOnAction(e -> {
            this.changeScene(loginView);
        });
        Label customerLabel = new Label();
        customerView.getChildren().addAll(customerLabel, backToLoginBtnFromCust, tripsView);
    }

    //For now all the admin modal windows are here
    private void createAddTripForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Trip Form");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label text = new Label("Enter the starting point, the destination, and the price of the trip");

        TextField startingPointTextField = new TextField();
        startingPointTextField.setPromptText("Add starting point");
        TextField destinationTextField = new TextField();
        destinationTextField.setPromptText("Add destination");

        TextField basePriceTextField = new TextField();
        basePriceTextField.setPromptText("Add price");
        configTextFieldForDoubles(basePriceTextField);

        Button submitBtn = new Button("Submit Button");
        submitBtn.setOnAction(e -> {
            if (!startingPointTextField.getText().isEmpty() && !destinationTextField.getText().isEmpty() && !basePriceTextField.getText().isEmpty()) {
                System.out.println("it works");
            }
        });

        Button cancelBtn = new Button("Cancel Button");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });
        
        HBox locationRow = new HBox(5, startingPointTextField, new Label ("to"), destinationTextField, new Label("$"), basePriceTextField);
        locationRow.setAlignment(Pos.CENTER);

        HBox BtnRow = new HBox(5, submitBtn, cancelBtn);
        BtnRow.setAlignment(Pos.CENTER);
        
        VBox root = new VBox(5, text, locationRow, BtnRow);
        root.setAlignment(Pos.CENTER);

        Scene addTripScene = new Scene(root, 550, 200);
        stage.setScene(addTripScene);
        stage.show();
    }

    private void createUpdateTripForm() {
        Stage stage = new Stage();
        stage.setTitle("Update Trip Form");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label text = new Label("Enter the starting point, the destination, and the price of the trip");
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

    private void configTextFieldForDoubles(TextField field) {
        field.setTextFormatter(new TextFormatter<Integer>((Change c) -> {
            if (c.getControlNewText().matches("\\d+.?\\d*")) {
                return c;
            }
            return null;
        }));
    }
}
