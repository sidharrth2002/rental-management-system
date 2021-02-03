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
import java.util.regex.Pattern;

public class VisitorDashboardController extends Controller implements Initializable {
    public VBox root;
    public TextField searchField;
    public VBox propertyTable;
    public Menu options;
    public Text welcomeMessage;
    public HBox specialOptions;
    public TextField minPriceField;
    public TextField maxPriceField;
    public double minPrice = 0;
    public double maxPrice = 10000000;

    //regex to check for decimal input
    private static final Pattern DOUBLE_PATTERN = Pattern.compile(
            "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

    public VisitorDashboardController() {}

    //write to fxml elements on the page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(stage.getUserData() != null) {
            User user = (User) stage.getUserData();

            welcomeMessage.setText("Hello " + user.getName());
            welcomeMessage.setStyle("-fx-font-size: 20");

            MenuItem logoutItem = new MenuItem("Logout");
            logoutItem.setOnAction(e -> logout());
            options.getItems().add(logoutItem);

            Button specialInterface = new Button("Go To Dashboard");
            specialInterface.setOnAction(e -> {
                if(user instanceof Owner || user instanceof Agent) {
                    // render owner interface
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("agentOwnerScreen.fxml"));
                        loader.setController(new AgentOwnerScreenController());
                        Scene scene = new Scene(loader.load(), 700, 600);
                        stage.setScene(scene);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (user instanceof Admin) {
                    //go to ahmed's admin interface
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("admindashboard.fxml"));
                        loader.setController(new AdminController());
                        Scene scene = new Scene(loader.load(), 700, 600);
                        stage.setScene(scene);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            if(!(user instanceof Tenant)) {
                specialOptions.setPadding(new Insets(10, 10, 10, 10));
                specialOptions.getChildren().add(specialInterface);
            }
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
        //loops through properties for GUI elements
        for (Property property : properties) {
            if (!property.getAssignedStatus()) {
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
                Button seeMore = new Button("View Details");
                seeMore.setOnAction(event -> {
                    //sets property that will be viewed on the special propery page
                    PropertyPageController.propertyToDisplay = property;
                    //render the page
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
    }

    //searches by keyword and price range
    public void searchProperty(KeyEvent e) throws IOException {
        propertyTable.getChildren().clear();
        String searchText = searchField.getText();
        if(searchText.length() != 0) {
            ArrayList<Property> results = propertySearchFacade.getByKeyword(searchText);
            for (Property property : results) {
                if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
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
                    Button seeMore = new Button("View Details");
                    seeMore.setOnAction(event -> {
                        //sets property that will be viewed on the special propery page
                        PropertyPageController.propertyToDisplay = property;
                        //render the page
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
        }
    }

    //sort properties by price(lowest to highest)
    public void sortByPrice(ActionEvent e) {
        propertyTable.getChildren().clear();
        //calls search facade to return sorted list
        //logic maintained in facade and not in GUI controller
        ArrayList<Property> results = propertySearchFacade.getByPrice();
        for (Property property : results) {
            if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
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
                Button seeMore = new Button("View Details");
                seeMore.setOnAction(event -> {
                    PropertyPageController.propertyToDisplay = property;
                    //render the page
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

    }

    //filters through property to get the various property types
    //then prepares a list of them
    //sorted by the type of the property
    public void sortByType(ActionEvent e) {
        propertyTable.getChildren().clear();
        ArrayList<Property> results = propertySearchFacade.getByPrice();
        ArrayList<String> propertyTypes = new ArrayList<>();
        for (Property property : results) {
            if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                if(!propertyTypes.contains(property.getType())) {
                    propertyTypes.add(property.getType());
                }
            }
        }
        for (String type: propertyTypes) {
            HBox typeHeading = new HBox();
            Text headingText = new Text(type + "s");
            headingText.setFont(Font.font(null, FontWeight.BOLD, 20));
            typeHeading.getChildren().add(headingText);
            typeHeading.setPadding(new Insets(10, 10, 10, 10));
            propertyTable.getChildren().add(typeHeading);
            //loop through the properties and print them to the GUI
            for (Property property : results) {
                //import check function to make sure the price is within the range
                if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                    if (type.equals(property.getType())) {
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
                        Button seeMore = new Button("View Details");
                        seeMore.setOnAction(event -> {
                            //sets property that will be viewed on the special propery page
                            PropertyPageController.propertyToDisplay = property;
                            //render the page
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
            }
        }

    }

    //displays by project and for each project, displays the types and the properties are sorted by price
    public void sortByProject(ActionEvent actionEvent) {
        propertyTable.getChildren().clear();
        ArrayList<Property> results = propertySearchFacade.getProperties();
        ArrayList<String> projects = new ArrayList<>();
        for (Property property : results) {
            if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                if (!projects.contains(property.getProject())) {
                    projects.add(property.getProject());
                }
            }
        }
        for (String project: projects) {
            HBox projectTitle = new HBox();
            Text projectHeading = new Text(project);
            projectHeading.setFont(Font.font(null, FontWeight.BOLD, 20));
            projectTitle.getChildren().add(projectHeading);
            projectTitle.setPadding(new Insets(10, 10, 10, 10));
            propertyTable.getChildren().add(projectTitle);

            ArrayList<Property> propertiesInProject = new ArrayList<>();
            for(Property property: results) {
                if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                    if (property.getProject().equalsIgnoreCase(project)) {
                        propertiesInProject.add(property);
                    }
                }
            }
            ArrayList<String> propertyTypes = new ArrayList<>();
            for(Property property: propertiesInProject) {
                if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                    if (!propertyTypes.contains(property.getType())) {
                        propertyTypes.add(property.getType());
                    }
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
                    if(!property.getAssignedStatus() && property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                        if (property.getType().equals(type)) {
                            propertyOfType.add(property);
                        }
                    }
                }
                //pass custom comparator to sort the filtered properties by price
                //doesn't use the facade because this list goes through several layers of filtering
                Collections.sort(propertyOfType, new Comparator<Property>() {
                    @Override
                    public int compare(final Property property1, final Property property2) {
                        return Double.compare(property1.getPrice(), property2.getPrice());
                    }
                });
                //loop through each property that has been filtered
                for (Property property: propertyOfType) {
                    if (!property.getAssignedStatus()) {
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
                        Button seeMore = new Button("View Details");
                        seeMore.setOnAction(event -> {
                            //sets property that will be viewed on the special propery page
                            PropertyPageController.propertyToDisplay = property;
                            //render the page
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
            }
        }
    }

    //listens for changes in the input and updates the min price instance field
    public void setMinPrice(KeyEvent keyEvent) {
        //puts it through regex checking to make sure it is a valid double and doesn't throw exceptions
        if(!minPriceField.getText().isEmpty() && DOUBLE_PATTERN.matcher(minPriceField.getText()).matches()) {
            minPrice = Double.parseDouble(minPriceField.getText());
        }
    }

    //listens for changes in the input and updates the max price instance field
    public void setMaxPrice(KeyEvent keyEvent) {
        //puts it through regex checking to make sure it is a valid double and doesn't throw exceptions
        if(!maxPriceField.getText().isEmpty() && DOUBLE_PATTERN.matcher(maxPriceField.getText()).matches()) {
            maxPrice = Double.parseDouble(maxPriceField.getText());
        }
    }
}