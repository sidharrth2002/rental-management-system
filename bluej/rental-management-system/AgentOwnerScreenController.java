import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
    public GridPane propertyList;
    public Text stats;
    private User user = (User) stage.getUserData();

    //initialize page by filling in fields with details specific to the user
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = user.getName();
        welcomeMessage.setText("Welcome " + name);
        dateToday.setText(new Date().toString());
        stats.setText("You currently manage: " + user.getPropertyList().size() + " properties");

        propertyList.setPadding(new Insets(10, 10, 10, 10));
        displayList();
    }

    //loops to print the properties managed by the user
    public void displayList() {
        ArrayList<Property> propertiesManaged = user.getPropertyList();
        stats.setText("You currently manage: " + propertiesManaged.size() + " properties");
        for(int i = 0; i < propertiesManaged.size(); i++) {
            Property property = propertiesManaged.get(i);
            VBox propertyDetails = new VBox();
            propertyDetails.setSpacing(10);
            Text propertyName = new Text("Name: " + property.getName());
            propertyName.setWrappingWidth(220);
            Text propertyAddress = new Text("Address:  "+ property.getAddress());
            propertyAddress.setWrappingWidth(220);
            Text propertyPrice = new Text("Rent: RM " + property.getPrice());
            propertyPrice.setWrappingWidth(220);

            propertyDetails.getChildren().add(propertyName);
            propertyDetails.getChildren().add(propertyAddress);
            propertyDetails.getChildren().add(propertyPrice);

            propertyList.add(propertyDetails, 0, i);
            HBox buttons = new HBox();
            buttons.setSpacing(20);

            HBox assign = new HBox();
            assign.setSpacing(10);
            Label assignLabel = new Label("Assigned?");
            CheckBox checkBox = new CheckBox();
            assign.setFillHeight(true);

            if(property.getAssignedStatus()) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }
            checkBox.setOnAction(e -> {
                property.setAssignedStatus(checkBox.isSelected());
            });
            assign.getChildren().addAll(assignLabel, checkBox);

            //button that allows user to visit page specifically for the property
            Button moreDetails = new Button("Manage This");
            moreDetails.setOnAction(e -> {
                PropertyPageController.propertyToDisplay = property;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyPage.fxml"));
                    loader.setController(new PropertyPageController());
                    Scene scene = new Scene(loader.load(), 700, 600);
                    stage.setScene(scene);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            //option to delete the property
            Button deleteProperty = new Button("Delete");
            deleteProperty.setOnAction(e -> {
                //removes from both user's and property list
                user.deleteProperty(property);
                propertySearchFacade.deleteProperty(property);
                //need to refresh or auto?
                propertyList.getChildren().clear();
                displayList();
            });
            buttons.getChildren().addAll(assign, moreDetails, deleteProperty);
            propertyList.add(buttons, 1, i);
//            propertyList.getChildren().add(singleProperty);
        }
    }

    //goes to add new property page
    public void goToAddNewProperty(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addProperty.fxml"));
        loader.setController(new ManagePropertyController());
        Scene scene = new Scene(loader.load(), 700, 600);
        stage.setScene(scene);
    }

    //allows user to edit their details
    public void editDetails(ActionEvent actionEvent) throws IOException {
        RegisterPageController.setUserManaged(user);
        if (user instanceof Agent) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agentRegister.fxml"));
            loader.setController(new RegisterPageController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } else if(user instanceof Owner) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerRegister.fxml"));
            loader.setController(new RegisterPageController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        }
    }
}