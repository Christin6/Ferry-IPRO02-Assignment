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
    private Stage adminPane;
    private Stage customerPane;
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

        Button customerBtn = new Button("Customer");
        customerBtn.setOnAction(e -> {
            showCustomerPane();
        });

        Button adminBtn = new Button("Admin");
        adminBtn.setOnAction(e -> {
            showAdminPane();
        });

        // login view setup
        Label loginLabel = new Label("LOGIN PAGE");
        loginView.getChildren().addAll(loginLabel, customerBtn, adminBtn);

        // Admin view setup
        this.adminPane = new Stage();
        Button backToLoginBtnFromAdmin = new Button("Logout");
        backToLoginBtnFromAdmin.setOnAction(e -> {
            this.primaryStage.show();
            this.adminPane.close();
        });
        Label adminLabel = new Label();
        adminView.getChildren().addAll(adminLabel, backToLoginBtnFromAdmin);
        Scene adminScene = new Scene(adminView, 800, 500);
        this.adminPane.setScene(adminScene);

        // Customer view setup
        this.customerPane = new Stage();
        // Customer view setup
        Button backToLoginBtnFromCust = new Button("Logout");
        backToLoginBtnFromCust.setOnAction(e -> {
            this.primaryStage.show();
            this.customerPane.close();
        });
        Label customerLabel = new Label();
        customerView.getChildren().addAll(customerLabel, backToLoginBtnFromCust, tripsView);
        
        Scene customerScene = new Scene(customerView, 800, 500);

        this.customerPane.setScene(customerScene);
    }

    private void showAdminPane() {
        this.adminPane.show();
        this.primaryStage.close();
    }

    private void showCustomerPane() {
        this.customerPane.show();
        this.primaryStage.close();
    }

    private void createAndConfigurePane() {
        loginView = new VBox(5);
        loginView.setAlignment(Pos.CENTER);

        customerView = new VBox(5);
        customerView.setAlignment(Pos.CENTER);

        adminView = new VBox(5);
        adminView.setAlignment(Pos.CENTER);
    }
}
