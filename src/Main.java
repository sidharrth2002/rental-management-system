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
import java.util.Date;

// Screens to do:
// make a separate FXML file for each page

//screens to make:
//        1. visitor dashboard
//        2. owner dashboard
//        3. agent dashboard
//        4. add property page (common to both of above)
//        5. login page
//        6. register page
//        7. admin dashboard

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
    PropertySearchFacade propertySearchFacade = PropertySearchFacade.getInstance();
    UserFactory userFactory = UserFactory.getInstance();
    FileHandlers fileHandlers = FileHandlers.getInstance();



    @Override
    public void start(Stage primaryStage) throws Exception {
        //load all the data here- now just adding dummy data

        fileHandlers.readUsersFromFile();
        fileHandlers.getPropertyFromFile();
        fileHandlers.loadPropertyToUsers();

        //temp to test
        Parent root = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("addProperty.fxml")); // testing addProperty
        Scene scene = new Scene(root, 700, 600);
        Controller.stage = primaryStage;
        primaryStage.setTitle("Rental Management System");
        primaryStage.setScene(scene);
        primaryStage.show();


//        PropertySearchFacade propertySearchFacade = PropertySearchFacade.getInstance();
//        UserFactory userFactory = UserFactory.getInstance();
//        Owner tempOwner = (Owner) userFactory.makeUser("owner", "Sidharrth", "sidharrth2002", "123456789", "K1234ff");
//
//        propertySearchFacade.addProperty(new Property("Bungalow in the World", "2, Jalan Cochrane", "Big fat bungalow", 1000, "Condominium", "Mutiara", tempOwner));
//        propertySearchFacade.addProperty(new Property("Condominium in the World", "2, Jalan Cochrane", "C fat bungalow", 3000, "Studio", "Mutiara",tempOwner));
//        propertySearchFacade.addProperty(new Property("Bungalow in the World", "2, Jalan Cochrane", "D fat bungalow", 10000, "Condominium", "DPulze", tempOwner));
//        propertySearchFacade.addProperty(new Property("SemiD in the World", "2, Jalan Special", "D fat bungalow", 500000,"Condominium", "DPulze",  tempOwner));
//
//        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
//        Scene scene = new Scene(root, 700, 600);
//        primaryStage.setTitle("Rental Management System");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
