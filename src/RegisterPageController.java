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

public class RegisterPageController extends Controller{
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
        if(credential.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                    "Please enter the needed credential for verification");
            return;
        }
        //identify type of user we are going to make
        String role = "";
        String extraDetails = extraInfo.getText();
        switch (extraDetails) {
            case "Agent License Code: ":
                role = "agent";
                break;
            case "Ownership Code(Of Any Property You Own): ":
                role = "owner";
                break;
            case "IC Number: ":
                role = "tenant";
                break;
        }
        Stage stage = (Stage) root.getScene().getWindow();
//        public User makeUser(String userType, String name, String username, String password, String credential) {
        User user = userFactory.makeUser(role, nameField.getText(), usernameField.getText(), passwordField.getText(), credential.getText());
        stage.setUserData(user);
        if(user != null) {
            //each will have their own dashboard(menu will have a few different options)
            //but same until those are all ready
            if(user instanceof Agent) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            } else if(user instanceof Owner) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            } else if(user instanceof Tenant) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            } else if(user instanceof Admin) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            }
        }

    }
}
