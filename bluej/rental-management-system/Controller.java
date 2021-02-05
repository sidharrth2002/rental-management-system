
//This was written by Sidharrth Nagappan and Ahmed Sanad
//1181102313 , 1181102208

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

//This is the main controller. Methods common to controllers of all
//pages will be her, extend this controller for every other controller you make

public class Controller implements Initializable {
    public Parent root;
    public MenuBar menuBar;
    public Menu optionsMenu;
    public MenuItem roleChooser;
    public MenuItem saveAndExit;
    public MenuItem registerPage;
    public MenuItem loginPage;
    public PropertySearchFacade propertySearchFacade = PropertySearchFacade.getInstance();
    public UserFactory userFactory = UserFactory.getInstance();
    public static Stage stage;

    public Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //method to change scene from the menu bar
    //not the only method that handles navigation though
    @FXML
    public void changeScene(ActionEvent e) throws IOException {
        String ID = "";
        Object clickedButton = e.getSource();
        if(clickedButton instanceof MenuItem) {
            ID = ((MenuItem) clickedButton).getId();
        } else if (clickedButton instanceof Button) {
            ID = ((Button) clickedButton).getId();
        }
        switch (ID) {
            case "login":
            case "roleChooserPage":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("roleChooser.fxml"));
                loader.setController(new RoleChooserController());
                Scene scene = new Scene(loader.load(), 700, 600);
                stage.setScene(scene);
                break;
            case "agentOwnerPage":
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("agentOwnerScreen.fxml"));
                loader2.setController(new AgentOwnerScreenController());
                Scene scene2 = new Scene(loader2.load(), 700, 600);
                stage.setScene(scene2);
                break;
            case "searchArea":
            case "backToDashboard":
                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                loader3.setController(new VisitorDashboardController());
                Scene scene3 = new Scene(loader3.load(), 700, 600);
                stage.setScene(scene3);
                break;
        }
    }

    //method used to show an alert with a custom message
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) throws IOException {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    //clean logout
    //removes user object from the stage and redirects to role chooser
    public void logout() {
        stage.setUserData(null);
        Parent agentOwnerfxml = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("roleChooser.fxml"));
            loader.setController(new RoleChooserController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
