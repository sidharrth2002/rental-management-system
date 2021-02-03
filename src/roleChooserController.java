import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class roleChooserController extends Controller {
    public VBox root;

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) root.getScene().getWindow();
        //identify type of user since this controller is used by multiple pages
        if(button.getId().equals("admin")) {
            LoginController.typeOfUser = "admin";
        } else if(button.getId().equals("owner")) {
            LoginController.typeOfUser = "owner";
        } else if(button.getId().equals("agent")) {
            LoginController.typeOfUser = "agent";
        } else if(button.getId().equals("tenant")) {
            LoginController.typeOfUser = "tenant";
        }
        //login page
        Parent loginfxml = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginPage = new Scene(loginfxml, 700, 600);
        stage.setScene(loginPage);
    }

    //go to search page
    public void goToVisitorDashboard(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        Parent dashboard = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene register = new Scene(dashboard, 700, 600);
        stage.setScene(register);
    }
}
