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
        Admin admin = new Admin("ad",nameField.getText(), usernameField.getText(), passwordField.getText(),true);
        UserFactory.getInstance().admins.add(admin);


        stage.setUserData(admin);
        if(admin != null) {
            //each will have their own dashboard(menu will have a few different options)
            //but same until those are all ready
            Parent root = FXMLLoader.load(getClass().getResource("admindashboard.fxml"));
            Scene dashboard = new Scene(root, 700, 600);
            stage.setScene(dashboard);
        }
    }
}

