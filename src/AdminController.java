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
    public ArrayList<Property> propertiesMain = propertySearchFacade.getProperties();
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
        options.getChildren().add(new Text("Show By:"));

        options.setSpacing(10);
        options.setPadding(new Insets(10, 10, 10, 10));
        propertyTable.getChildren().add(options);

        //show Owners only:
        Button showOwners = new Button("Owners");
        showOwners.setOnAction(c ->{
            propertyTable.getChildren().clear();
            propertyTable.getChildren().add(options);
            for (Owner owner : owners) {
                HBox outerBox = new HBox();
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
                //redirect to user dashboard
                seeMoreu.setOnAction(event -> {
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
                tableEntry.getChildren().add(new Text("Properties:____________________________________"));
                ArrayList <Property> properties = owner.getPropertyList();
                for (Property property: properties) {
                    tableEntry.getChildren().add(new Text("Title: "+ property.getName()));
                    tableEntry.getChildren().add(new Text("Type: "+ property.getType()));
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
                outerBox.getChildren().add(tableEntry);
                propertyTable.getChildren().add(outerBox);

            }


        });

        //show Agents only:
        Button showAgents = new Button("Agents");
        showAgents.setOnAction(c ->{
            propertyTable.getChildren().clear();
            propertyTable.getChildren().add(options);
            for (Agent agent : agents) {
                HBox outerBox = new HBox();
                outerBox.setSpacing(10);
                outerBox.setAlignment(Pos.CENTER_LEFT);
                outerBox.setPadding(new Insets(10, 10, 10, 10));
                VBox tableEntry = new VBox();
                // User Info:
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
                tableEntry.getChildren().add(new Text("Agent Name: "+ agent.getName()));
                tableEntry.getChildren().add(new Text("Agent ID: "+ agent.getUserID()));
                // edit User button
                Button seeMoreu = new Button("View Details");
                tableEntry.getChildren().add(seeMoreu);
                //redirect to user dashboard
                seeMoreu.setOnAction(event -> {
                });

                // delete User button
                Button deleteu = new Button("Delete");
                deleteu.setOnAction(event ->{
                    agents.remove(agent);
                    propertyTable.getChildren().clear();
                });
                tableEntry.getChildren().add(deleteu);

                tableEntry.setSpacing(10);
                // Property by User:
                tableEntry.getChildren().add(new Text("Properties:____________________________________"));
                ArrayList <Property> properties = agent.getPropertyList();
                for (Property property: properties) {
                    tableEntry.getChildren().add(new Text("Title: "+ property.getName()));
                    tableEntry.getChildren().add(new Text("Type: "+ property.getType()));
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
                outerBox.getChildren().add(tableEntry);
                propertyTable.getChildren().add(outerBox);

            }

        });
        //show Tenants only:
        Button showTenats = new Button("Tenants");
        showTenats.setOnAction(c ->{
            propertyTable.getChildren().clear();
            propertyTable.getChildren().add(options);
            for (Tenant tenant : tenants) {
                HBox outerBox = new HBox();
                outerBox.setSpacing(10);
                outerBox.setAlignment(Pos.CENTER_LEFT);
                outerBox.setPadding(new Insets(10, 10, 10, 10));
                VBox tableEntry = new VBox();
                // User Info:
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
                tableEntry.getChildren().add(new Text("Agent Name: "+ tenant.getName()));
                tableEntry.getChildren().add(new Text("Agent ID: "+ tenant.getUserID()));

                // delete User button
                Button deleteu = new Button("Delete");
                deleteu.setOnAction(event ->{
                    tenants.remove(tenant);
                    propertyTable.getChildren().clear();
                });
                tableEntry.getChildren().add(deleteu);
                tableEntry.setSpacing(10);
                outerBox.getChildren().add(tableEntry);
                propertyTable.getChildren().add(outerBox);

            }

        });

        options.getChildren().addAll(showOwners,showAgents,showTenats);

    }


    // show properties:
    public void sortByProperty(ActionEvent e) {
        propertyTable.getChildren().clear();

        //options:
        HBox options = new HBox();
        options.getChildren().add(new Text("More Property Options:"));

        // print assigned:
        Button getByAssignedb = new Button("Get By Assigned");
        getByAssignedb.setOnAction(event ->{
            propertyTable.getChildren().clear();
            propertyTable.getChildren().add(options);
            ArrayList<Property> activeproperties = propertySearchFacade.getByActive();
            for (Property property: activeproperties){
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
                seeMore.setOnAction(c -> {
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
                Button deletep = new Button("Delete");
                deletep.setOnAction(c ->{
                    propertiesMain.remove(property);
                    propertyTable.getChildren().clear();

                });
                outerBox.getChildren().add(deletep);
                outerBox.getChildren().add(seeMore);
                propertyTable.getChildren().add(outerBox);

            }
        });



        // print unassigned:
        Button getByUnassignedb = new Button("Get By Unassigned");
        getByUnassignedb.setOnAction(event ->{
            System.out.println("unassigned");
            propertyTable.getChildren().clear();
            propertyTable.getChildren().add(options);
            ArrayList<Property> inactiveproperties = propertySearchFacade.getByInactive();
            for (Property property: inactiveproperties){
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
                seeMore.setOnAction(c -> {
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
                Button deletep = new Button("Delete");
                deletep.setOnAction(c ->{
                    propertiesMain.remove(property);
                    propertyTable.getChildren().clear();

                });
                outerBox.getChildren().add(deletep);
                outerBox.getChildren().add(seeMore);
                propertyTable.getChildren().add(outerBox);

            }
        });




        options.getChildren().addAll(getByAssignedb,getByUnassignedb);
        options.setSpacing(10);
        options.setPadding(new Insets(10, 10, 10, 10));
        propertyTable.getChildren().add(options);

        //print ALL properties:
        for (Property property : propertiesMain) {
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

            // delete property button
            Button deletep = new Button("Delete");
            deletep.setOnAction(event ->{
                propertiesMain.remove(property);
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
        propertyTable.setAlignment(Pos.CENTER);
        propertyTable.getChildren().add(new Text("Number of Owners Registered: " + owners.size()));
        propertyTable.setSpacing(10);
        propertyTable.getChildren().add(new Text("Number of Agents Registered: " + agents.size()));
        propertyTable.getChildren().add(new Text("Number of Tenants Registered: " + tenants.size()));
        propertyTable.getChildren().add(new Text("Number of Properties in System: " + propertiesMain.size()));

    }



}

