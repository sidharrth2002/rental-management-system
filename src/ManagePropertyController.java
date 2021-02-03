import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class ManagePropertyController extends Controller implements Initializable {
    public TextField nameField;
    public TextField addressField;
    public TextField projectField;
    public TextField descriptionField;
    public ComboBox typeField;
    public Button photoChooseButton;
    public Label photoFileLabel;
    public TextField priceField;
    public TextField facilitiesField;
    public CheckBox assignedStatusField;
    public Button submitButton;
    public File selectedFile; // for property photo
    public static Property propertyManaged;

    //helps to check if format of price is valid
    private static boolean isValidPrice(String strNum) {
        try {
            double value = Double.parseDouble(strNum);
            return (value > 0.0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //if this page is used to edit existing property instead of adding a new one
    //load the fields of the existing property to make it easier to edit
    //also load the image and add a preview there
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(propertyManaged != null) {
            nameField.setText(propertyManaged.getName());
            addressField.setText(propertyManaged.getAddress());
            projectField.setText(propertyManaged.getProject());
            descriptionField.setText(propertyManaged.getDescription());
            typeField.setValue(propertyManaged.getType());
//             photoFileLabel.setGraphic(propertyImageView);
            priceField.setText(Double.toString(propertyManaged.getPrice()));
            assignedStatusField.setSelected(propertyManaged.getAssignedStatus());
            StringBuilder sb = new StringBuilder();
            for(String facility: propertyManaged.getFacilities()) {
                sb.append(facility).append(",");
            }
            facilitiesField.setText(sb.toString());
            Image propertyImage = new Image("." + propertyManaged.getPhoto().substring(5));
            ImageView propertyImageView = new ImageView(propertyImage);
            propertyImageView.setPreserveRatio(true); // fix image view ratio
            propertyImageView.setFitHeight(100); // set image view height
            photoFileLabel.setText(null); // remove the text
            photoFileLabel.setGraphic(propertyImageView); // set the scaled image
            selectedFile = new File(propertyManaged.getPhoto());
        }
    }

    //shows popup to allow user to choose photo file
    public void photoFileChooser(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(null); // select a file from the FileChooser
        if (selectedFile != null && ImageIO.read(selectedFile) != null) {
            Image propertyImage = new Image(selectedFile.toURI().toString());
            ImageView propertyImageView = new ImageView(propertyImage);
            propertyImageView.setPreserveRatio(true); // fix image view ratio
            propertyImageView.setFitHeight(100); // set image view height
            photoFileLabel.setText(null); // remove the text
            photoFileLabel.setGraphic(propertyImageView); // set the scaled image
        }
        else {
            photoFileLabel.setGraphic(null); // remove the image
            photoFileLabel.setText("No photo uploaded"); // set default text
        }
    }

    //checks if fields are valid and builds/updates existing property
    public void submitForm(ActionEvent actionEvent) throws IOException {
        Window window = root.getScene().getWindow();

        if(nameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the name");
            return;
        }
        if(addressField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the address");
            return;
        }
        if(projectField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the project");
            return;
        }
        if(descriptionField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the description");
            return;
        }
        if(typeField.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the type");
            return;
        }
        if (selectedFile == null) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please upload a photo");
            return;
        }
        if(priceField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the price");
            return;
        }
        if(!isValidPrice(priceField.getText())) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Invalid price format");
            return;
        }


        //if all goes well, make the property using property builder
        //then add to propertySearchFacade (or write to file first?)
        String photoStorePath = "./src/photos/";
        String photoStorePath2 = "./out/production/assig/photos";
        if (propertyManaged == null) {
            // creating a new property..

            // doesn't belong here but works for now,
            // later move to FileHandler static method and call from Property's setPhoto()
            //copy to both locations now
            Files.copy(selectedFile.toPath(), Paths.get(photoStorePath, selectedFile.getName()),
                    StandardCopyOption.REPLACE_EXISTING); // copy selected photo to photo store
            Files.copy(selectedFile.toPath(), Paths.get(photoStorePath2, selectedFile.getName()),
                    StandardCopyOption.REPLACE_EXISTING); // copy selected photo to photo store

            String[] facilitiesArray = facilitiesField.getText().split(","); // string array of facilities, assume user enters in csv format

            //build property
            //ID autogenerated
            Property newProperty = new Property.Builder()
                    .withName(nameField.getText())
                    .withAddress(addressField.getText())
                    .withProject(projectField.getText())
                    .withDescription(descriptionField.getText())
                    .withType((String)typeField.getValue())
                    .withPhoto(photoStorePath + selectedFile.getName()) // use the copied photo
                    .withPrice(Double.parseDouble(priceField.getText()))
                    .withInitialMarketDate(new Date()) // pick now as date
                    .withAssignedStatus(assignedStatusField.isSelected())
                    .withFacilities(new ArrayList<>(Arrays.asList(facilitiesArray)))
                    // with owner / agent ????
                    .build();
            User user = (User) stage.getUserData();
            if (user instanceof Agent) {
                newProperty.setAgent((Agent) user);
            } else if (user instanceof Owner) {
                newProperty.setOwner((Owner) user);
            } else {
            }

            // if everything checks out, can save this property to file, but need to load all the data again
            propertySearchFacade.addProperty(newProperty);
            user.addProperty(newProperty);
        }
        else {
            if(selectedFile != null) {
                Files.copy(selectedFile.toPath(), Paths.get(photoStorePath, selectedFile.getName()),
                        StandardCopyOption.REPLACE_EXISTING); // copy selected photo to photo store
                Files.copy(selectedFile.toPath(), Paths.get(photoStorePath2, selectedFile.getName()),
                        StandardCopyOption.REPLACE_EXISTING); // copy selected photo to photo store
            }
            //update existing fields
            propertyManaged.setName(nameField.getText());
            propertyManaged.setAddress(addressField.getText());
            propertyManaged.setProject(projectField.getText());
            propertyManaged.setDescription(descriptionField.getText());
            propertyManaged.setType((String)typeField.getValue());
            propertyManaged.setPrice(Double.parseDouble(priceField.getText()));
            propertyManaged.setPhoto(photoStorePath + selectedFile.getName());
            String[] facilitiesArray = facilitiesField.getText().split(","); // string array of facilities, assume user enters in csv format
            propertyManaged.setFacilities(new ArrayList<>(Arrays.asList(facilitiesArray)));
        }
        //return to agentOwner dashboard where they can now view the new property
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agentOwnerScreen.fxml"));
        loader.setController(new AgentOwnerScreenController());
        Scene scene = new Scene(loader.load(), 700, 600);
        stage.setScene(scene);
    }
}
