import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private TableView<FerryTrip> customerTripsView;
    private TableView<FerryTrip> adminTripsView;
    private ObservableList<FerryTrip> filteredTrips;

    // for filtering in customer view
    private String selectedStart;
    private String selectedDestination;
    private Double inputtedMaxPrice;
    private TableView <FerryTrip> customerTripsView;
    private TableView <FerryTrip> adminTripsView;

    private AppController controller;
    private AppModel model;
    private Stage primaryStage;

    public AppView(AppController controller, AppModel model, Stage primaryStage) {
        this.controller = controller;
        this.model = model;
        this.filteredTrips = FXCollections.observableArrayList(
                this.model.tripsProperty());
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
        this.customerTripsView.setItems(this.filteredTrips);
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

        //Customer View
        Button bookTripBtn = new Button("Book");
        bookTripBtn.setOnAction(e -> {
            int index = this.customerTripsView.getSelectionModel().getSelectedIndex();
            //Need a method to make booking
            createBookingForm();
        });

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

    //Customer Window
    private void createBookingForm() {
        Stage stage = new Stage();
        stage.setTitle("Booking Form");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label warning = new Label();

        TextField fName = new TextField();
        fName.setPromptText("Enter first name");
        TextField lName = new TextField();
        lName.setPromptText("Enter last name");
        TextField age = new TextField();
        age.setPromptText("Enter your age");
        configTextFieldForInts(age);

        //Toggle group for medical conditions
        Label medQuestion = new Label("Do you have any medical conditions we should be aware of?");
        ToggleGroup toggleGroup = new ToggleGroup();
        //Might need to change names for better clarity
        RadioButton yesBtn = new RadioButton("Yes");
        yesBtn.setToggleGroup(toggleGroup);

        RadioButton noBtn = new RadioButton("No");
        noBtn.setToggleGroup(toggleGroup);

        Button submitBtn = new Button("Submit");

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });

        HBox nameRow = new HBox(5, new Label("Name: "), fName, lName);
        nameRow.setAlignment(Pos.CENTER);

        HBox ageRow = new HBox(5, new Label("Age:"), age);
        ageRow.setAlignment(Pos.CENTER);

        HBox medQuestionRow = new HBox(5, yesBtn, noBtn);
        medQuestionRow.setAlignment(Pos.CENTER);

        HBox btnRow = new HBox(5, submitBtn, cancelBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, ageRow, medQuestion, medQuestionRow, warning, btnRow);
        root.setAlignment(Pos.CENTER);
        Scene bookingScene = new Scene(root, 500, 300);
        stage.setScene(bookingScene);
        stage.show();

        yesBtn.setOnAction(e -> {
            CheckBox seasickCBox = new CheckBox("Sea sick");
            CheckBox pregnantCBox = new CheckBox("Pregnant");
            CheckBox prmCBox = new CheckBox("Person with Reduced Mobility(PRM)");
            HBox medConditionRow = new HBox(5, seasickCBox, pregnantCBox, prmCBox);
            medConditionRow.setAlignment(Pos.CENTER);

            //Basically, we have to remove the warning and the btnRow
            //Before adding the medConditionRow

            for (int i = 0; i < root.getChildren().size(); i++) {
                if (root.getChildren().get(i).equals(btnRow)) {
                    root.getChildren().remove(i); //Remove the btnRow
                    root.getChildren().remove(i-1); //Remove the warning
                }
            }
            root.getChildren().addAll(medConditionRow, warning, btnRow);
        });

        noBtn.setOnAction(e -> {
            for (int i = root.getChildren().size() - 1; i > 0; i--) {
                if (root.getChildren().get(i).equals(btnRow)) {
                    //Remove only the medConditionRow
                    root.getChildren().remove(i-2);
                    break;
                }
            }
        });

        submitBtn.setOnAction(e -> {
            String fNameText = fName.getText().trim();
            String lNameText = lName.getText().trim();

            if (!fNameText.isEmpty() && !lNameText.isEmpty()) {
                System.out.println("It is working");
            } else {
                warning.setText("You have not filled out all the fields!");
            }
            //Need code here to put data
        });
    }

    private void applyFilters() {
        this.filteredTrips.clear();
        for (FerryTrip t : this.model.tripsProperty()) {
            // checking starting point
            boolean matchesStart = false;
            if (this.selectedStart == null || t.startingPointProperty().get().equals(this.selectedStart)) {
                matchesStart = true;
            }

            // checking destination
            boolean matchesDestination = false;
            if (selectedDestination == null || t.destinationProperty().get().equals(this.selectedDestination)) {
                matchesDestination = true;
            }

            // checking maximum price
            boolean matchesPrice = false;
            if (this.inputtedMaxPrice == null || t.priceProperty().get() <= this.inputtedMaxPrice) {
                matchesPrice = true;
            }

            // if everything matches, put trip to customer's table view
            if (matchesStart && matchesDestination && matchesPrice) {
                this.filteredTrips.add(t);
            }
        }
    }

    private void createFilterForm() {
        Stage stage = new Stage();
        stage.setTitle("Filter by");
        stage.initOwner(this.primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        // first row for destination and starting point filters
        Label filterByStartLabel = new Label("Filter by starting point: ");
        ToggleGroup filterByStartToggleGroup = new ToggleGroup();

        Label filterByDestinationLabel = new Label("Filter by destination: ");
        ToggleGroup filterByDestinationToggleGroup = new ToggleGroup();

        VBox filterByStartCol = new VBox(5, filterByStartLabel);
        VBox filterByDestinationCol = new VBox(5, filterByDestinationLabel);

        for (FerryTrip trip : this.model.tripsProperty()) {
            RadioButton startRadioBtn = new RadioButton(trip.startingPointProperty().get());
            startRadioBtn.setToggleGroup(filterByStartToggleGroup);
            startRadioBtn.setOnAction(e -> {
                this.selectedStart = trip.startingPointProperty().get();
                applyFilters();
            });
            filterByStartCol.getChildren().addAll(startRadioBtn);

            RadioButton destinationRadioBtn = new RadioButton(trip.destinationProperty().get());
            destinationRadioBtn.setToggleGroup(filterByDestinationToggleGroup);
            destinationRadioBtn.setOnAction(e -> {
                this.selectedDestination = trip.destinationProperty().get();
                applyFilters();
            });
            filterByDestinationCol.getChildren().addAll(destinationRadioBtn);
        }

        HBox firstRow = new HBox(5, filterByStartCol, filterByDestinationCol);
        firstRow.setAlignment(Pos.CENTER);

        // second row for maximum price
        Label maxPriceLabel = new Label("Maximum price: ");
        TextField maxPriceInput = new TextField("10000.0");
        configTextFieldForDoubles(maxPriceInput);
        maxPriceInput.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                this.inputtedMaxPrice = null;
            } else {
                this.inputtedMaxPrice = Double.parseDouble(newVal);
            }
            applyFilters();
        });

        HBox secondRow = new HBox(2, maxPriceLabel, maxPriceInput);
        secondRow.setAlignment(Pos.CENTER);

        // third row for modality options
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> {
            stage.close();
        });
        Button resetFilter = new Button("Reset");
        resetFilter.setOnAction(e -> {
            this.selectedStart = null;
            this.selectedDestination = null;
            this.inputtedMaxPrice = null;
            maxPriceInput.setText("");
            applyFilters();
        });

        HBox thirdRow = new HBox(2, closeBtn, resetFilter);
        thirdRow.setAlignment(Pos.CENTER);

        // set everything together
        VBox root = new VBox(5, firstRow, secondRow, thirdRow);

        Scene filterScene = new Scene(root, 500, 300);
        stage.setScene(filterScene);
        stage.show();
    }

    // Admin Windows
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

    //From Week 8 "Model-View-Controller" page 
    //but adjusted so there are no minus signs allowed
    private void configTextFieldForInts(TextField field) {
        field.setTextFormatter(new TextFormatter<Integer>((Change c) -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            }
            return null;
        }));
    }
}
