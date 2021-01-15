import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class RegisterPageController {
    public GridPane root;
    public TextField nameField;
    public TextField usernameField;
    public TextField passwordField;
    public ToggleGroup toggleGroup;
    public RadioButton agentButton;
    public RadioButton ownerButton;
    public RadioButton tenantButton;
    public TextField credential;

    private static class ShowAlert {
        public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
    }

    public void submitForm(ActionEvent actionEvent) throws IOException {
        Window stage = root.getScene().getWindow();
        if(nameField.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, stage, "Incomplete Data!",
                    "Please enter your name");
            return;
        }
        if(usernameField.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, stage, "Incomplete Data!",
                    "Please enter your username");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, stage, "Incomplete Data!",
                    "Please enter a password");
            return;
        }
        if(credential.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, stage, "Incomplete Data!",
                    "Please enter the needed credential for verification");
        }


        ShowAlert.showAlert(Alert.AlertType.CONFIRMATION, stage, "Registration Submitted For Approval",
                "Welcome " + nameField.getText());
    }
}
