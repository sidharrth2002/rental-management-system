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

    public Controller() {
        System.out.println("Main Controller");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //take an arraylist
        //create vbox
        //in a loop, propertyTable.getChildren().add()
    }

    //method to change scene from the menu
    @FXML
    public void changeScene(ActionEvent e) throws IOException {
        MenuItem clickedButton = (MenuItem) e.getSource();
        System.out.println(clickedButton.getId());
        Stage stage = (Stage) root.getScene().getWindow();
        if (clickedButton.getId().equals("login")) {
            Parent loginfxml = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
            Scene loginPage = new Scene(loginfxml, 700, 600);
            stage.setScene(loginPage);
        } else if(clickedButton.getId().equals("saveAndExit")) {
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

    public void saveTenantsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getTenants().size(); i++) {
            sb.append(userFactory.getTenants().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/tenants.csv"), sb.toString().getBytes());
    }

    public void saveOwnersToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getOwners().size(); i++) {
            sb.append(userFactory.getOwners().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/owners.csv"), sb.toString().getBytes());
    }

    public void saveAgentsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getAgents().size(); i++) {
            sb.append(userFactory.getAgents().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }

    public void savePropertyToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Property> propertyList = propertySearchFacade.getProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }

//            sb.append("\"" + getID() + "\"" + ",")
//            .append("\"" + getName() + "\"" + ",")
//            .append("\"" + getAddress() + "\"" + ",")
//            .append("\"" + getDescription() + "\"" + ",")
//            .append("\"" + getPhoto() + "\"" + ",")
//            .append("\"" + getPrice() + "\"" + ",")
//            .append("\"" + getRating() + "\"" + ",")
//            .append("\"" + getInitialMarketDate() + "\"" + ",")
//            .append("\"" + getStatus() + "\"" + ",")
//            .append("\"" + getAssigned() + "\"");


    public void getPropertyToFile() throws IOException, ParseException {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        List<String> lines = Files.readAllLines(Paths.get("./data/property.csv"));
        for(int i = 0; i < lines.size(); i++) {
            String[] dataInFile = lines.get(i).split(",");
            String ID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String address = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String description = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            String photo = dataInFile[4].substring(1, dataInFile[4].length() - 1);
            double price = Double.parseDouble(dataInFile[5].substring(1, dataInFile[5].length() - 1));
            String rating = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            Date initialMarketRate = format.parse(dataInFile[7].substring(1, dataInFile[7].length() - 1));
            boolean status = Boolean.parseBoolean(dataInFile[8].substring(1, dataInFile[8].length() - 1));
            boolean assigned = Boolean.parseBoolean(dataInFile[9].substring(1, dataInFile[9].length() - 1));
        }
    }
}
