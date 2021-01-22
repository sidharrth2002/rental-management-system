import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManagePropertyController extends Controller implements Initializable {
    public TextField nameField;
    public TextField addressField;
    public TextField projectField;
    public TextField descriptionField;
    public ComboBox typeField;
    public TextField priceField;
    public CheckBox assignedStatusField;
    public Button submitButton;
    public static Property propertyManaged;

    private static final Pattern DOUBLE_PATTERN = Pattern.compile(
            "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

    public static boolean isDouble(String s)
    {
        return DOUBLE_PATTERN.matcher(s).matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(propertyManaged != null) {
            nameField.setText(propertyManaged.getName());
            addressField.setText(propertyManaged.getAddress());
            projectField.setText(propertyManaged.getProject());
            descriptionField.setText(propertyManaged.getDescription());
            typeField.setValue(propertyManaged.getType());
            priceField.setText(Double.toString(propertyManaged.getPrice()));
            assignedStatusField.setSelected(propertyManaged.getStatus());
        }    }

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
        if(priceField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the price");
            return;
        }
        if(!DOUBLE_PATTERN.matcher(priceField.getText()).matches()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Invaid price format");
            return;
        }
//        try {
//            Double price = Double.valueOf(priceField.getText());
//        } catch(NumberFormatException e) {
//            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
//                    "Price must be a number");
//        }

        //if all goes well, make the property using property builder
        //then add to propertySearchFacade
        if(propertyManaged == null) {
            Property newProperty = new Property.Builder()
                    .withName(nameField.getText())
                    .withAddress(addressField.getText())
                    .withProject(projectField.getText())
                    .withDescription(descriptionField.getText())
                    .withType((String)typeField.getValue())
                    .withPrice(Double.parseDouble(priceField.getText()))
                    .build();
            propertySearchFacade.addProperty(newProperty);
        } else {
            propertyManaged.setName(nameField.getText());
            propertyManaged.setAddress(addressField.getText());
            propertyManaged.setProject(projectField.getText());
            propertyManaged.setDescription(descriptionField.getText());
            propertyManaged.setType((String)typeField.getValue());
            propertyManaged.setPrice(Double.parseDouble(priceField.getText()));        }
        }
}
