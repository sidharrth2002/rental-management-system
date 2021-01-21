import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AgentOwnerScreenController extends Controller implements Initializable {
    public VBox root;
    public Text welcomeMessage;
    public Text dateToday;
    public VBox propertyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = (User) stage.getUserData();
        String name = user.getName();
        welcomeMessage.setText("Welcome " + name);
        dateToday.setText(new Date().toString());

        ArrayList<Property> propertiesManaged = user.getPropertyList();

        System.out.println("Properties are " + propertiesManaged);

        for (Property property : propertiesManaged) {
            HBox singleProperty = new HBox();
            VBox propertyDetails = new VBox();
            propertyDetails.getChildren().add(new Text(property.getName()));
            propertyDetails.getChildren().add(new Text(property.getAddress()));
            propertyDetails.getChildren().add(new Text("RM " + property.getPrice()));
            singleProperty.getChildren().add(propertyDetails);
            Button moreDetails = new Button("Manage This");
            moreDetails.setOnAction(e -> {
                PropertyPageController.propertyToDisplay = property;
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                    Scene propertyPage = new Scene(root, 700, 600);
//                    stage.setScene(propertyPage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            Button deleteProperty = new Button("Delete");
            deleteProperty.setOnAction(e -> {
                user.deleteProperty(property);
                propertySearchFacade.deleteProperty(property);
                //need to refresh or auto?
            });
            singleProperty.getChildren().addAll(moreDetails, deleteProperty);
            propertyList.getChildren().add(singleProperty);
        }
    }
}