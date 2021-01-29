import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PropertyPageController extends Controller implements Initializable {
    public static Property propertyToDisplay;
    private User user = (User) stage.getUserData();
    boolean myunit = false;

    public VBox imageArea;
    public GridPane detailsArea;
    public VBox optionsArea;
    public Text propertyName;
    public Text propertyAddress;
    public Text propertyDescription;
    public Text propertyPrice;
    public Text propertyFacilities;
    public VBox Facilities;
    public Text contact;
    public Menu options;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image("." + propertyToDisplay.getPhoto().substring(5, propertyToDisplay.getPhoto().length()), 600, 300, false, false);
        imageArea.getChildren().add(new ImageView(image));

        // check user properties compare it with displayed property page to make sure it's owned by that user
        if(user != null){
            ArrayList<Property>  userProperties  = user.getPropertyList();
        for (Property property: userProperties){
            if (property.getID().equals(propertyToDisplay.getID())) {
                myunit = true;
            }
        }
         if (user.getUserID().substring(0,2).equals("ad")) { // admin access
                myunit = true;

            }

            MenuItem logoutItem = new MenuItem("Logout");
            logoutItem.setOnAction(e -> logout());
            options.getItems().add(logoutItem);
        } else {
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


        if (myunit == true ) {
            Button mproperty = new Button("Manage Property");
            optionsArea.setPadding(new Insets(0, 0, 5, 50));
            optionsArea.getChildren().add(mproperty);
            mproperty.setOnAction(e -> {
                ManagePropertyController.propertyManaged = propertyToDisplay;
                Parent roleChooserfxml = null;
                try {
                    roleChooserfxml = FXMLLoader.load(getClass().getResource("addProperty.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Scene roleChooser = new Scene(roleChooserfxml, 700, 600);
                stage.setScene(roleChooser);

            });
        }

        propertyName.setText(propertyToDisplay.getName());
        propertyAddress.setText(propertyToDisplay.getAddress());
        propertyDescription.setText(propertyToDisplay.getDescription());
        propertyPrice.setText(Double.toString(propertyToDisplay.getPrice()));
        contact.setText(propertyToDisplay.getManager().getPhone());
//        propertyFacilities.setText("Facilities:" + "\n" );
        ArrayList<String> facilities = propertyToDisplay.getFacilities();

        for(String facility: facilities) {
            Facilities.getChildren().add(new Text("- " + facility.strip() + "\n"));
        }

//        detailsArea.setSpacing(7);
        imageArea.setPadding(new Insets(10, 50, 10, 50));
        detailsArea.setPadding(new Insets(10, 50, 50, 50));
    }
}