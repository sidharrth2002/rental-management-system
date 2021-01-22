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
//        Owner tempOwner = (Owner) userFactory.makeUser("owner", "Sidharrth", "sidharrth2002", "123456789", "K1234ff");
//        Owner tempOwner2 = (Owner) userFactory.makeUser("owner", "Ahmed", "asm", "123456789", "K1234ft");
//        Agent tempAgent1 = (Agent) userFactory.makeUser("agent", "Maheson", "mmm", "123456789", "ABCD5678");

        // test property builder
//        Property property1 = new Property.Builder()
//                .withName("Maadi")
//                .withAddress("New Degla")
//                .withProject("Mutiara")
//                .withDescription("Big fat bungalow")
//                .withPrice(3000000)
//                .withInitialMarketDate(new Date(1993, 7, 10))
//                .withType("Condominium")
//                .withOwner(userFactory.getOwners().get(0))
//                .build();
//
//        propertySearchFacade.addProperty(property1);
//
//        Property property2 = new Property.Builder()
//                .withName("New Capital")
//                .withAddress("Ring Road")
//                .withProject("Mutiara")
//                .withDescription("C fat bungalow")
//                .withPrice(3000000)
//                .withInitialMarketDate(new Date(1995, 9, 10))
//                .withType("Studio")
//                .withOwner(userFactory.getOwners().get(1))
//                .withActiveStatus(true)
//                .build();
//
//        propertySearchFacade.addProperty(property2);
//
//        Property property3 = new Property.Builder()
//                .withName("Bungalow in the World")
//                .withAddress("2, Jalan Cochrane")
//                .withProject("DPulze")
//                .withDescription("D fat bungalow")
//                .withPrice(10000)
//                .withInitialMarketDate(new Date(2000, 9, 11))
//                .withType("Condominium")
//                .withOwner(userFactory.getOwners().get(0))
//                .build();
//
//        propertySearchFacade.addProperty(property3);
//
//        Property property4 = new Property.Builder()
//                .withName("SemiD in the World")
//                .withAddress("2, Jalan Special")
//                .withProject("DPulze")
//                .withDescription("another D fat bungalow")
//                .withPrice(500000)
//                .withInitialMarketDate(new Date(2001, 1, 12))
//                .withType("Condominium")
//                .withOwner(userFactory.getOwners().get(1))
//                .build();
//
//        propertySearchFacade.addProperty(property4);

        fileHandlers.loadPropertyToUsers();

//        System.out.println(property1.toCSVString());
//        System.out.println(property2.toCSVString());
//        System.out.println(property3.toCSVString());
//        System.out.println(property4.toCSVString());

//        System.out.println(property2.getID());

//        Parent root = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
        //temp to test
        Parent root = FXMLLoader.load(getClass().getResource("addProperty.fxml"));
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
