
//This was written by Ahmed Sanad
//1181102208

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class AdminController extends Controller implements Initializable {

    public VBox propertyTable;
    public VBox menuArea;
    public ArrayList<Property> propertiesMain = propertySearchFacade.getProperties();
    public ArrayList<Owner> owners = UserFactory.getInstance().getOwners();
    public ArrayList<Tenant> tenants = UserFactory.getInstance().getTenants();
    public ArrayList<Agent> agents = UserFactory.getInstance().getAgents();

    //admin register page
    public void addAdmin(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminRegister.fxml"));
            loader.setController(new AdminRegisterController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

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
                HBox useroptions = new HBox(); //1
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
                Text t2 = new Text(owner.getUserID() + " - " + owner.getName());
                t2.setFill(Color.DARKRED);
                tableEntry.getChildren().add(t2);
                // edit User button


                // approve user
                HBox assign = new HBox();
                assign.setSpacing(10);
                Label assignLabel = new Label("Approve?");
                CheckBox checkBox = new CheckBox();
                assign.setFillHeight(true);

                if(owner.getApprovalStatus()) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                checkBox.setOnAction(event -> {
                    owner.setApprovalStatus(checkBox.isSelected());
                });
                assign.getChildren().addAll(assignLabel, checkBox);


                // delete User button
                Button deleteu = new Button("Delete");
                deleteu.setOnAction(event ->{
                    UserFactory.getInstance().removeOwner(owner);
                    propertyTable.getChildren().clear();
                });

                useroptions.getChildren().addAll(new Text("More Options:"),deleteu,assign);//2
                useroptions.setSpacing(10);
                tableEntry.getChildren().add(useroptions);//3
                tableEntry.setSpacing(10);
                // Property by User:
                Text t = new Text("Properties: ");
                t.setFill(Color.DARKRED);
                tableEntry.getChildren().add(t);

                ArrayList <Property> properties = owner.getPropertyList();
                for (Property property: properties) {
                    tableEntry.getChildren().add(new Text(property.getName()));
                    tableEntry.getChildren().add(new Text("Type: "+ property.getType()));
                    tableEntry.getChildren().add(new Text("Address: "+property.getAddress()));

                    HBox poptions = new HBox();//1
                    poptions.setSpacing(10);


                    // edit property button
                    Button seeMore = new Button("View Details");

                    seeMore.setOnAction(event -> {
                        PropertyPageController.propertyToDisplay = property;
                        try {
                            Stage stage = (Stage) root.getScene().getWindow();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyPage.fxml"));
                            loader.setController(new PropertyPageController());
                            Scene scene = new Scene(loader.load(), 700, 600);
                            stage.setScene(scene);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    // delete property button
                    Button delete = new Button("Delete");
                    delete.setOnAction(event ->{
                        owner.deleteProperty(property);
                        propertySearchFacade.deleteProperty(property);
                        propertyTable.getChildren().clear();
                    });
                    poptions.getChildren().addAll(seeMore,delete);//2
                    tableEntry.getChildren().add(poptions);//3
                }
                tableEntry.getChildren().add(new Text("_______________________________________________________________________________"));
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
                HBox useroptions = new HBox(); //1
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
                Text t2 = new Text(agent.getUserID() + " - " + agent.getName());
                t2.setFill(Color.DARKRED);
                tableEntry.getChildren().add(t2);
                // edit User button

                // approve user
                HBox assign = new HBox();
                assign.setSpacing(10);
                Label assignLabel = new Label("Approve?");
                CheckBox checkBox = new CheckBox();
                assign.setFillHeight(true);

                if(agent.getApprovalStatus()) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                checkBox.setOnAction(event -> {
                    agent.setApprovalStatus(checkBox.isSelected());
                });
                assign.getChildren().addAll(assignLabel, checkBox);
                tableEntry.getChildren().add(assign);

                // delete User button
                Button deleteu = new Button("Delete");
                deleteu.setOnAction(event ->{
                    UserFactory.getInstance().removeAgent(agent);
                    propertyTable.getChildren().clear();
                });


                useroptions.getChildren().addAll(new Text("More Options:"),deleteu,assign);//2
                useroptions.setSpacing(10);
                tableEntry.getChildren().add(useroptions);//3
                tableEntry.setSpacing(10);
                // Property by User:
                Text t = new Text("Properties: ");
                t.setFill(Color.DARKRED);
                tableEntry.getChildren().add(t);

                ArrayList <Property> properties = agent.getPropertyList();
                for (Property property: properties) {
                    tableEntry.getChildren().add(new Text("Title: "+ property.getName()));
                    tableEntry.getChildren().add(new Text("Type: "+ property.getType()));
                    tableEntry.getChildren().add(new Text("Address: "+property.getAddress()));

                    HBox poptions = new HBox();//1
                    poptions.setSpacing(10);
                    // edit property button
                    Button seeMore = new Button("View Details");
                    seeMore.setOnAction(event -> {
                        PropertyPageController.propertyToDisplay = property;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyPage.fxml"));
                            loader.setController(new PropertyPageController());
                            Scene scene = new Scene(loader.load(), 700, 600);
                            stage.setScene(scene);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    // delete property button
                    Button delete = new Button("Delete");
                    delete.setOnAction(event ->{
                        agent.deleteProperty(property);
                        propertySearchFacade.deleteProperty(property);
                        propertyTable.getChildren().clear();
                    });
                    poptions.getChildren().addAll(seeMore,delete);//2
                    tableEntry.getChildren().add(poptions);//3
                }
                tableEntry.getChildren().add(new Text("_______________________________________________________________________________"));
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
                HBox useroptions = new HBox(); //1
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
                Text t2 = new Text(tenant.getUserID() + " - " + tenant.getName());
                t2.setFill(Color.DARKRED);
                tableEntry.getChildren().add(t2);

                // approve user
                HBox assign = new HBox();
                assign.setSpacing(10);
                Label assignLabel = new Label("Approve?");
                CheckBox checkBox = new CheckBox();
                assign.setFillHeight(true);

                if(tenant.getApprovalStatus()) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                checkBox.setOnAction(event -> {
                    tenant.setApprovalStatus(checkBox.isSelected());
                });
                assign.getChildren().addAll(assignLabel, checkBox);

                // delete User button
                Button deleteu = new Button("Delete");
                deleteu.setOnAction(event ->{
                    tenants.remove(tenant);
                    propertyTable.getChildren().clear();
                });

                useroptions.getChildren().addAll(new Text("More Options:"),deleteu,assign);//2
                useroptions.setSpacing(10);
                tableEntry.getChildren().add(useroptions);//3
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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyPage.fxml"));
                        loader.setController(new PropertyPageController());
                        Scene scene = new Scene(loader.load(), 700, 600);
                        stage.setScene(scene);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                outerBox.getChildren().add(seeMore);
                propertyTable.getChildren().add(outerBox);

            }
        });



        // print unassigned:
        Button getByUnassignedb = new Button("Get By Unassigned");
        getByUnassignedb.setOnAction(event ->{
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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyPage.fxml"));
                        loader.setController(new PropertyPageController());
                        Scene scene = new Scene(loader.load(), 700, 600);
                        stage.setScene(scene);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyPage.fxml"));
                    loader.setController(new PropertyPageController());
                    Scene scene = new Scene(loader.load(), 700, 600);
                    stage.setScene(scene);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

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

