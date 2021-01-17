import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController extends Controller implements Initializable {

    public VBox propertyTable;
    public VBox menuArea;
    public ArrayList<Property> properties = propertySearchFacade.getProperties();
    public ArrayList<Owner> owners = UserFactory.getInstance().getOwners();
    public ArrayList<Tenant> tenants = UserFactory.getInstance().getTenants();
    public ArrayList<Agent> agents = UserFactory.getInstance().getAgents();


    //todo:
    // need to delete property if user is deleted
    // add tenants and agents in users tab




    //show Users:
    public void sortByUsers(ActionEvent e) {
        propertyTable.getChildren().clear();

        //options:
        HBox options = new HBox();
        options.getChildren().add(new Text("More User Options:"));

        // add user button:
        Button adduser = new Button("Add User");
        options.getChildren().add(adduser);
        adduser.setOnAction(event ->{
            try {
                Stage stage = (Stage) root.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("ownerRegister.fxml"));
                Scene RegisterPage = new Scene(root, 700, 600);
                stage.setScene(RegisterPage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        options.setSpacing(10);
        options.setPadding(new Insets(10, 10, 10, 10));
        propertyTable.getChildren().add(options);


        // show users:
        for (Owner owner : owners) {
            HBox outerBox = new HBox();
            String currentOwner = owner.getUserID();
            outerBox.setSpacing(10);
            outerBox.setAlignment(Pos.CENTER_LEFT);
            outerBox.setPadding(new Insets(10, 10, 10, 10));
            VBox tableEntry = new VBox();
            // User Info:
            tableEntry.setPadding(new Insets(10, 10, 10, 10));
            tableEntry.getChildren().add(new Text("Owner Name: "+ owner.getName()));
            tableEntry.getChildren().add(new Text("Owner ID: "+ owner.getUserID()));
            // edit User button
            Button seeMoreu = new Button("View Details");
            tableEntry.getChildren().add(seeMoreu);
            seeMoreu.setOnAction(event -> {
                // go to edit page
//                try {
//                    Stage stage = (Stage) root.getScene().getWindow();
//                    Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
//                    Scene propertyPage = new Scene(root, 700, 600);
//                    stage.setScene(propertyPage);
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            });

            // delete User button
            Button deleteu = new Button("Delete");
            deleteu.setOnAction(event ->{
                owners.remove(owner);
                propertyTable.getChildren().clear();

            });
            tableEntry.getChildren().add(deleteu);

            tableEntry.setSpacing(10);

            // Property by User:
            tableEntry.getChildren().add(new Text("Properties:---------"));
            for (Property property: properties) {
                if (property.getOwner().getUserID().equals(currentOwner)){
                    tableEntry.getChildren().add(new Text("Title: "+property.getName()));
                    tableEntry.getChildren().add(new Text("Type: "+property.getType()));
                    tableEntry.getChildren().add(new Text("Address: "+property.getAddress()));
                    // edit property button
                    Button seeMore = new Button("View Details");
                    tableEntry.getChildren().add(seeMore);
                    seeMore.setOnAction(event -> {
                        PropertyPageController.propertyToDisplay = property;
                        try {
                            Stage stage = (Stage) root.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                            Scene propertyPage = new Scene(root, 700, 600);
                            stage.setScene(propertyPage);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    // delete property button
                    Button delete = new Button("Delete");
                    delete.setOnAction(event ->{
                        properties.remove(property);
                        propertyTable.getChildren().clear();

                    });
                    tableEntry.getChildren().add(delete);
                }


            }

            outerBox.getChildren().add(tableEntry);
            propertyTable.getChildren().add(outerBox);

        }

    }



    // show properties:
    public void sortByProperty(ActionEvent e) {
        propertyTable.getChildren().clear();

        //options:
        HBox options = new HBox();
        options.getChildren().add(new Text("More Property Options:"));
        Button getByActiveb = new Button("Get By Active");
        Button getByInactiveb = new Button("Get By Inactive");
        Button getByAssignedb = new Button("Get By Assigned");
        Button getByUnassignedb = new Button("Get By Unassigned");
        Button getByTypeb = new Button("Get By Type");
        options.getChildren().addAll(getByActiveb,getByInactiveb,getByAssignedb,getByUnassignedb,getByTypeb);
        options.setSpacing(10);
        options.setPadding(new Insets(10, 10, 10, 10));
        propertyTable.getChildren().add(options);

        //print properties:
        for (Property property : properties) {
            HBox outerBox = new HBox();
            outerBox.setSpacing(10);
            outerBox.setAlignment(Pos.CENTER_LEFT);
            outerBox.setPadding(new Insets(10, 10, 10, 10));
            VBox tableEntry = new VBox();
            tableEntry.setPadding(new Insets(10, 10, 10, 10));
            tableEntry.getChildren().add(new Text(property.getName()));
            tableEntry.getChildren().add(new Text(property.getAddress()));
            tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
            outerBox.getChildren().add(tableEntry);
            // view property button
            Button seeMore = new Button("View Details");
            seeMore.setOnAction(event -> {
                PropertyPageController.propertyToDisplay = property;
                try {
                    Stage stage = (Stage) root.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                    Scene propertyPage = new Scene(root, 700, 600);
                    stage.setScene(propertyPage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            // delete User button
            Button deletep = new Button("Delete");
            deletep.setOnAction(event ->{
                properties.remove(property);
                propertyTable.getChildren().clear();

            });
            outerBox.getChildren().add(deletep);
            outerBox.getChildren().add(seeMore);
            propertyTable.getChildren().add(outerBox);
        }

    }



    //show reports:
    public void showreports(ActionEvent e) {
        propertyTable.getChildren().clear();

        propertyTable.getChildren().add(new Text("Coming Soon"));

    }



}

