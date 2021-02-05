//This was written by Sidharrth Nagappan and Ahmed Sanad
//1181102313 and 1181102208

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPageController extends Controller implements Initializable {
    public GridPane root;
    public TextField nameField;
    public TextField usernameField;
    public PasswordField passwordField;
    public ToggleGroup toggleGroup;
    public RadioButton agentButton;
    public RadioButton ownerButton;
    public RadioButton tenantButton;
    public TextField credential;
    public TextField tphone;
    public Label extraInfo;
    public Button submitButton;

    //user whose details the page prefills (if for edit and not register)
    private static User userManaged;

    //in case this page is used as an edit page, then set the static field to prefill details of this user
    public static void setUserManaged(User userManaged) {
        RegisterPageController.userManaged = userManaged;
    }

    //if page used for editing, initalize fields with existing values
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (userManaged != null) {
            //for each field, set the property
            nameField.setText(userManaged.getName());
            usernameField.setText(userManaged.getUsername());
            passwordField.setText(userManaged.getPassword());
            if (userManaged instanceof Owner) {
                credential.setText(((Owner) userManaged).getOwnershipCode());
            } else if (userManaged instanceof Agent) {
                credential.setText(((Agent) userManaged).getLicenseCode());
            }
            tphone.setText(userManaged.getPhone());
            //the page is used for editing, so change the button to update
            submitButton.setText("Update");
        }
    }

    //checks each input to make sure not empty and is of correct format
    //then updates/creates user
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
            if(tphone.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, window, "Incomplete Data!",
                        "Please enter your phone number.");
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
            //if page is used as register page
            if(userManaged == null) {
                Stage stage = (Stage) root.getScene().getWindow();
                User user = userFactory.makeUser(role, nameField.getText(), usernameField.getText(), passwordField.getText(), credential.getText(), tphone.getText());
                stage.setUserData(user);
                //make sure user created successfully
                if(user != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    loader.setController(new VisitorDashboardController());
                    Scene scene = new Scene(loader.load(), 700, 600);
                    stage.setScene(scene);
                }
            } else {
                //if page is used to update profile
                //set the fields with the existing/updated values
                userManaged.setName(nameField.getText());
                userManaged.setUsername(usernameField.getText());
                userManaged.setPassword(passwordField.getText());
                if(userManaged instanceof Owner) {
                    ((Owner) userManaged).setOwnershipCode(credential.getText());
                } else if(userManaged instanceof Agent) {
                    ((Agent) userManaged).setLicenseCode(credential.getText());
                }
                userManaged.setPhone(tphone.getText());
                //go to the dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                loader.setController(new VisitorDashboardController());
                Scene scene = new Scene(loader.load(), 700, 600);
                stage.setScene(scene);
                userManaged = null;
            }
    }
}