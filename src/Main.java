import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

// Screens to do:
//1. role chooser screen- Pira
//2. login screen- something nice- Maheson
//3. registration page- make a new account
//4. dashboard with search option (call controller method to filter, get the arraylist, set the state and show the results screen)- Sid
//5. screen to show search results (same stage)- Sid
//6. admin interface- show number of users in the system, show users pending approval, with button to approve
//7. property page- print details about property and have rating form (if got time)- Maheson
//8. adding property page- Maheson
//9. file handling controllers- Sid

public class Main extends Application {
    Scene roleChooser;
    Scene loginScreen;
    public static String role = "";
    public static String name = "John Doe";
    public static String loginError = "";
    public static ArrayList<Property> propertyToDisplay;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

        Scene scene = new Scene(root, 700, 600);

        primaryStage.setTitle("Rental Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
//        showDashboard(primaryStage);
//        showRoleChooser(primaryStage);
    }

    public void showRoleChooser(Stage primaryStage) {
        HBox roleChooserPane = new HBox();
        roleChooser = new Scene(roleChooserPane, 900, 700);

        roleChooserPane.setPadding(new Insets(15, 12, 15, 12));
        roleChooserPane.setSpacing(10);
        Button owner = new Button("Owner");
        Button tenant = new Button("Tenant");
        Button agent = new Button("agent");
        Button admin = new Button("admin");
        owner.setOnAction(e -> {
            Main.role = "owner";
            showLoginScreen(primaryStage);
        });

        roleChooserPane.getChildren().addAll(owner, tenant, agent, admin);

        primaryStage.setScene(roleChooser);
    }

    //write the display stuff here
    public void showLoginScreen(Stage primaryStage) {
        //if loginError is empty, then don't print it, if not empty, then show error

        HBox loginPane = new HBox();
        loginScreen = new Scene(loginPane, 900, 700);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            showRoleChooser(primaryStage);
        });

        primaryStage.setScene(loginScreen);
    }

    public void showRegisterScreen(Stage primaryStage) {

    }

    public void showAddProperty() {

    }

    public void showPropertyPage(String propertyID) {
        //print based on propertyID
    }

    public void showSearchResults() {
        StackPane searchResults = new StackPane();
        for(int i = 0; i < propertyToDisplay.size(); i++) {
            Property property = propertyToDisplay.get(i);
            VBox propertyItem = new VBox();
            propertyItem.getChildren().add(new Text(property.getName()));
            propertyItem.getChildren().add(new Text(Character.toString(property.getRating())));
            Button viewDetails = new Button("See Details");
            viewDetails.setId(property.getID());
            viewDetails.setOnAction(e -> {
                Button buttonClicked = (Button) e.getSource();
                String id = buttonClicked.getId();
                showPropertyPage(id);
            });
        }
    }

    public void showDashboard(Stage primaryStage) {
        VBox dashboard = new VBox();
        dashboard.setPadding(new Insets(10, 10, 10, 10));

        //menu
        VBox menuArea = new VBox();
        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Options");
        MenuItem roleChooser = new MenuItem("Role Chooser");
        roleChooser.setOnAction(e -> {
            showRoleChooser(primaryStage);
        });
        MenuItem saveAndExit = new MenuItem("Save and Exit");
        saveAndExit.setOnAction(e -> {
            System.out.println("do a clean exit");
        });

        optionsMenu.getItems().add(roleChooser);
        optionsMenu.getItems().add(saveAndExit);
        menuBar.getMenus().add(optionsMenu);

        if(role.equals("admin")) {
            Menu adminOptions = new Menu("Admin Tools");
            MenuItem systemSummary = new MenuItem("View System Summary");
            MenuItem adminDashboard = new MenuItem("Admin Dashboard");
            adminOptions.getItems().add(systemSummary);
            adminOptions.getItems().add(adminDashboard);
            menuBar.getMenus().add(adminOptions);
        }

        menuArea.getChildren().add(menuBar);
        dashboard.getChildren().add(menuArea);

        VBox welcomeMessage = new VBox();
        welcomeMessage.setPadding(new Insets(10, 10, 10, 10));
        Text welcomeName = new Text("Welcome " + name);
        welcomeName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeMessage.getChildren().add(welcomeName);
//             welcomeMessage.getChildren().add(new Text("You are a " + role).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        dashboard.getChildren().add(welcomeMessage);

        //mainArea
        GridPane searchBar = new GridPane();
        Text searchLabel = new Text("Search (Enter a keyword)");
        TextField searchField = new TextField();
        searchBar.add(searchLabel, 0, 0);
        searchBar.add(searchField, 1, 0);
        searchBar.setPadding(new Insets(10, 10, 10, 10));
        searchBar.setMinSize(50, 50);
        searchBar.setVgap(5);
        searchBar.setHgap(10);
        dashboard.getChildren().add(searchBar);

        Button showByType = new Button("Show By Type");
        showByType.setOnAction(e -> {
            //call some controller class and then go to new scene
            System.out.println("sort and go to new window");
        });
        Button showByPrice = new Button("Show By Price");
        showByPrice.setOnAction(e -> {
            //call some controller class and go to new scene
            System.out.println("sort and go to new window");
        });
        Button showByAvailable = new Button("Show Active Property");
        showByAvailable.setOnAction(e -> {
            System.out.println("sort and go to new window");
        });

        FlowPane dividerLabel = new FlowPane();
        dividerLabel.setPadding(new Insets(10, 10, 10, 10));
        dividerLabel.getChildren().add(new Text("Or go ahead and aggregate by a bunch of options here: "));
        dashboard.getChildren().add(dividerLabel);

        GridPane searchOptions = new GridPane();
        searchOptions.add(showByType, 0, 3);
        searchOptions.add(showByPrice, 1, 3);
        searchOptions.add(showByAvailable, 2, 3);
        searchOptions.setVgap(5);
        searchOptions.setHgap(10);
        searchOptions.setPadding(new Insets(10, 10, 10, 10));
        dashboard.getChildren().add(searchOptions);

        if(role.equals("owner") || role.equals("agent")) {
            Separator line = new Separator();
            dashboard.getChildren().add(line);
                
        }

        Scene scene = new Scene(dashboard, 900, 700);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
