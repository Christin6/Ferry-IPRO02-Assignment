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
    private Button loginBtn;
    private TableView<> ;

    private AppController controller;
    private AppModel model;
    private Stage primaryStage;
    
    public AppView(AppController controller, AppModel model, Stage primaryStage){
        this.controller = controller;
        this.model = model;

        createAndLayoutControls();
    }

    private void createAndLayoutControls(){
        
    }
}

