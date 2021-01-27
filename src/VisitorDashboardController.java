import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class VisitorDashboardController extends Controller implements Initializable {
    public VBox root;
    public TextField searchField;
    public VBox propertyTable;
    public Menu options;
    public Text welcomeMessage;
    public HBox specialOptions;

    public VisitorDashboardController() {
        System.out.println("Child controller");
    }

    //write to fxml elements on the page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(stage.getUserData() != null) {
            User user = (User) stage.getUserData();
            System.out.println(user.getName());
            welcomeMessage.setText("Hello " + user.getName());

            MenuItem logoutItem = new MenuItem("Logout");
            logoutItem.setOnAction(e -> logout());
            options.getItems().add(logoutItem);

            Button specialInterface = new Button("Manage your property");
            specialInterface.setOnAction(e -> {
                if(user instanceof Owner || user instanceof Agent) {
                    // render owner interface
                    try {
                        Parent agentOwnerfxml = FXMLLoader.load(getClass().getResource("agentOwnerScreen.fxml"));
                        Scene page = new Scene(agentOwnerfxml, 700, 600);
                        System.out.println(user.getPropertyList());
                        stage.setScene(page);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (user instanceof Admin) {
                    //go to ahmed's admin interface
                    try {
                        Parent adminfxml = FXMLLoader.load(getClass().getResource("admindashboard.fxml"));
                        Scene page = new Scene(adminfxml, 700, 600);
                        stage.setScene(page);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            specialOptions.setPadding(new Insets(10, 10, 10, 10));
            specialOptions.getChildren().add(specialInterface);
        } else {
            //if not logged in, give option to go and login
            MenuItem loginItem = new MenuItem("Login");
            loginItem.setId("login");
            loginItem.setOnAction(e -> {
                try {
                    changeScene(e);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            options.getItems().add(loginItem);
        }

        ArrayList<Property> properties = propertySearchFacade.getProperties();
        for (Property property : properties) {
            HBox outerBox = new HBox();
            outerBox.setSpacing(10);
            outerBox.setAlignment(Pos.CENTER_LEFT);
            outerBox.setPadding(new Insets(10, 10, 10, 10));
            VBox tableEntry = new VBox();
            tableEntry.setPadding(new Insets(10, 10, 10, 10));
//                outerBox.getChildren().add(new Separator());
            tableEntry.getChildren().add(new Text(property.getName()));
            tableEntry.getChildren().add(new Text(property.getAddress()));
            tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
            outerBox.getChildren().add(tableEntry);
            Button seeMore = new Button("View Details");
            seeMore.setOnAction(event -> {
                //sets property that will be viewed on the special propery page
                PropertyPageController.propertyToDisplay = property;
                //render the page
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                    Scene propertyPage = new Scene(root, 700, 600);
                    stage.setScene(propertyPage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            outerBox.getChildren().add(seeMore);
//                outerBox.getChildren().add(new Separator());
            propertyTable.getChildren().add(outerBox);
        }

    }

    public void searchProperty(KeyEvent e) throws IOException {
        propertyTable.getChildren().clear();
        String searchText = searchField.getText();
        System.out.println("Search with the keyword. " + searchText);
        if(searchText.length() != 0) {
            ArrayList<Property> results = propertySearchFacade.getByKeyword(searchText);
            for (Property property : results) {
                HBox outerBox = new HBox();
                outerBox.setSpacing(10);
                outerBox.setAlignment(Pos.CENTER_LEFT);
                outerBox.setPadding(new Insets(10, 10, 10, 10));
                VBox tableEntry = new VBox();
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
//                outerBox.getChildren().add(new Separator());
                tableEntry.getChildren().add(new Text(property.getName()));
                tableEntry.getChildren().add(new Text(property.getAddress()));
                tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
                outerBox.getChildren().add(tableEntry);
                Button seeMore = new Button("View Details");
                seeMore.setOnAction(event -> {
                    //sets property that will be viewed on the special propery page
                    PropertyPageController.propertyToDisplay = property;
                    //render the page
                    try {
                        Stage stage = (Stage) root.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                        Scene propertyPage = new Scene(root, 700, 600);
                        stage.setScene(propertyPage);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                outerBox.getChildren().add(seeMore);
//                outerBox.getChildren().add(new Separator());
                propertyTable.getChildren().add(outerBox);
            }
        }
    }

    public void sortByPrice(ActionEvent e) {
        propertyTable.getChildren().clear();
        ArrayList<Property> results = propertySearchFacade.getByPrice();

        for (Property property : results) {
            HBox outerBox = new HBox();
            outerBox.setSpacing(10);
            outerBox.setAlignment(Pos.CENTER_LEFT);
            outerBox.setPadding(new Insets(10, 10, 10, 10));
            VBox tableEntry = new VBox();
            tableEntry.setPadding(new Insets(10, 10, 10, 10));
//            outerBox.getChildren().add(new Separator());
            tableEntry.getChildren().add(new Text(property.getName()));
            tableEntry.getChildren().add(new Text(property.getAddress()));
            tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
            outerBox.getChildren().add(tableEntry);
            Button seeMore = new Button("View Details");
            seeMore.setOnAction(event -> {
                //sets property that will be viewed on the special propery page
                PropertyPageController.propertyToDisplay = property;
                //render the page
                try {
                    Stage stage = (Stage) root.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                    Scene propertyPage = new Scene(root, 700, 600);
                    stage.setScene(propertyPage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            outerBox.getChildren().add(seeMore);
//            outerBox.getChildren().add(new Separator());
            propertyTable.getChildren().add(outerBox);
        }

    }

    public void sortByType(ActionEvent e) {
        propertyTable.getChildren().clear();
        ArrayList<Property> results = propertySearchFacade.getByPrice();
        ArrayList<String> propertyTypes = new ArrayList<>();
        for (Property property : results) {
            if(!propertyTypes.contains(property.getType())) {
                propertyTypes.add(property.getType());
            }
        }
        for (String type: propertyTypes) {
            HBox typeHeading = new HBox();
            Text headingText = new Text(type + "s");
            headingText.setFont(Font.font(null, FontWeight.BOLD, 20));
            typeHeading.getChildren().add(headingText);
            typeHeading.setPadding(new Insets(10, 10, 10, 10));
            propertyTable.getChildren().add(typeHeading);
            for (Property property : results) {
                if(type.equals(property.getType())) {
                    HBox outerBox = new HBox();
                    outerBox.setSpacing(10);
                    outerBox.setAlignment(Pos.CENTER_LEFT);
                    outerBox.setPadding(new Insets(10, 10, 10, 10));
                    VBox tableEntry = new VBox();
                    tableEntry.setPadding(new Insets(10, 10, 10, 10));
//            outerBox.getChildren().add(new Separator());
                    tableEntry.getChildren().add(new Text(property.getName()));
                    tableEntry.getChildren().add(new Text(property.getAddress()));
                    tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
                    outerBox.getChildren().add(tableEntry);
                    Button seeMore = new Button("View Details");
                    seeMore.setOnAction(event -> {
                        //sets property that will be viewed on the special propery page
                        PropertyPageController.propertyToDisplay = property;
                        //render the page
                        try {
                            Stage stage = (Stage) root.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                            Scene propertyPage = new Scene(root, 700, 600);
                            stage.setScene(propertyPage);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    outerBox.getChildren().add(seeMore);
//            outerBox.getChildren().add(new Separator());
                    propertyTable.getChildren().add(outerBox);
                }
            }
        }

    }

    public void sortByProject(ActionEvent actionEvent) {
        propertyTable.getChildren().clear();
        ArrayList<Property> results = propertySearchFacade.getProperties();
        ArrayList<String> projects = new ArrayList<>();
        for (Property property : results) {
            if(!projects.contains(property.getProject())) {
                projects.add(property.getProject());
            }
        }
        System.out.println(projects);
        for (String project: projects) {
            HBox projectTitle = new HBox();
            Text projectHeading = new Text(project);
            projectHeading.setFont(Font.font(null, FontWeight.BOLD, 20));
            projectTitle.getChildren().add(projectHeading);
            projectTitle.setPadding(new Insets(10, 10, 10, 10));
            propertyTable.getChildren().add(projectTitle);

            ArrayList<Property> propertiesInProject = new ArrayList<>();
            for(Property property: results) {
                if (property.getProject().equalsIgnoreCase(project)) {
                    propertiesInProject.add(property);
                }
            }
            ArrayList<String> propertyTypes = new ArrayList<>();
            for(Property property: propertiesInProject) {
                if(!propertyTypes.contains(property.getType())) {
                    propertyTypes.add(property.getType());
                }
            }
            for(String type: propertyTypes) {
                HBox typeTitle = new HBox();
                Text typeText = new Text(type + "s");
                typeText.setFont(Font.font(null, FontWeight.BOLD, 15));
                typeTitle.getChildren().add(typeText);
                typeTitle.setPadding(new Insets(10, 10, 10, 10));
                propertyTable.getChildren().add(typeTitle);
                ArrayList<Property> propertyOfType = new ArrayList<>();
                for(Property property: propertiesInProject) {
                    if(property.getType().equals(type)) {
                        propertyOfType.add(property);
                    }
                }
                Collections.sort(propertyOfType, new Comparator<Property>() {
                    @Override
                    public int compare(final Property property1, final Property property2) {
                        return Double.compare(property1.getPrice(), property2.getPrice());
                    }
                });
                for (Property property: propertyOfType) {
                    HBox outerBox = new HBox();
                    outerBox.setSpacing(10);
                    outerBox.setAlignment(Pos.CENTER_LEFT);
                    outerBox.setPadding(new Insets(10, 10, 10, 10));
                    VBox tableEntry = new VBox();
                    tableEntry.setPadding(new Insets(10, 10, 10, 10));
//            outerBox.getChildren().add(new Separator());
                    tableEntry.getChildren().add(new Text(property.getName()));
                    tableEntry.getChildren().add(new Text(property.getAddress()));
                    tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
                    outerBox.getChildren().add(tableEntry);
                    Button seeMore = new Button("View Details");
                    seeMore.setOnAction(event -> {
                        //sets property that will be viewed on the special propery page
                        PropertyPageController.propertyToDisplay = property;
                        //render the page
                        try {
                            Stage stage = (Stage) root.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                            Scene propertyPage = new Scene(root, 700, 600);
                            stage.setScene(propertyPage);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    outerBox.getChildren().add(seeMore);
//            outerBox.getChildren().add(new Separator());
                    propertyTable.getChildren().add(outerBox);
                }
            }
        }
    }
}

//        for (Property property: results) {
//            VBox tableEntry = new VBox();
//            tableEntry.setPadding(new Insets(10, 10, 10, 10));
//            tableEntry.getChildren().add(new Separator());
//            tableEntry.getChildren().add(new Box(10, 10, 10));
//            tableEntry.getChildren().add(new Text(property.getName()));
//            tableEntry.getChildren().add(new Text(property.getAddress()));
//            tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
//            tableEntry.getChildren().add(new Box(10, 10, 10));
//            tableEntry.getChildren().add(new Separator());
//            propertyTable.getChildren().add(tableEntry);
//        }