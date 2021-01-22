import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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

    public static boolean isValidPrice(String strNum) {
        try {
            double value = Double.parseDouble(strNum);
            return (value > 0.0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(propertyManaged != null) {
            nameField.setText(propertyManaged.getName());
            addressField.setText(propertyManaged.getAddress());
            projectField.setText(propertyManaged.getProject());
            descriptionField.setText(propertyManaged.getDescription());
            typeField.setValue(propertyManaged.getType());
            // photoFileLabel.setGraphic(propertyImageView);
            priceField.setText(Double.toString(propertyManaged.getPrice()));
            assignedStatusField.setSelected(propertyManaged.getAssignedStatus());
        }
    }

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
        if (selectedFile == null || ImageIO.read(selectedFile) == null) {
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
        if (propertyManaged == null) {
            // creating a new property..

            // doesn't belong here but works for now,
            // later move to FileHandler static method and call from Property's setPhoto()
            String photoStorePath = "./src/photos/";
            Files.copy(selectedFile.toPath(), Paths.get(photoStorePath, selectedFile.getName()),
                    StandardCopyOption.REPLACE_EXISTING); // copy selected photo to photo store

            String[] facilitiesArray = facilitiesField.getText().split(","); // string array of facilities, assume user enters in csv format

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

            System.out.println(newProperty.toCSVString()); // test entered data

            // if everything checks out, can save this property to file, but need to load all the data again
//            propertySearchFacade.addProperty(newProperty);
        }
        else {
            // not yet completed
            propertyManaged.setName(nameField.getText());
            propertyManaged.setAddress(addressField.getText());
            propertyManaged.setProject(projectField.getText());
            propertyManaged.setDescription(descriptionField.getText());
            propertyManaged.setType((String)typeField.getValue());
            propertyManaged.setPrice(Double.parseDouble(priceField.getText()));
        }
    }
}
