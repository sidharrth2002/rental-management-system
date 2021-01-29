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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = user.getName();
        welcomeMessage.setText("Welcome " + name);
        dateToday.setText(new Date().toString());
        stats.setText("You currently manage: " + user.getPropertyList().size() + " properties");

        propertyList.setPadding(new Insets(10, 10, 10, 10));
        displayList();
    }

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

            Button moreDetails = new Button("Manage This");
            moreDetails.setOnAction(e -> {
                PropertyPageController.propertyToDisplay = property;
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("propertyPage.fxml"));
                    Scene propertyPage = new Scene(root, 700, 600);
                    stage.setScene(propertyPage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            Button deleteProperty = new Button("Delete");
            deleteProperty.setOnAction(e -> {
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

    public void goToAddNewProperty(ActionEvent actionEvent) throws IOException {
        Parent roleChooserfxml = FXMLLoader.load(getClass().getResource("addProperty.fxml"));
        Scene roleChooser = new Scene(roleChooserfxml, 700, 600);
        stage.setScene(roleChooser);
    }

    public void editDetails(ActionEvent actionEvent) throws IOException {
        RegisterPageController.setUserManaged(user);
        if (user instanceof Agent) {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("agentRegister.fxml")), 700, 600));
        } else if(user instanceof Owner) {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ownerRegister.fxml")), 700, 600));
        }
    }
}