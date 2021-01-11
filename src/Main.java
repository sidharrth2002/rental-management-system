import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
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
    public static String role;
    public static String name;
    public static String loginError = "";
    public static ArrayList<Property> propertyToDisplay;

    @Override
    public void start(Stage primaryStage) throws Exception {
        showRoleChooser(primaryStage);
        primaryStage.show();
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

        loginPane.getChildren().addAll(new Text("You are an " + role), logoutButton);
        primaryStage.setScene(loginScreen);
    }

    public void showRegisterScreen(Stage primaryStage) {

    }

    public void showAddProperty() {

    }

    public void showSearchResults() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
