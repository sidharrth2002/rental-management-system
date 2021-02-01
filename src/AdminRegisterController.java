import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminRegisterController extends Controller {
    public GridPane root;
    public TextField nameField;
    public TextField usernameField;
    public PasswordField passwordField;
    public ToggleGroup toggleGroup;
    public RadioButton agentButton;
    public RadioButton ownerButton;
    public RadioButton tenantButton;
    public TextField credential;
    public Label extraInfo;

    //validates form fields and calls factory methods to make the new admin
    public void submitForm(ActionEvent actionEvent) throws IOException {
        Window window = root.getScene().getWindow();
        if(nameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter your name");
            return;
        }
        if(usernameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter your username");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter a password");
            return;
        }

        Stage stage = (Stage) root.getScene().getWindow();
        User admin = userFactory.makeUser("admin", nameField.getText(), usernameField.getText(), passwordField.getText(), "", "");

        if(admin != null) {
            //each will have their own dashboard(menu will have a few different options)
            //but same until those are all ready
            Parent root = FXMLLoader.load(getClass().getResource("admindashboard.fxml"));
            Scene dashboard = new Scene(root, 700, 600);
            stage.setScene(dashboard);
        }
    }
}

