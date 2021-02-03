import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//controller responsible for controlling GUI elements on individual page for each property
public class PropertyPageController extends Controller implements Initializable {
    //the property whose details the page is to display
    public static Property propertyToDisplay;
    //the data of the logged in user
    private User user = (User) stage.getUserData();
    //whether the logged in user actually manages the unit
    //if true, then button for edit options appears
    boolean myunit = false;

    public VBox imageArea;
    public GridPane detailsArea;
    public VBox optionsArea;
    public Text propertyName;
    public Text propertyAddress;
    public Text propertyDescription;
    public Text propertyPrice;
    public VBox Facilities;
    public Text contact;
    public Menu options;

    //initalizes by reading fields
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //view image
        Image image = new Image(propertyToDisplay.getPhoto(), 600, 300, false, false);
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


        if (myunit == true ) {// if the user is the owner of the property or admin can edit property
            Button mproperty = new Button("Manage Property");
            optionsArea.setPadding(new Insets(0, 0, 5, 50));
            optionsArea.getChildren().add(mproperty);
            mproperty.setOnAction(e -> {
                ManagePropertyController.propertyManaged = propertyToDisplay;
                Parent roleChooserfxml = null;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("addProperty.fxml"));
                    loader.setController(new ManagePropertyController());
                    Scene scene = new Scene(loader.load(), 700, 600);
                    stage.setScene(scene);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }

        //property info:
        propertyName.setText(propertyToDisplay.getName());
        propertyAddress.setText(propertyToDisplay.getAddress());
        propertyDescription.setText(propertyToDisplay.getDescription());
        propertyPrice.setText(Double.toString(propertyToDisplay.getPrice()));
        contact.setText(propertyToDisplay.getManager().getPhone());
        ArrayList<String> facilities = propertyToDisplay.getFacilities();

        // facilities
        for(String facility: facilities) {
            Facilities.getChildren().add(new Text("- " + facility.strip() + "\n"));
        }

        imageArea.setPadding(new Insets(10, 50, 10, 50));
        detailsArea.setPadding(new Insets(10, 50, 50, 50));
    }
}