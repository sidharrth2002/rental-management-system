import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController extends Controller {
    public GridPane root;
    public TextField usernameField;
    public PasswordField passwordField;
    public Stage stage;
    public static String typeOfUser;

    public void submitForm(ActionEvent actionEvent) throws IOException {
        Window window = root.getScene().getWindow();
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
        ArrayList<User> users = userFactory.getUsers();
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean correct = false;
        User loggedInUser = null;
        for(User user: users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                correct = true;
                break;
            }
        }
        if(correct) {
            //each will have their own dashboard(menu will have a few different options)
            //but same until those are all ready
            Stage stage = (Stage) root.getScene().getWindow();
            if(loggedInUser instanceof Agent) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            } else if(loggedInUser instanceof Owner) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            } else if(loggedInUser instanceof Tenant) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            } else if(loggedInUser instanceof Admin) {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene dashboard = new Scene(root, 700, 600);
                stage.setScene(dashboard);
            }
        } else {
            showAlert(Alert.AlertType.ERROR, window, "Incorrect Login!",
                    "Please try again!");
        }
    }

    public void goToRoleChooser(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
        Scene dashboard = new Scene(root, 700, 600);
        stage.setScene(dashboard);
    }

    public void goToRegisterPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        Window window = root.getScene().getWindow();
        if(typeOfUser.equals("agent")) {
            Parent registerfxml = FXMLLoader.load(getClass().getResource("agentRegister.fxml"));
            Scene register = new Scene(registerfxml, 700, 600);
            stage.setScene(register);
        } else if(typeOfUser.equals("owner")) {
            Parent registerfxml= FXMLLoader.load(getClass().getResource("ownerRegister.fxml"));
            Scene register = new Scene(registerfxml, 700, 600);
            stage.setScene(register);
        } else if(typeOfUser.equals("tenant")) {
            Parent registerfxml = FXMLLoader.load(getClass().getResource("tenantRegister.fxml"));
            Scene register = new Scene(registerfxml, 700, 600);
            stage.setScene(register);
        } else if(typeOfUser.equals("admin")) {
//            showAlert(Alert.AlertType.WARNING, window, "Not Available",
//                    "Admins can only be added by other admins.");
            Parent registerfxml = FXMLLoader.load(getClass().getResource("admindashboard.fxml"));
            Scene register = new Scene(registerfxml, 700, 600);
            stage.setScene(register);
        }

    }
}
