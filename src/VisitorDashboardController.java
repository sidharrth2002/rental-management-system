import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VisitorDashboardController extends Controller implements Initializable {
    public VBox root;
    public TextField searchField;
    public VBox propertyTable;
    public VBox menuArea;
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

            Button specialInterface = new Button("Manage your property");
            specialInterface.setOnAction(e -> {
                if(user instanceof Owner) {
                    // render owner interface
                    try {
                        Parent ownerfxml = FXMLLoader.load(getClass().getResource("ownerScreen.fxml"));
                        Scene page = new Scene(ownerfxml, 700, 600);
                        stage.setScene(page);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (user instanceof Tenant) {
                    // render tenant interface
                    try {
                        Parent tenantfxml = FXMLLoader.load(getClass().getResource("tenantScreen.fxml"));
                        Scene page = new Scene(tenantfxml, 700, 600);
                        stage.setScene(page);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (user instanceof Agent) {
                    // render agent interface
                    try {
                        Parent agentfxml = FXMLLoader.load(getClass().getResource("agentScreen.fxml"));
                        Scene page = new Scene(agentfxml, 700, 600);
                        stage.setScene(page);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (user instanceof Admin) {
                    //go to ahmed's admin interface
                }
            });
            specialOptions.getChildren().add(specialInterface);
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
//                    stage.setScene(propertyPage);
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