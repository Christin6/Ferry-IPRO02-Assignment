import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppView {
    // for base views (windows for each user)
    private VBox loginView;
    private VBox customerView;
    private VBox adminView;
    private Stage adminPane;
    private Stage customerPane;

    // for tables
    private TableView<FerryTrip> customerTripsView;
    private TableView<FerryTrip> adminTripsView;
    private TableView<Ferry> ferriesView;

    // for filtering in customer view
    private ObservableList<FerryTrip> filteredTrips;
    private String selectedStart;
    private String selectedDestination;
    private Double inputtedMaxPrice;

    // main parts
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
        createFerryTable();
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

    private void createFerryTable() {
        this.ferriesView = new TableView<>();

        TableColumn<Ferry, String> ferryNameCol = new TableColumn<>("Ferry");
        ferryNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ferryNameCol.setMinWidth(120.0);

        TableColumn<Ferry, Integer> maxSeatCol = new TableColumn<>("Destination");
        maxSeatCol.setCellValueFactory(cellData -> cellData.getValue().maxSeatsProperty().asObject());

        this.ferriesView.getColumns().addAll(ferryNameCol, maxSeatCol);
        this.ferriesView.setItems(this.model.ferriesProperty());
    }

    private void createCustViewTable() {
        this.customerTripsView = new TableView<>();

        TableColumn<FerryTrip, String> ferryNameCol = new TableColumn<>("Ferry");
        ferryNameCol.setCellValueFactory(cellData -> cellData.getValue().assignedFerryProperty().get().nameProperty());
        ferryNameCol.setMinWidth(120.0);

        TableColumn<FerryTrip, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        destinationCol.setMinWidth(120.0);

        TableColumn<FerryTrip, String> startingPointCol = new TableColumn<>("Starting Point");
        startingPointCol.setCellValueFactory(cellData -> cellData.getValue().startingPointProperty());
        startingPointCol.setMinWidth(120.0);

        TableColumn<FerryTrip, Double> tripPriceCol = new TableColumn<>("Price");
        tripPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        this.customerTripsView.getColumns().addAll(ferryNameCol, destinationCol, startingPointCol, tripPriceCol);
        this.customerTripsView.setItems(this.filteredTrips);
    }

    private void createAdminViewTable() {
        this.adminTripsView = new TableView<>();

        TableColumn<FerryTrip, String> ferryNameCol = new TableColumn<>("Ferry");
        ferryNameCol.setCellValueFactory(cellData -> cellData.getValue().assignedFerryProperty().get().nameProperty());
        ferryNameCol.setMinWidth(120.0);

        TableColumn<FerryTrip, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        destinationCol.setMinWidth(120.0);

        TableColumn<FerryTrip, String> startingPointCol = new TableColumn<>("Starting Point");
        startingPointCol.setCellValueFactory(cellData -> cellData.getValue().startingPointProperty());
        startingPointCol.setMinWidth(120.0);

        TableColumn<FerryTrip, Double> basePriceCol = new TableColumn<>("Base Price");
        basePriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<FerryTrip, Double> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(cellData -> cellData.getValue().discountProperty().asObject());

        TableColumn<FerryTrip, Double> tripPriceCol = new TableColumn<>("Final Price");
        tripPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<FerryTrip, Double> revenueCol = new TableColumn<>("Revenue");
        revenueCol.setCellValueFactory(cellData -> cellData.getValue().getCurrentRevenue().asObject());

        TableColumn<FerryTrip, Integer> customerNumCol = new TableColumn<>("Customers");
        customerNumCol.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().customersProperty().size()).asObject());

        this.adminTripsView.getColumns().addAll(ferryNameCol, destinationCol, startingPointCol,
                basePriceCol, discountCol, tripPriceCol, customerNumCol, revenueCol);
        this.adminTripsView.setItems(model.tripsProperty());
    }

    private void createAndLayoutControls() {
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
        loginLabel.setFont(new Font(24));
        loginView.getChildren().addAll(loginLabel, customerBtn, adminBtn);

        // Admin view setup
        this.adminPane = new Stage();

        Label adminWarning = new Label();

        Button backToLoginBtnFromAdmin = new Button("Logout");
        backToLoginBtnFromAdmin.setOnAction(e -> {
            this.primaryStage.show();
            this.adminPane.close();
        });

        Button assignDiscBtn = new Button("Assign Discount");
        assignDiscBtn.setOnAction(e -> {
            int index = this.adminTripsView.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                createDiscountForm(index);
            }
        });

        Button addTripBtn = new Button("Add Trip");
        addTripBtn.setOnAction(e -> {
            createAddTripForm();
        });

        Button editTripBtn = new Button("Edit Trip");
        editTripBtn.setOnAction(e -> {
            int index = this.adminTripsView.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                adminWarning.setText("You haven't selected any trip!");
            } else {
                createUpdateTripForm(index);
            }
        });

        Button removeTripBtn = new Button("Remove Trip");
        removeTripBtn.setOnAction(e -> {
            int index = this.adminTripsView.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                adminWarning.setText("You haven't selected any trip!");
            } else {
                this.controller.removeTrip(index);
                applyFilters();
            }
        });

        Button editFerryBtn = new Button("Edit Ferry List");
        editFerryBtn.setOnAction(e -> {
            createEditFerryListModal();
        });

        HBox adminControlMenu = new HBox(5, assignDiscBtn, addTripBtn, editTripBtn, removeTripBtn, editFerryBtn,
                backToLoginBtnFromAdmin);
        adminControlMenu.setAlignment(Pos.CENTER);

        adminView.getChildren().addAll(this.adminTripsView, adminControlMenu, adminWarning);
        Scene adminScene = new Scene(adminView, 800, 500);
        this.adminPane.setScene(adminScene);

        // Customer view setup
        this.customerPane = new Stage();

        Label custWarning = new Label();

        Button backToLoginBtnFromCust = new Button("Logout");
        backToLoginBtnFromCust.setOnAction(e -> {
            this.primaryStage.show();
            this.customerPane.close();
        });

        Button bookTripBtn = new Button("Book");
        bookTripBtn.setOnAction(e -> {
            int index = this.customerTripsView.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                custWarning.setText("You haven't selected any trip!");
            } else {
                createBookingForm(index);
            }
        });

        Button checkHistoryBtn = new Button("Booking History");
        checkHistoryBtn.setOnAction(e -> {
            createCustomerBookingList();
        });

        Button filterBtn = new Button("Filter");
        filterBtn.setOnAction(e ->

        createFilterForm());

        HBox customerControlMenu = new HBox(5, bookTripBtn, checkHistoryBtn, filterBtn, backToLoginBtnFromCust);
        customerControlMenu.setAlignment(Pos.CENTER);

        customerView.getChildren().addAll(this.customerTripsView, customerControlMenu, custWarning);

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

    // Customer Window
    private void createBookingForm(int index) {
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

        // Adult information
        TextField passportID = new TextField();
        passportID.setPromptText("Enter your passport number");

        // Children information
        TextField guardianFName = new TextField();
        guardianFName.setPromptText("Enter guardian first name");
        TextField guardianLName = new TextField();
        guardianLName.setPromptText("Enter guardian last name");

        // Toggle group for medical conditions
        Label medQuestion = new Label("Do you have any medical conditions we should be aware of?");
        ToggleGroup toggleGroupMed = new ToggleGroup();
        RadioButton yesMedBtn = new RadioButton("Yes");
        yesMedBtn.setToggleGroup(toggleGroupMed);

        RadioButton noMedBtn = new RadioButton("No");
        noMedBtn.setToggleGroup(toggleGroupMed);

        // CheckBox for medical conditions
        CheckBox seasickCBox = new CheckBox("Sea sick");
        CheckBox pregnantCBox = new CheckBox("Pregnant");
        CheckBox prmCBox = new CheckBox("Person with Reduced Mobility(PRM)");

        Button submitBtn = new Button("Submit");

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });

        HBox nameRow = new HBox(5, new Label("Name: "), fName, lName);
        nameRow.setAlignment(Pos.CENTER);

        HBox ageRow = new HBox(5, new Label("Age:"), age);
        ageRow.setAlignment(Pos.CENTER);

        HBox ageAdultRow = new HBox(5, new Label("Passport Number:"), passportID);
        ageAdultRow.setAlignment(Pos.CENTER);

        HBox ageChildRow = new HBox(5, new Label("Guardian Name:"), guardianFName, guardianLName);
        ageChildRow.setAlignment(Pos.CENTER);

        HBox medQuestionRow = new HBox(5, yesMedBtn, noMedBtn);
        medQuestionRow.setAlignment(Pos.CENTER);

        HBox btnRow = new HBox(5, submitBtn, cancelBtn);
        btnRow.setAlignment(Pos.CENTER);

        HBox medConditionRow = new HBox(5, seasickCBox, pregnantCBox, prmCBox);
        medConditionRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, ageRow, medQuestion, medQuestionRow, warning, btnRow);
        root.setAlignment(Pos.CENTER);
        Scene bookingScene = new Scene(root, 500, 300);
        stage.setScene(bookingScene);
        stage.show();

        age.setOnAction(e -> {
            // We need to remove some rows to add new rows
            for (int i = root.getChildren().size() - 1; i > 0; i--) {
                if (root.getChildren().get(i).equals(ageAdultRow) || root.getChildren().get(i).equals(ageChildRow)) {
                    root.getChildren().remove(i);
                } else if (root.getChildren().get(i).equals(btnRow)) {
                    root.getChildren().remove(i);
                } else if (root.getChildren().get(i).equals(warning)) {
                    root.getChildren().remove(i);
                } else if (root.getChildren().get(i).equals(medQuestion)) {
                    root.getChildren().remove(i);
                } else if (root.getChildren().get(i).equals(medQuestionRow)) {
                    root.getChildren().remove(i);
                } else if (root.getChildren().get(i).equals(medConditionRow)) {
                    root.getChildren().remove(i);
                }
            }

            if (convertStringToInt(age.getText().trim()) >= 18) {
                root.getChildren().addAll(ageAdultRow, medQuestion, medQuestionRow);
                if (yesMedBtn.isSelected()) {
                    root.getChildren().addAll(medConditionRow, warning, btnRow);
                } else {
                    root.getChildren().addAll(warning, btnRow);
                }

            } else {
                root.getChildren().addAll(ageChildRow, medQuestion, medQuestionRow);
                if (yesMedBtn.isSelected()) {
                    root.getChildren().addAll(medConditionRow, warning, btnRow);
                } else {
                    root.getChildren().addAll(warning, btnRow);
                }
            }
        });

        // Medical Button Events
        yesMedBtn.setOnAction(e -> {
            // Basically, we have to remove the warning and the btnRow
            // Before adding the medConditionRow
            for (int i = 0; i < root.getChildren().size(); i++) {
                if (root.getChildren().get(i).equals(btnRow)) {
                    root.getChildren().remove(i); // Remove the btnRow
                    root.getChildren().remove(i - 1); // Remove the warning
                }
            }
            root.getChildren().addAll(medConditionRow, warning, btnRow);
        });

        noMedBtn.setOnAction(e -> {
            for (int i = root.getChildren().size() - 1; i > 0; i--) {
                if (root.getChildren().get(i).equals(medConditionRow)) {
                    // Remove only the medConditionRow
                    root.getChildren().remove(i);
                    break;
                }
            }
        });

        submitBtn.setOnAction(e -> {
            String fNameText = fName.getText().trim();
            String lNameText = lName.getText().trim();
            String ageText = age.getText().trim();
            String passportIDText = passportID.getText().trim();
            ArrayList<MedicalCondition> medicalCondition = new ArrayList<>();

            int ageNum = convertStringToInt(age.getText().trim());

            // FIX CAUSE IT CANNOT DETECT SELECTING HEALTH YET!!!
            if (!fNameText.isEmpty() && !lNameText.isEmpty() && !ageText.isEmpty() && noMedBtn.isSelected()
                    || !fNameText.isEmpty() && !lNameText.isEmpty() && !ageText.isEmpty() && yesMedBtn.isSelected()) {
                // Medical Condition checkbox
                if (yesMedBtn.isSelected()) {
                    if (seasickCBox.isSelected()) {
                        medicalCondition.add(MedicalCondition.SEA_SICK);
                    } else if (pregnantCBox.isSelected()) {
                        medicalCondition.add(MedicalCondition.PREGNANT);
                    } else if (prmCBox.isSelected()) {
                        medicalCondition.add(MedicalCondition.SPECIAL_DISABILITY);
                    }
                } else {
                    medicalCondition.add(MedicalCondition.HEALTHY);
                }

                if (ageNum >= 18) {
                    AdultCustomer adultCustomer = new AdultCustomer(fNameText, lNameText, ageNum, passportIDText,
                            medicalCondition);

                    FerryTrip ferryTrip = model.tripsProperty().get(index);
                    this.controller.createBooking(adultCustomer, ferryTrip);
                } else { // Work on child class later
                    ChildCustomer childCustomer = new ChildCustomer(fNameText, lNameText, ageNum, null,
                            medicalCondition);
                }

                // force update admin's view to make sure its ferry column is updated
                this.controller.setFerryTripList(this.model.tripsProperty().get(index), index);

                stage.close();
            } else {
                warning.setText("You have not filled out all the fields!");
            }
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
            maxPriceInput.setText("10000.0");
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

    // show customer booking list
    private void createCustomerBookingList() {
        Stage stage = new Stage();
        stage.setTitle("Identity Verification");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label text = new Label("Enter your name listed inside the booking:");
        text.setAlignment(Pos.CENTER);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        HBox firstNameFieldRow = new HBox(5, new Label("Enter first name: "), firstNameField);
        firstNameFieldRow.setAlignment(Pos.CENTER);

        HBox lastNameFieldRow = new HBox(5, new Label("Enter last name: "), lastNameField);
        lastNameFieldRow.setAlignment(Pos.CENTER);

        ListView<FerryTrip> tripList = new ListView();

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String firstNameText = firstNameField.getText().trim();
            String lastNameText = lastNameField.getText().trim();
            String customerName = firstNameText + " " + lastNameText;

            ArrayList<FerryTrip> bookedTrip = new ArrayList<>();
            bookedTrip = this.model.customerBookedTrip(customerName);

            ObservableList<FerryTrip> bookedTripView = FXCollections.observableArrayList(bookedTrip);
            tripList.setItems(bookedTripView);
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });

        HBox buttonRow = new HBox(10, submitBtn, cancelBtn);
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, text, firstNameFieldRow, lastNameFieldRow, buttonRow, tripList);
        root.setAlignment(Pos.CENTER);

        Scene listScene = new Scene(root, 500, 300);
        stage.setScene(listScene);
        stage.show();
    }

    // Admin Windows
    private void createAddTripForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Trip Form");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        final Ferry[] selectedFerry = { null }; // store selected ferry from radio buttons
        // using array because lambda only accepts final local variable
        // the array reference is final, but the inside is changeable
        Label warning = new Label();

        Label startingPointLabel = new Label("Starting point: ");
        TextField startingPointTextField = new TextField();
        startingPointTextField.setPromptText("Add starting point");

        HBox startingPointInputRow = new HBox(5, startingPointLabel, startingPointTextField);
        startingPointInputRow.setAlignment(Pos.CENTER);

        Label destinationLabel = new Label("Destination: ");
        TextField destinationTextField = new TextField();
        destinationTextField.setPromptText("Add destination");

        HBox destinationInputRow = new HBox(5, destinationLabel, destinationTextField);
        destinationInputRow.setAlignment(Pos.CENTER);

        Label basePriceLabel = new Label("Price: $");
        TextField basePriceTextField = new TextField();
        basePriceTextField.setPromptText("Add price");
        configTextFieldForDoubles(basePriceTextField);

        HBox priceInputRow = new HBox(5, basePriceLabel, basePriceTextField);
        priceInputRow.setAlignment(Pos.CENTER);

        Label ferryInputRow = new Label("Choose the ferry to assign the trip to: ");

        ToggleGroup assignFerryToggleGroup = new ToggleGroup();

        VBox assignFerryCol = new VBox(5, ferryInputRow);
        assignFerryCol.setAlignment(Pos.CENTER);

        for (Ferry ferry : this.model.ferriesProperty()) {
            RadioButton ferryRadioBtn = new RadioButton(ferry.nameProperty().get());
            ferryRadioBtn.setToggleGroup(assignFerryToggleGroup);
            ferryRadioBtn.setOnAction(e -> {
                selectedFerry[0] = ferry;
            });
            assignFerryCol.getChildren().addAll(ferryRadioBtn);
        }

        Button submitBtn = new Button("Add");
        submitBtn.setOnAction(e -> {
            if (!startingPointTextField.getText().isEmpty() && !destinationTextField.getText().isEmpty()
                    && !basePriceTextField.getText().isEmpty() && selectedFerry[0] != null) {
                String destination = destinationTextField.getText();
                String startingPoint = startingPointTextField.getText();
                double basePrice = Double.parseDouble(basePriceTextField.getText());

                FerryTrip ferryTrip = new FerryTrip(destination, startingPoint, basePrice, selectedFerry[0]);

                this.controller.addTrip(ferryTrip);
                applyFilters(); // update customer's view
                stage.close();
            } else {
                warning.setText("You have not filled out all the fields!");
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });

        HBox BtnRow = new HBox(5, submitBtn, cancelBtn);
        BtnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, startingPointInputRow, destinationInputRow, priceInputRow, assignFerryCol, warning,
                BtnRow);
        root.setAlignment(Pos.CENTER);

        Scene addTripScene = new Scene(root, 550, 450);
        stage.setScene(addTripScene);
        stage.show();
    }

    private void createUpdateTripForm(int index) {
        Stage stage = new Stage();
        stage.setTitle("Update Trip Form");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        FerryTrip selectedTrip = this.model.tripsProperty().get(index);

        final Ferry[] selectedFerry = { selectedTrip.assignedFerryProperty().get() }; // store selected ferry from
                                                                                      // radioButtons
        Label warning = new Label();

        Label startingPointLabel = new Label("Starting point: ");
        TextField startingPointTextField = new TextField(selectedTrip.startingPointProperty().get());

        HBox startingPointInputRow = new HBox(5, startingPointLabel, startingPointTextField);
        startingPointInputRow.setAlignment(Pos.CENTER);

        Label destinationLabel = new Label("Destination: ");
        TextField destinationTextField = new TextField(selectedTrip.destinationProperty().get());

        HBox destinationInputRow = new HBox(5, destinationLabel, destinationTextField);
        destinationInputRow.setAlignment(Pos.CENTER);

        Label basePriceLabel = new Label("Price: $");
        TextField basePriceTextField = new TextField("" + selectedTrip.basePriceProperty().get());
        configTextFieldForDoubles(basePriceTextField);

        HBox priceInputRow = new HBox(5, basePriceLabel, basePriceTextField);
        priceInputRow.setAlignment(Pos.CENTER);

        Label ferryInputRow = new Label("Choose the ferry to assign the trip to: ");

        ToggleGroup assignFerryToggleGroup = new ToggleGroup();

        VBox assignFerryCol = new VBox(5, ferryInputRow);
        assignFerryCol.setAlignment(Pos.CENTER);

        for (Ferry ferry : this.model.ferriesProperty()) {
            RadioButton ferryRadioBtn = new RadioButton(ferry.nameProperty().get());
            ferryRadioBtn.setToggleGroup(assignFerryToggleGroup);
            ferryRadioBtn.setOnAction(e -> {
                selectedFerry[0] = ferry;
            });
            assignFerryCol.getChildren().addAll(ferryRadioBtn);
        }

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            if (!startingPointTextField.getText().isEmpty() && !destinationTextField.getText().isEmpty()
                    && !basePriceTextField.getText().isEmpty() && selectedFerry[0] != null) {
                String destination = destinationTextField.getText();
                String startingPoint = startingPointTextField.getText();
                double basePrice = Double.parseDouble(basePriceTextField.getText());

                this.controller.updateTrip(index, destination, startingPoint, basePrice, selectedFerry[0]);
                applyFilters(); // update customer's view
                // force update admin's view to make sure its ferry column is updated
                this.controller.setFerryTripList(this.model.tripsProperty().get(index), index);

                stage.close();
            } else {
                warning.setText("You have not filled out all the fields!");
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });

        HBox BtnRow = new HBox(5, submitBtn, cancelBtn);
        BtnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, startingPointInputRow, destinationInputRow, priceInputRow, assignFerryCol, warning,
                BtnRow);
        root.setAlignment(Pos.CENTER);

        Scene updateTripScene = new Scene(root, 550, 350);
        stage.setScene(updateTripScene);
        stage.show();
    }

    // Admin's ferry control
    private void createEditFerryListModal() {
        Stage stage = new Stage();
        stage.setTitle("Edit Ferry List");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label warning = new Label();

        Button addFerryBtn = new Button("Add Ferry");
        addFerryBtn.setOnAction(e -> createAddFerryModal());

        Button editFerryBtn = new Button("Edit Ferry");
        editFerryBtn.setOnAction(e -> {
            int index = this.ferriesView.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                warning.setText("You haven't selected any ferry!");
            } else {
                createEditFerryModal(index);
            }
        });

        Button removeFerryBtn = new Button("Remove Ferry");
        removeFerryBtn.setOnAction(e -> {
            int index = this.ferriesView.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                warning.setText("You haven't selected any ferry!");
            } else {
                createFerryRemovalConfirmationModal(index);
            }
        });

        HBox controlMenu = new HBox(5, addFerryBtn, editFerryBtn, removeFerryBtn);
        controlMenu.setAlignment(Pos.CENTER);

        VBox root = new VBox(this.ferriesView, controlMenu, warning);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 550, 350);
        stage.setScene(scene);
        stage.show();
    }

    private void createAddFerryModal() {
        Stage stage = new Stage();
        stage.setTitle("Add Ferry");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label nameInputLabel = new Label("Ferry Name: ");
        TextField nameInput = new TextField("Ferry" + this.model.ferriesProperty().size());

        HBox nameRow = new HBox(5, nameInputLabel, nameInput);
        nameRow.setAlignment(Pos.CENTER);

        Label maxSeatsInputLabel = new Label("Maximum Seats: ");
        TextField maxSeatsInput = new TextField("1");
        configTextFieldForInts(maxSeatsInput);

        HBox maxSeatsRow = new HBox(5, maxSeatsInputLabel, maxSeatsInput);
        maxSeatsRow.setAlignment(Pos.CENTER);

        Label warning = new Label();

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            if (!nameInput.getText().isEmpty() && !maxSeatsInput.getText().isEmpty()) {
                String ferryName = nameInput.getText();
                int ferryMaxSeats = Integer.parseInt(maxSeatsInput.getText());
                Ferry newFerry = new Ferry(ferryName, ferryMaxSeats);
                this.controller.addFerry(newFerry);
                stage.close();
            } else {
                warning.setText("You have not filled out all the fields!");
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> stage.close());

        HBox windowControlRow = new HBox(5, submitBtn, cancelBtn);
        windowControlRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, maxSeatsRow, warning, windowControlRow);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 350, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void createEditFerryModal(int index) {
        Stage stage = new Stage();
        stage.setTitle("Edit Ferry");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label confirmationLabel = new Label("IMPORTANT NOTE: by editing "
                + this.model.ferriesProperty().get(index).nameProperty().get()
                + " you will be editing ferry data of:");

        VBox tripsContainer = new VBox(confirmationLabel);
        tripsContainer.setAlignment(Pos.CENTER);

        for (FerryTrip trip : this.model.searchTripsInFerry(index)) {
            Label tripLabel = new Label("-) Ferry trip from " + trip.startingPointProperty().get()
                    + " to " + trip.destinationProperty().get());

            tripsContainer.getChildren().addAll(tripLabel);
        }

        Label nameInputLabel = new Label("Ferry Name: ");
        TextField nameInput = new TextField(
                this.model.ferriesProperty().get(index).nameProperty().get());

        HBox nameRow = new HBox(5, nameInputLabel, nameInput);
        nameRow.setAlignment(Pos.CENTER);

        Label maxSeatsInputLabel = new Label("Maximum Seats: ");
        TextField maxSeatsInput = new TextField("" + // "" added to convert int to string
                this.model.ferriesProperty().get(index).maxSeatsProperty().get());
        configTextFieldForInts(maxSeatsInput);

        HBox maxSeatsRow = new HBox(5, maxSeatsInputLabel, maxSeatsInput);
        maxSeatsRow.setAlignment(Pos.CENTER);

        Label warning = new Label();

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            if (!nameInput.getText().isEmpty() && !maxSeatsInput.getText().isEmpty()) {
                String newName = nameInput.getText();
                int newMaxSeat = Integer.parseInt(maxSeatsInput.getText());
                this.controller.updateFerry(index, newName, newMaxSeat);
                stage.close();
            } else {
                warning.setText("You have not filled out all the fields!");
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> stage.close());

        HBox windowControlRow = new HBox(5, submitBtn, cancelBtn);
        windowControlRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, tripsContainer, nameRow, maxSeatsRow, warning, windowControlRow);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 550, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void createFerryRemovalConfirmationModal(int index) {
        Stage stage = new Stage();
        stage.setTitle("Remove Ferry");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label confirmationLabel = new Label("By removing "
                + this.model.ferriesProperty().get(index).nameProperty().get()
                + " you will be removing data of:");

        VBox tripsContainer = new VBox(confirmationLabel);
        tripsContainer.setAlignment(Pos.CENTER);

        for (FerryTrip trip : this.model.searchTripsInFerry(index)) {
            Label tripLabel = new Label("-) Ferry trip from " + trip.startingPointProperty().get()
                    + " to " + trip.destinationProperty().get() + ", with revenue of "
                    + trip.getCurrentRevenue().get() + ", and filled with " + trip.customersProperty().size()
                    + " customers ");

            tripsContainer.getChildren().addAll(tripLabel);
        }

        Button okayBtn = new Button("Yes");
        okayBtn.setOnAction(e -> {
            this.controller.removeTripInFerry(index); // remove the trips first
            this.controller.removeFerry(index); // then remove the ferry
            // trips is removed first because if the ferry is removed before trips,
            // the indexing will remove the trips with the next ferry after the removed
            // ferry
            applyFilters(); // update customer's view
            stage.close();
        });

        Button noBtn = new Button("No");
        noBtn.setOnAction(e -> stage.close());

        HBox confirmationBtnsRow = new HBox(5, okayBtn, noBtn);
        confirmationBtnsRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, tripsContainer, confirmationBtnsRow);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 550, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void createDiscountForm(int index) {
        Stage stage = new Stage();
        stage.setTitle("Edit Discount");

        Label label = new Label("Input discount amount: ");
        
        TextField discountInput = new TextField();
        discountInput.setPromptText("Discount amount");
        configTextFieldForDoubles(discountInput);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String adminInput = discountInput.getText().trim();

            boolean assignDiscount = !adminInput.isEmpty();

            if (assignDiscount) {
                FerryTrip trip = this.model.tripsProperty().get(index);
                this.controller.setDiscount(Double.parseDouble(adminInput), trip);
                stage.close();
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            stage.close();
        });

        HBox firstRow = new HBox(10, label, discountInput);
        firstRow.setAlignment(Pos.CENTER);

        HBox buttonRow = new HBox(10, submitBtn, cancelBtn);
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, firstRow, buttonRow);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    // other configurations
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

    // From Week 8 "Model-View-Controller" page
    // but adjusted so there are no minus signs allowed
    private void configTextFieldForInts(TextField field) {
        field.setTextFormatter(new TextFormatter<Integer>((Change c) -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            }
            return null;
        }));
    }

    // Temporary
    public int convertStringToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if ("-".equals(s)) {
            return 0;
        }
        return Integer.parseInt(s); // Convert string into integer
    }
}
