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
        System.out.println("Main Controller");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //method to change scene from the menu
    @FXML
    public void changeScene(ActionEvent e) throws IOException {
        String ID = "";
        Object clickedButton = e.getSource();
        if(clickedButton instanceof MenuItem) {
            ID = ((MenuItem) clickedButton).getId();
        } else if (clickedButton instanceof Button) {
            ID = ((Button) clickedButton).getId();
        }
        System.out.println(ID);
        if (ID.equals("login")) {
            Parent loginfxml = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
            Scene loginPage = new Scene(loginfxml, 700, 600);
            stage.setScene(loginPage);
        } else if(ID.equals("roleChooserPage")) {
            Parent roleChooserfxml = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
            Scene roleChooser = new Scene(roleChooserfxml, 700, 600);
            stage.setScene(roleChooser);
        } else if(ID.equals("agentOwnerPage")) {
            Parent agentOwnerfxml = FXMLLoader.load(getClass().getResource("agentOwnerScreen.fxml"));
            Scene agentOwnerScreen = new Scene(agentOwnerfxml, 700, 600);
        } else if(ID.equals("saveAndExit")) {
            System.out.println("Graceful exit");
        }
    }

    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) throws IOException {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
