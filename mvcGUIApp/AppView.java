import javafx.beans.property.SimpleIntegerProperty;
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
    private Stage adminPane;
    private Stage customerPane;
    private TableView <FerryTrip> customerTripsView;
    private TableView <FerryTrip> adminTripsView;

    private AppController controller;
    private AppModel model;
    private Stage primaryStage;

    public AppView(AppController controller, AppModel model, Stage primaryStage) {
        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;

        createCustViewTable();
        createAdminViewTable();
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

    private void createCustViewTable() {
        this.customerTripsView = new TableView<>();

        TableColumn<FerryTrip, String> ferryNameCol = new TableColumn<>("Ferry");
        ferryNameCol.setCellValueFactory(cellData -> cellData.getValue().getAssignedFerry().nameProperty());

        TableColumn<FerryTrip, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());

        TableColumn<FerryTrip, String> startingPointCol = new TableColumn<>("Starting Point");
        startingPointCol.setCellValueFactory(cellData -> cellData.getValue().startingPointProperty());

        TableColumn<FerryTrip, Double> tripPriceCol = new TableColumn<>("Price");
        tripPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        this.customerTripsView.getColumns().addAll(ferryNameCol, destinationCol, startingPointCol, tripPriceCol);
        this.customerTripsView.setItems(model.tripsProperty());
    }

    private void createAdminViewTable() {
        this.adminTripsView = new TableView<>();
        
        TableColumn<FerryTrip, String> ferryNameCol = new TableColumn<>("Ferry");
        ferryNameCol.setCellValueFactory(cellData -> cellData.getValue().getAssignedFerry().nameProperty());

        TableColumn<FerryTrip, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());

        TableColumn<FerryTrip, String> startingPointCol = new TableColumn<>("Starting Point");
        startingPointCol.setCellValueFactory(cellData -> cellData.getValue().startingPointProperty());

        TableColumn<FerryTrip, Double> basePriceCol = new TableColumn<>("Base Price");
        basePriceCol.setCellValueFactory(cellData -> cellData.getValue().basePriceProperty().asObject());

        TableColumn<FerryTrip, Double> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(cellData -> cellData.getValue().discountProperty().asObject());

        TableColumn<FerryTrip, Double> tripPriceCol = new TableColumn<>("Final Price");
        tripPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<FerryTrip, Double> revenueCol = new TableColumn<>("Revenue");

        TableColumn<FerryTrip, Integer> customerNumCol = new TableColumn<>("Number of Customers");
        customerNumCol.setCellValueFactory(cellData ->
            new SimpleIntegerProperty(cellData.getValue().customersProperty().size()).asObject()
        );

        this.adminTripsView.getColumns().addAll(ferryNameCol, destinationCol, startingPointCol,
            basePriceCol, discountCol, tripPriceCol, customerNumCol, revenueCol);
        this.adminTripsView.setItems(model.tripsProperty());
    }

    private void createAndLayoutControls() {
        //For now, all the admin buttons are below here
        Button viewDetailsBtn = new Button("View Details");
        
        Button addTripBtn = new Button("Add Trip");
        addTripBtn.setOnAction(e -> {
            createAddTripForm();
        });

        Button editTripBtn = new Button("Edit Trip");
        editTripBtn.setOnAction(e -> {
            createUpdateTripForm();
        });

        Button customerBtn = new Button("Customer");
        customerBtn.setOnAction(e -> {
            showCustomerPane();
        });

        Button adminBtn = new Button("Admin");
        adminBtn.setOnAction(e -> {
            showAdminPane();
        });

        // login view setup
        Label loginLabel = new Label("LOGIN AS");
        loginView.getChildren().addAll(loginLabel, customerBtn, adminBtn);

        // Admin view setup
        this.adminPane = new Stage();
        Button backToLoginBtnFromAdmin = new Button("Logout");
        backToLoginBtnFromAdmin.setOnAction(e -> {
            this.primaryStage.show();
            this.adminPane.close();
        });
        
        HBox adminControlMenu = new HBox(addTripBtn, backToLoginBtnFromAdmin);
        adminView.getChildren().addAll(adminControlMenu, this.adminTripsView);
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

        Button bookTripBtn = new Button("Book");
        bookTripBtn.setOnAction(null);

        Button checkHistoryBtn = new Button("Booking History");
        checkHistoryBtn.setOnAction(null);

        Button filterBtn = new Button("Filter");
        filterBtn.setOnAction(null);

        HBox customerControlMenu = new HBox(bookTripBtn, checkHistoryBtn, filterBtn, backToLoginBtnFromCust);
        customerView.getChildren().addAll(customerControlMenu, this.customerTripsView);
        
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

    //For now all the admin modal windows are here
    private void createAddTripForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Trip Form");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label text = new Label("Enter the starting point, the destination, and the price of the trip");
        Label warning = new Label();

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
            else {
                warning.setText("You have not filled out all the fields!");
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
        
        VBox root = new VBox(5, text, locationRow, BtnRow, warning);
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

        Label text = new Label("Enter the new starting point, the destination, and the price of the trip");


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

        Scene updateTripScene = new Scene(root, 550, 200);
        stage.setScene(updateTripScene);
        stage.show();
    }

    private void createAndConfigurePane() {
        loginView = new VBox(5);
        loginView.setAlignment(Pos.CENTER);

        customerView = new VBox(5);
        customerView.setAlignment(Pos.CENTER);

        adminView = new VBox(5);
        adminView.setAlignment(Pos.CENTER);
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
