import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PropertyPageController implements Initializable {
    public static Property propertyToDisplay;

    public VBox imageArea;
    public VBox detailsArea;

    public Text propertyName;
    public Text propertyAddress;
    public Text propertyDescription;
    public Text propertyPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertyName.setText(propertyToDisplay.getName());
        propertyAddress.setText(propertyToDisplay.getAddress());
        propertyPrice.setText(Double.toString(propertyToDisplay.getPrice()));
    }
}