import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
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




    public VBox imageArea;
    public VBox detailsArea;
    public Text propertyName;
    public Text propertyAddress;
    public Text propertyDescription;
    public Text propertyPrice;
    public Text propertyFacilities;
    public Text Facilities;
    public Button mproperty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(propertyToDisplay.getPhoto().substring(6, propertyToDisplay.getPhoto().length()), 800, 400, true, true);
        imageArea.getChildren().add(new ImageView(image));

        if (user != null) {
            Button mproperty = new Button("Manage Property");
            detailsArea.getChildren().add(mproperty);
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
        propertyAddress.setText("Address: " + "\n" + propertyToDisplay.getAddress());
        propertyDescription.setText("Description:" + "\n" + propertyToDisplay.getDescription());
        propertyPrice.setText("Price:" + "\n" + Double.toString(propertyToDisplay.getPrice()));
        propertyFacilities.setText("Facilities:" + "\n" );
        ArrayList<String> facilities = propertyToDisplay.getFacilities();
        for(String facility: facilities){
            Facilities.setText(facility + "\n");
        }
        detailsArea.setSpacing(7);
        imageArea.setPadding(new Insets(10, 50, 50, 50));
        detailsArea.setPadding(new Insets(10, 50, 50, 50));
    }
}