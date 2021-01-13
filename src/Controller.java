import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.ResourceBundle;

//This is the main controller. Methods common to controllers of all
//pages will be her, extend this controller for every other controller you make

public class Controller implements Initializable {
    @FXML
    private VBox root;
    public MenuBar menuBar;
    public Menu optionsMenu;
    public MenuItem roleChooser;
    public MenuItem saveAndExit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //take an arraylist
        //create vbox
        //in a loop, propertyTable.getChildren().add()
    }

    @FXML
    public void changeScene(ActionEvent e) throws IOException {
        MenuItem clickedButton = (MenuItem) e.getSource();
        System.out.println(clickedButton.getId());
        Stage stage = (Stage) root.getScene().getWindow();
        if(clickedButton.getId().equals("roleChooser")) {
            Parent root = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
            Scene roleChooser = new Scene(root, 700, 600);
            stage.setScene(roleChooser);
        } else if(clickedButton.getId().equals("saveAndExit")) {
            System.out.println("Graceful exit");
        }
    }

    public void saveTenantsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Tenant> tenants = UserFactory.tenants;
        for (int i = 0; i < tenants.size(); i++) {
            sb.append(tenants.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/tenants.csv"), sb.toString().getBytes());
    }

    public void saveOwnersToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Owner> owners = UserFactory.owners;
        for (int i = 0; i < owners.size(); i++) {
            sb.append(owners.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/owners.csv"), sb.toString().getBytes());
    }

    public void saveAgentsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Agent> agents = UserFactory.agents;
        for (int i = 0; i < agents.size(); i++) {
            sb.append(agents.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }

    public void savePropertyToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Property> propertyList = PropertySearchFacade.getProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }




    //add Property Method
    //load data inside (reads all the file store, loads the users, property)
    //load data into files (save at the end)
    //login
        //if wrong login, set Error Message of Main class, and Main.showLoginScreen()
        //check details (cross check with CSV)
        //Main.name = ''
        //Main.role = ''
        //Main.showDashboard

    //register
        //create user
        //Main.name = ''
        //Main.role = ''


    //search
    //searchProperty
//    Main.propertyToDisplay = PropertySearchFacade.getByActive();
}
