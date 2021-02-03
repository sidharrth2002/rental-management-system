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
    public static String typeOfUser;

    //checks fields and if ok, compares with user list to check if login is correct
    //if correct, login user and set the user data on the stage
    public void submitForm(ActionEvent actionEvent) throws IOException {
        Window window = root.getScene().getWindow();
        if(usernameField.getText().isEmpty()) {
            //use showAlert from parent Controller
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
            stage.setUserData(loggedInUser);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            loader.setController(new VisitorDashboardController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } else {
            showAlert(Alert.AlertType.ERROR, window, "Incorrect Login!",
                    "Please try again!");
            stage.setUserData(null);
        }
    }

    //back to the role chooser to login from a different interface
    public void goToRoleChooser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("roleChooser.fxml"));
        loader.setController(new RoleChooserController());
        Scene scene = new Scene(loader.load(), 700, 600);
        stage.setScene(scene);
    }

    //depending on type of user, load corresponding register page
    public void goToRegisterPage(ActionEvent actionEvent) throws IOException {
        Window window = (Window) stage;
        if(typeOfUser.equals("agent")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agentRegister.fxml"));
            loader.setController(new RegisterPageController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } else if(typeOfUser.equals("owner")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerRegister.fxml"));
            loader.setController(new RegisterPageController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } else if(typeOfUser.equals("tenant")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tenantRegister.fxml"));
            loader.setController(new RegisterPageController());
            Scene scene = new Scene(loader.load(), 700, 600);
            stage.setScene(scene);
        } else if(typeOfUser.equals("admin")) {
            showAlert(Alert.AlertType.ERROR, window, "Unavailable!",
                    "Yo, admin cannot register. You have to BE registered by another admin.");
        }

    }
}
