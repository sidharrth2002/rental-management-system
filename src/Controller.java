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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
